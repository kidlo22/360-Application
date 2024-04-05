package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
	
	Main m = new Main();
	FileSystemManager fileManager = new FileSystemManager();
	
	public void login(ActionEvent e) {
		boolean userHasValidCredentials = false;
		try {
			userHasValidCredentials = fileManager.authenticateUser(username.getText().toString(), password.getText().toString(), id.getText().toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (userHasValidCredentials) {
			m.changeScene("Patientview.fxml");
		}
		else if (username.getText().toString().equals("Doctor") && password.getText().toString().equals("Password") && id.getText().toString().equals("Doctor1011")) {
			m.changeScene("Doctorview.fxml");
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

}