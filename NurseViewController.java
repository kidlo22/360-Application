package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class NurseViewController {
	@FXML
    	private TextField patientHeight;
	@FXML
	private TextField patientWeight;
	@FXML
	private TextField patientTemperature;
	@FXML
	private TextField patientSysBP;
	@FXML
	private TextField patientDiaBP;
	@FXML
	private TextArea patientImmunizationRecordText;
	@FXML
	private TextArea patientHealthIssue;
	@FXML
	private TextArea patientPrescription;
	@FXML
	private TextArea patientAllergies;
	@FXML
	private TextArea patientHealthConcerns;
	@FXML
	private TextField patientPharmacyID;
	@FXML
	private TextField patientInsuranceID;
	@FXML
	private Button signOutButton;
	@FXML
	private Button saveButton;
	@FXML
	private TextField visitDateField;
	@FXML
	private TextField retrievePatientVisitTextField;
	@FXML 
	private Label patientNameLabel;
	private static PatientData patientDataInfo;
	private static String patientIDNurse;
	private String currentUserRole = "Nurse";
	private String currentUserID;
	
	public NurseViewController() {
		// Default constructor
	}
	
	Main newMain = new Main();
	FileSystemManager fileManager = new FileSystemManager();
    
	void populateNurseView(PatientData patientData) {
        	patientImmunizationRecordText.setText(patientData.getImmunizationRecord());
        	patientHealthIssue.setText(patientData.getHealthIssues());
        	patientPrescription.setText(patientData.getPrescriptions());
        	patientAllergies.setText(patientData.getAllergies());
        	patientHealthConcerns.setText(patientData.getHealthConcerns());
        	patientPharmacyID.setText(patientData.getPharmacyId());
        	patientInsuranceID.setText(patientData.getInsuranceId());
        	patientNameLabel.setText(fileManager.getPatientName(patientData.getPatientId()));
	}
	
	
	public void createVisit(ActionEvent e) {
		newMain.changeScene("NewVisit.fxml");
	}
	
	public PatientData getPatientData(PatientData patientData) {
		patientDataInfo = patientData;
		patientIDNurse = patientData.getPatientId();

	    	return patientDataInfo;
	}

	@FXML
	public void saveVitals(ActionEvent e) {
		System.out.println(patientIDNurse);
		
		String height = patientHeight.getText().trim();
		String weight = patientWeight.getText().trim();
		String bodyTemp = patientTemperature.getText().trim();
		String bpSys = patientSysBP.getText().trim();
		String bpDia = patientDiaBP.getText().trim();

		if (!checkVitals(height) || !checkVitals(weight) || !checkVitals(bodyTemp) || !checkVitals(bpSys) || !checkVitals(bpDia)) {
			showPopupMessage("Please enter valid height, weight, and blood pressure.");
			return;
		}
		
		patientDataInfo.setHeight(height);
		patientDataInfo.setWeight(weight);
		patientDataInfo.setBodyTemp(bodyTemp);
		patientDataInfo.setBloodPressureSys(bpSys);
		patientDataInfo.setBloodPressureDia(bpDia);
		
		String filePath = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientIDNurse + "Visit" + visitDateField.getText().trim() + ".txt";
		File userInfoFile = new File(filePath);
		
		try  {
			userInfoFile.createNewFile();
			FileWriter writer = new FileWriter(filePath);
	        	writer.write("Patient ID: " + patientDataInfo.getPatientId() + "\n");
	        	writer.write("Height: " + patientDataInfo.getHeight() + "\n");
	        	writer.write("Weight: " + patientDataInfo.getWeight() + "\n");
	        	writer.write("Body Temperature: " + patientDataInfo.getBodyTemp() + "\n");
	        	writer.write("Blood Pressure (Sys): " + patientDataInfo.getBloodPressureSys() + "\n");
	        	writer.write("Blood Pressure (Dia): " + patientDataInfo.getBloodPressureDia() + "\n");
	        	writer.write("Immunization Record: " + patientDataInfo.getImmunizationRecord() + "\n");
	        	writer.write("Health Issues: " + patientDataInfo.getHealthIssues() + "\n");
	        	writer.write("Prescriptions: " + patientDataInfo.getPrescriptions() + "\n");
	        	writer.write("Allergies: " + patientDataInfo.getAllergies() + "\n");
	        	writer.write("Health Concerns: " + patientDataInfo.getHealthConcerns() + "\n");
	        	writer.write("Pharmacy ID: " + patientDataInfo.getPharmacyId() + "\n");
	        	writer.write("Insurance ID: " + patientDataInfo.getInsuranceId() + "\n");
	        
	        	writer.close();
		}
		
		catch (IOException ex) {
			showErrorDialog("Failed to save patient vitals: " + ex.getMessage());
		}
		
		newMain.changeScene("DoctorView.fxml");
	}

	public boolean checkVitals(String vital) {
		try {
			double number = Double.parseDouble(vital);
			return number > 0;
		} catch (NumberFormatException e) {
			// Handle when input is not a valid number
			return false;
		}
	}
	
	public void retrievePatientData(ActionEvent e) {
		String date = retrievePatientVisitTextField.getText().trim();
		
		if (!date.isEmpty()) {
	        String filePath = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientIDNurse + "Visit" + date + ".txt";
	        File file = new File(filePath);
	        if (file.exists()) 
	            {try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    if (line.startsWith("Height:")) {
                        patientHeight.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Weight:")) {
                        patientWeight.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Body Temperature:")) {
                        patientTemperature.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Blood Pressure (Sys):")) {
                        patientSysBP.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Blood Pressure (Dia):")) {
                        patientDiaBP.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Immunization Record:")) {
                        patientImmunizationRecordText.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Health Issues:")) {
                        patientHealthIssue.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Prescriptions:")) {
                        patientPrescription.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Allergies:")) {
                        patientAllergies.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Health Concerns:")) {
                        patientHealthConcerns.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Pharmacy ID:")) {
                        patientPharmacyID.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Insurance ID:")) {
                        patientInsuranceID.setText(line.substring(line.indexOf(":") + 2));
                    }
                }
            } catch (IOException exception) {
            	exception.printStackTrace();
            }
        } else {
        	showPopupMessage("A Visit with that date was not found. Please review carefully.");
            System.out.println("File not found: " + filePath);
        }
		}
		
	}

	public String getCurrentUserRole() {
		return currentUserRole;
	}

	public String getCurrentUserId() {
		return currentUserID;
	}

	public void launchMessagingSystem(ActionEvent e) {
		try {
			String role = getCurrentUserRole();
			String userID = patientIDNurse;
			if (role.equals("Nurse")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MessagingSystem.fxml"));
				Parent root = loader.load();

				MessagingSystemController messagingController = loader.getController();
				messagingController.initData("Nurse1011",userID);
				messagingController.loadMessages("Nurse");
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("Messaging System");
				stage.show();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			showErrorDialog("Failed to launch messaging system: " + e1.getMessage());
		}
	}

	@FXML
	public void signOut(ActionEvent e) {
		newMain.changeScene("Home.fxml");
	}
	
	private void showPopupMessage(String message) {
    		Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Warning");
        	alert.setHeaderText(null);
        	alert.setContentText(message);
        	alert.showAndWait();
	}
	
	private void showErrorDialog(String message) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText(null);
        	alert.setContentText(message);
        	alert.showAndWait();
    	}
}