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
	@FXML
	private TextField messageField;
	@FXML 
	private Label errorLabel;
	@FXML 
	private Label emptyFieldLabel;
	@FXML 
	private Label accountExistsLabel;
	
	private String thisPatientID;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// Attributes to store the current user's role and ID
	private static String currentUserRole;
	private static String currentUserId;
	
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
			m.changeScene("nurseID.fxml");
		}
		else {
			errorLabel.setVisible(true);
		}
	}
	
	public void signup(ActionEvent e) {
		m.changeScene("Signup.fxml");
	}
	
	public void returnToHome(ActionEvent e) {
		m.changeScene("Home.fxml");
	}
	
	public void createNewUser(ActionEvent e) {
		if (newUsername.getText().toString().isEmpty() ||newPassword.getText().toString().isEmpty() || firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || dob.getText().toString().isEmpty() || phoneNum.getText().toString().isEmpty() || email.getText().toString().isEmpty() || insuranceID.getText().isEmpty())
		{
			emptyFieldLabel.setVisible(true);
			return;
		}
		Patient patient = new Patient(newUsername.getText().toString(),newPassword.getText().toString(),firstName.getText().toString(),lastName.getText().toString(),dob.getText().toString(),phoneNum.getText().toString(),email.getText().toString(),Integer.parseInt(insuranceID.getText()));
		boolean addedPatient = fileManager.addUserToSystem(patient);
		if (addedPatient) {
			m.changeScene("Home.fxml");
		}
		accountExistsLabel.setVisible(true);
	}	
	public void sendMessage(ActionEvent e) {
		Message message = new Message(messageField.getText().toString());
		fileManager.addMessageToSystem(message);
	}

	public String getCurrentUserRole() {
        	return currentUserRole;
    	}

	public String getCurrentUserId() {
        	return currentUserId;
    	}
	// Method to launch the messaging system
}