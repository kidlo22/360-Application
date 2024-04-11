package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class DoctorViewController {
	@FXML
    private TextField patientHeight;
    @FXML
    private TextField patientWeight;
    @FXML
    private TextField patientTemperature;
    @FXML
    private TextField patientSysBloodPressure;
    @FXML
    private TextField patientDiaBloodPressure;
    @FXML
    private TextArea patientImmunizationRecordText;
    @FXML
    private TextArea patientVisitHealthIssue;
    @FXML
    private TextArea patientVisitPrescription;
    @FXML
    private TextArea patientAllergies;
    @FXML
    private TextArea patientHealthConcerns;
    @FXML
    private TextField patientPharmacyID;
    @FXML
    private TextField patientInsuranceID;
    @FXML
    private TextArea doctorVisitRecommendations;
    @FXML
    private Button signOutButton;
    @FXML
    private Button saveVisitButton;
    @FXML
    private TextField visitDateField;
    @FXML
    private TextField retrievePatientVisitTextField;
    
    
    
    @FXML 
	private Label patientNameLabel;
    
    private static PatientData patientDataInfo;
    
    private static String patientIDDoctor;
    
    
    // constructor
    
    public DoctorViewController() {
        // Default constructor
    }
    
    
    
    Main m = new Main();
	FileSystemManager fileManager = new FileSystemManager();
    
	void populateDoctorView(PatientData patientData) {
		patientHeight.setText(patientData.getHeight());
        patientWeight.setText(patientData.getWeight());
        patientTemperature.setText(patientData.getBodyTemp());
        
        patientSysBloodPressure.setText(patientData.getBloodPressureSys());
        patientDiaBloodPressure.setText(patientData.getBloodPressureDia());
        
        patientImmunizationRecordText.setText(patientData.getImmunizationRecord());
        patientVisitHealthIssue.setText(patientData.getHealthIssues());
        patientVisitPrescription.setText(patientData.getPrescriptions());
        patientAllergies.setText(patientData.getAllergies());
        patientHealthConcerns.setText(patientData.getHealthConcerns());
        patientPharmacyID.setText(patientData.getPharmacyId());
        patientInsuranceID.setText(patientData.getInsuranceId());
		
        patientNameLabel.setText(fileManager.getPatientName(patientData.getPatientId()));
	}
	
	

	public void createVisit(ActionEvent e) {
		m.changeScene("NewVisit.fxml");
		//System.out.println("hello");
	}
	
	
	// responsible for data transfer between two controllers
	public PatientData getPatientData(PatientData patientData) {
		patientDataInfo = patientData;
		patientIDDoctor = patientData.getPatientId();

	    return patientDataInfo;
	}
	
	
	
	public void saveVisit(ActionEvent e) {
		
		PatientData patientInfo = new PatientData();
		
		String height = patientHeight.getText().toString();
	    String weight = patientWeight.getText().toString();
	    String bodyTemp = patientTemperature.getText().trim();
	    String bloodPressureSys = patientSysBloodPressure.getText().trim();
	    String bloodPressureDia = patientDiaBloodPressure.getText().trim();
	    String immunizationRecord = patientImmunizationRecordText.getText().trim();
	    String healthIssues = patientVisitHealthIssue.getText().trim();
	    String prescriptions = patientVisitPrescription.getText().trim();
	    String allergies = patientAllergies.getText().trim();
	    String healthConcerns = patientHealthConcerns.getText().trim();
	    String pharmacyId = patientPharmacyID.getText().trim();
	    String insuranceId = patientInsuranceID.getText().trim();
	    String recommendations = doctorVisitRecommendations.getText().trim();
		
	    patientInfo.setHeight(height);
	    patientInfo.setWeight(weight);
	    patientInfo.setBodyTemp(bodyTemp);
	    patientInfo.setBloodPressureSys(bloodPressureSys);
	    patientInfo.setBloodPressureDia(bloodPressureDia);
	    patientInfo.setImmunizationRecord(immunizationRecord);
	    patientInfo.setHealthIssues(healthIssues);
	    patientInfo.setPrescriptions(prescriptions);
	    patientInfo.setAllergies(allergies);
	    patientInfo.setHealthConcerns(healthConcerns);
	    patientInfo.setPharmacyId(pharmacyId);
	    patientInfo.setInsuranceId(insuranceId);
	    patientInfo.setRecommendations(recommendations);
		
		String filePath = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientIDDoctor + "Visit" + visitDateField.getText().trim() + ".txt";
		File userInfoFile = new File(filePath);
		
		try  {
			userInfoFile.createNewFile();
			FileWriter writer = new FileWriter(filePath);
	        writer.write("Patient ID: " + patientInfo.getPatientId() + "\n");
	        writer.write("Height: " + patientInfo.getHeight() + "\n");
	        writer.write("Weight: " + patientInfo.getWeight() + "\n");
	        writer.write("Body Temperature: " + patientInfo.getBodyTemp() + "\n");
	        writer.write("Blood Pressure (Sys): " + patientInfo.getBloodPressureSys() + "\n");
	        writer.write("Blood Pressure (Dia): " + patientInfo.getBloodPressureDia() + "\n");
	        writer.write("Immunization Record: " + patientInfo.getImmunizationRecord() + "\n");
	        writer.write("Health Issues: " + patientInfo.getHealthIssues() + "\n");
	        writer.write("Prescriptions: " + patientInfo.getPrescriptions() + "\n");
	        writer.write("Allergies: " + patientInfo.getAllergies() + "\n");
	        writer.write("Health Concerns: " + patientInfo.getHealthConcerns() + "\n");
	        writer.write("Pharmacy ID: " + patientInfo.getPharmacyId() + "\n");
	        writer.write("Insurance ID: " + patientInfo.getInsuranceId() + "\n");
	        writer.write("Recommendation: " + patientInfo.getRecommendations() + "\n");
	        
	        // Close the writer
	        writer.close();
		
		}
		
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		m.changeScene("DoctorView.fxml");
	}
	
	
	public void retreivePatientDataFromDate(ActionEvent e) {
		String date = retrievePatientVisitTextField.getText().trim();
		
		if (!date.isEmpty()) {
	        String filePath = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientIDDoctor + "Visit" + date + ".txt";
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
                        patientSysBloodPressure.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Blood Pressure (Dia):")) {
                        patientDiaBloodPressure.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Immunization Record:")) {
                        patientImmunizationRecordText.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Health Issues:")) {
                        patientVisitHealthIssue.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Prescriptions:")) {
                        patientVisitPrescription.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Allergies:")) {
                        patientAllergies.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Health Concerns:")) {
                        patientHealthConcerns.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Pharmacy ID:")) {
                        patientPharmacyID.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Insurance ID:")) {
                        patientInsuranceID.setText(line.substring(line.indexOf(":") + 2));
                    } else if (line.startsWith("Recommendation:")) {
                    	doctorVisitRecommendations.setText(line.substring(line.indexOf(":") + 2));
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
	
	
	public void signOutDoctor(ActionEvent e) {
		m.changeScene("Home.fxml");
	}
	
	private void showPopupMessage(String message) {
    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}

	
}
