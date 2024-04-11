package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystemManager {
	
	private String dir = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/";
	
	public boolean addUserToSystem(Patient patient) {
		String filePath = dir + patient.getPaitentID() + ".txt";
		File myObj = new File(filePath);
		
		try {
			// creates file in the specified path and then adds patient data to file
			
			myObj.createNewFile();
			FileWriter myWriter = new FileWriter(dir + patient.getPaitentID() + ".txt");
			myWriter.write("Username: " + patient.getUserName());
			myWriter.write(System.lineSeparator());
			myWriter.write("Password: " + patient.getPassWord());
			myWriter.write(System.lineSeparator());
			myWriter.write("First Name: " + patient.getFirstName());
			myWriter.write(System.lineSeparator());
			myWriter.write("Last Name: " + patient.getLastName());
			myWriter.write(System.lineSeparator());
			myWriter.write("Date of birth: " + patient.getDateOfBirth());
			myWriter.write(System.lineSeparator());
			myWriter.write("Email: " + patient.getEmail());
			myWriter.write(System.lineSeparator());
			myWriter.write("Phone Number: " + patient.getPhoneNumber());
			myWriter.write(System.lineSeparator());
			myWriter.write("Insurance ID: " + patient.getInsuranceID());
			myWriter.write(System.lineSeparator());
			myWriter.write("Patient ID:  " + patient.getPaitentID());
			
			myWriter.close();
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
	
	public boolean authenticateUser(String userNameEntered, String passWordEntered, String id) throws IOException {
		File patientFile = new File(dir + id + ".txt");
		// checks if patient file exits if not account has not yet been created
		if (!patientFile.exists()) {
		    return false;
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(patientFile));
			String line1 = br.readLine();
			int colonIndex = line1.indexOf(":");
			String usernameFromFile = line1.substring(colonIndex + 1).trim();
			// gets the username
			
			String line2 = br.readLine();
			int colonIndex2 = line2.indexOf(":");
			String passwordFromFile = line2.substring(colonIndex2 + 1).trim();
			// gets the password
			br.close();
			
			if (usernameFromFile.equals(userNameEntered) && passwordFromFile.equals(passWordEntered)) {
				return true;
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getPatientName (String patientID) {
		File patientFile = new File(dir + patientID + ".txt");
		
		try {
			String line;
            String firstName = null;
            String lastName = null;
			BufferedReader br = new BufferedReader(new FileReader(patientFile));
			
			while ((line = br.readLine()) != null) {
                if (line.startsWith("First Name:")) {
                    firstName = line.substring("First Name:".length()).trim();
                } else if (line.startsWith("Last Name:")) {
                    lastName = line.substring("Last Name:".length()).trim();
                }
            }
			
			return firstName + " " + lastName;
				
			
		} catch (IOException e) {
			e.printStackTrace();
			return patientID;
		}
		
	}

	public boolean patientFileExists(String patientID) {
		String filePath = dir + patientID + ".txt";
	    File file = new File(filePath);
	    return file.exists();
	}

	public PatientData loadPatientData(String patientID) {
		PatientData patientData = new PatientData(patientID, "", "", "", "", "", "", "", "", "", "", "", "", "");
		return patientData;
	}

}
