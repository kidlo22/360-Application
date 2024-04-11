package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PatientViewController {

	@FXML
	private Button signOutButton;
	
    @FXML
    private TextField fNameTextField;

    @FXML
    private TextField lNameTextField;

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField pNumberTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField insuranceIDTextField;

    @FXML
    private TextField pharmacyTextField;
    
    @FXML
    private TextField visitDate;
    
    @FXML
    private TextArea dSummaryTextArea;
    
    @FXML
    private TextArea presciptionTextArea;
    
    @FXML
    private Text PatientLabel;
    @FXML
    private Button sendButton;
    
    private static String patientID;
    
    private String recommendations;
    private String healthIssues;
    private String prescriptions;
    private String username;
    private String pass;
    private String FirstName;
    private String LastName;
    Main m = new Main();
    FileSystemManager fileManager = new FileSystemManager();
    
    
    public void setLabel() {    
    	String fileName = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientID + ".txt"; // Specify the file name
       
        File file = new File(fileName);

        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    if (line.startsWith("First Name:")) {
                    	FirstName = line.substring(line.indexOf(":") + 2);
                    } else if (line.startsWith("Last Name:")) {
                    	LastName = line.substring(line.indexOf(":") + 2);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
        	System.out.println("File not found" + patientID);
        }
        PatientLabel.setText(FirstName + " " + LastName);
    }
    

    //Patients view Update info button
    public void updateInfo(ActionEvent e) {
        String fileName = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientID + ".txt"; // Specify the file name
        
        boolean isAnyFieldEmpty = false;
        File file = new File(fileName);

        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    if (line.startsWith("Username:")) {
                        username = line.substring(line.indexOf(":") + 2);
                    } else if (line.startsWith("Password:")) {
                        pass = line.substring(line.indexOf(":") + 2);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        
        else {
        	System.out.println("file not found");
        }
        
        if (fNameTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        } else if (lNameTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        } else if (dobTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        } else if (emailTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        } else if (pNumberTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        } else if (insuranceIDTextField.getText().trim().isEmpty()) {
            isAnyFieldEmpty = true;
        }
        
        
        
        if (isAnyFieldEmpty) {
            // Display a pop-up message
            showPopupMessage("Please fill in all the fields before updating.");
        } else {
            
            try {
                FileWriter writer = new FileWriter(fileName);
                writer.write("Username: " + username);
                writer.write(System.lineSeparator());
                writer.write("Password: " + pass);
                writer.write(System.lineSeparator());
                writer.write("First Name: " + fNameTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Last Name: " + lNameTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Date of birth: " + dobTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Email: " + emailTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Phone Number: " + pNumberTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Insurance ID: " + insuranceIDTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Pharmacy ID: " + pharmacyTextField.getText());
                writer.write(System.lineSeparator());
                writer.write("Patient ID: " + patientID);
                writer.close();
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        
        }
        
        
    }
       
    public void getPatientSummary(ActionEvent e) {
    	String fileName = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/" + patientID + "Visit" + visitDate.getText().trim() +".txt"; // Specify the file name
    	
        File file = new File(fileName);

        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(fileName));
                for (String line : lines) {
                    if (line.startsWith("Health Issues:")) {
                        healthIssues = line.substring(line.indexOf(":") + 2);
                    } else if (line.startsWith("Prescriptions:")) {
                        prescriptions = line.substring(line.indexOf(":") + 2);
                    } else if (line.startsWith("Recommendation:")) {
                        recommendations = line.substring(line.indexOf(":") + 2);
                    }
                    
                    dSummaryTextArea.setText(recommendations);
                    presciptionTextArea.setText("Health Issues: " + healthIssues + "\n" + "Prescriptions: " + prescriptions);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        
        else {
        	showPopupMessage("A Visit with that date was not found. Please review carefully.");
        	System.out.println("file not found");
        }
        
    }
    
	public void launchMessagingSystem(ActionEvent e) {
	    try {
	        // Assuming a method or means to get the logged-in user's role and ID
	        String userId = patientID;
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("MessagingSystem.fxml"));
	        Parent root = loader.load();
	
	            MessagingSystemController messagingController = loader.getController();
	            messagingController.initData(userId, userId);
	            messagingController.loadMessages(userId);
	
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Messaging System");
	            stage.show();
	    } catch (Exception e1) {
	    	
	        e1.printStackTrace();
	    }
	}
    
    
    public void signOut(ActionEvent e) {
		m.changeScene("Home.fxml");
	}

    private void showPopupMessage(String message) {
    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
	}

	public void getPatientID(String id) {
        patientID = id;
    }
}