package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;

public class Controller {
	
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField id;
	@FXML
	private TextField newUsername;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField confirmPassword;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField dob;
	@FXML
	private TextField phoneNum;
	@FXML
	private TextField email;
	@FXML
	private TextField insuranceID;
	
	
	
	@FXML 
	private Label patientNameLabel;
	
	private String thisPatientID;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Main m = new Main();
	FileSystemManager fileManager = new FileSystemManager();
	
	public void login(ActionEvent e) throws IOException {
		boolean userHasValidCredentials = false;
		try {
			userHasValidCredentials = fileManager.authenticateUser(username.getText().toString(), password.getText().toString(), id.getText().toString());
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		if (userHasValidCredentials) {
			String patientID = id.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));	
    		root = loader.load();
    		
    		PatientViewController patientView = loader.getController();
    		patientView.getPatientID(patientID);
    		patientView.setLabel();
    		
    		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
			
		}
		else if (username.getText().toString().equals("Doctor") && password.getText().toString().equals("Password") && id.getText().toString().equals("Doctor1011")) {
			m.changeScene("patientID.fxml");
		}
		else if (username.getText().toString().equals("Nurse") && password.getText().toString().equals("Password") && id.getText().toString().equals("Nurse1011")) {		
			m.changeScene("Nurseview.fxml");
		}
		else {
			System.out.println("Wrong");
		}
	}
	
	public void signup(ActionEvent e) {
		m.changeScene("Signup.fxml");
	}
	
	public void returnToHome(ActionEvent e) {
		m.changeScene("Home.fxml");
	}
	
	public void createNewUser(ActionEvent e) {
		Patient patient = new Patient(newUsername.getText().toString(),newPassword.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),dob.getText().toString(),phoneNum.getText().toString(),email.getText().toString(),Integer.parseInt(insuranceID.getText()));
		fileManager.addUserToSystem(patient);
	}	
	public void sendMessage(ActionEvent e) {
		Message message = new Message(messageField.getText().toString());
		fileManager.addMessageToSystem(message);
	}

	// Method to launch the messaging system
	public void launchMessagingSystem() {
	    try {
	        // Assuming a method or means to get the logged-in user's role and ID
	        String role = getCurrentUserRole();
	        String userId = getCurrentUserId();
	
	        if (role.equals("Patient")) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("MessagingSystem.fxml"));
	            Parent root = loader.load();
	
	            MessagingSystemController messagingController = loader.getController();
	            messagingController.initData(userId);
	
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Messaging System");
	            stage.show();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}