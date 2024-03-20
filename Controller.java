package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller {
	
	@FXML
	private Button button;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
	
	public void login(ActionEvent e) {
		
		Main m = new Main();
		
		if (username.getText().toString().equals("Patient") && password.getText().toString().equals("Password")) {
			
			m.changeScene("Patientview.fxml");
			
		}
		else if (username.getText().toString().equals("Doctor") && password.getText().toString().equals("Password")) {
			
			m.changeScene("Doctorview.fxml");
		}
		else if (username.getText().toString().equals("Nurse") && password.getText().toString().equals("Password")) {
			
			m.changeScene("Nurseview.fxml");
		}
		else {
			System.out.println("Wrong");
		}
	}

}

