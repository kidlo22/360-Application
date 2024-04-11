
package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class MessagingSystemController {

    @FXML
    private TextArea messageHistory;
    @FXML
    private TextArea composeMessage;
    @FXML
    private Button sendMessage;
    @FXML
    private ListView<String> messageList; 
    @FXML
    private TextField recipientIdField; // TextField for entering the recipient's ID
    @FXML
    private Button sendMessageButton;

    private FileSystemManager fileManager = new FileSystemManager();
    private String patientID; // Logged-in user ID
    private String userId;
    private String nurseID = "Nurse1011";// Recipient ID, determined based on user role
    private String doctorID = "Doctor1011"; 

    public void initData(String userId, String patientID) {
        this.userId = userId;
        this.patientID = patientID;
        
    }

    public void loadMessages(String user) {
    	messageList.getItems().clear();
    	if (user.equalsIgnoreCase("Doctor")) {
            List<String> doctorMessages = fileManager.retrieveMessages(patientID, doctorID);
            for (String message : doctorMessages) {
                messageList.getItems().add(message); 
            }
    	}
    	else if (user.equalsIgnoreCase("Nurse")) {
	        List<String> messages = fileManager.retrieveMessages(patientID, nurseID);
	        messageList.getItems().clear(); 
	        for (String message : messages) {
	            messageList.getItems().add(message); 
	        }
    	}
    	else {
		    List<String> doctorMessages = fileManager.retrieveMessages(doctorID, user);
		    messageList.getItems().clear(); 
		    for (String message : doctorMessages) {
		    	messageList.getItems().add(message); 
		    }
		    List<String> nurseMessages = fileManager.retrieveMessages(nurseID, user);
		    System.out.println(nurseMessages);
		    for (String messages : nurseMessages) {
		    	messageList.getItems().add(messages); 
		    }
		        
    	}
    		
    }
    

    @FXML
    private void onSendMessage(ActionEvent event) {
        String messageContent = composeMessage.getText().trim();
        System.out.println(messageContent);
        String recipientId = recipientIdField.getText().trim(); // Get recipient ID from input field
        System.out.println(recipientId);

        if (!messageContent.isEmpty() && !recipientId.isEmpty()) {
            boolean isMessageStored = fileManager.storeMessage(userId, recipientId, messageContent);
            if (isMessageStored) {
                composeMessage.clear();
                recipientIdField.clear(); // Clear the recipient ID field after sending
                //loadMessages(); // Optionally refresh message list
            } else {
                // Handle error in storing the message
                System.err.println("Failed to store message.");
            }
        } else {
            // Handle case where message content or recipient ID is empty
            System.err.println("Message content or recipient ID is empty.");
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
