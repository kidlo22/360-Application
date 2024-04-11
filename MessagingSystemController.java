package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

public class MessagingSystemController {

    @FXML
    private TextArea messageHistory;
    @FXML
    private TextArea composeMessage;
    @FXML
    private Button sendMessage;

    private FileSystemManager fileManager = new FileSystemManager();
    private String userId; // Logged-in user ID
    private String recipientId; // Recipient ID, determined based on user role

    public void initData(String userId, String recipientId) {
        this.userId = userId;
        this.recipientId = recipientId;
        loadMessages(); // Load existing conversation
    }

    private void loadMessages() {
        List<String> messages = fileManager.retrieveMessages(userId, recipientId);
        messages.forEach(message -> messageHistory.appendText(message + "\n"));
    }

    @FXML
    public void onSendMessage(ActionEvent event) {
        String message = composeMessage.getText().trim();
        if (!message.isEmpty()) {
            boolean success = fileManager.storeMessage(userId, recipientId, "You: " + message);
            if (success) {
                messageHistory.appendText("You: " + message + "\n---\n");
                composeMessage.clear();
            } else {
                showAlert("Error", "Failed to send message.");
            }
        }
    }

    @FXML
    public void onClose(ActionEvent event) {
        sendMessage.getScene().getWindow().hide();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}