package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NurseIDController {
	@FXML
	private TextField patientIDTextField;
	
	private String thisPatientID;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	Main m = new Main();
	FileSystemManager fileManager = new FileSystemManager();
	
	public void handlePatientIDInput(ActionEvent e) throws IOException {
        String patientID = patientIDTextField.getText();
        thisPatientID = patientID;
        if (fileManager.patientFileExists(patientID)) {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("NurseView.fxml"));	
    		root = loader.load();
    		
    		NurseViewController nurseView = loader.getController();
    		nurseView.getPatientData(fileManager.loadPatientData(patientID));
    		
    		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
            
        } else {
        	showPopupMessage("Invalid Patient ID.");
            showErrorMessage("Invalid patient ID");
        }
    }
	
	private void showErrorMessage(String string) {
		System.out.println(string);
		
	}
	
	private void showPopupMessage(String message) {
    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}
}
