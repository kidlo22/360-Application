package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileSystemManager {
	
	private String dir = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/users/";
	private String messageDir = "/Users/loganreny/eclipse-workspace/CSE 360/Prototype/src/application/messages/";
	
	
	public boolean addUserToSystem(Patient patient) {
		String filePath = dir + patient.getPaitentID() + ".txt";
		File myObj = new File(filePath);
		
		if (myObj.exists()) {
		    return false;
		} 
		
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
	
	public void addMessageToSystem(Message message) {
		System.out.println(System.currentTimeMillis());
		
	}

	public boolean storeMessage(String senderId, String recipientId, String message) {
		Path filePath = Paths.get(messageDir, senderId + "_to_" + recipientId + ".txt");
	    try {
		    	Files.createDirectories(filePath.getParent());
		        Files.write(filePath, (message + "\n---\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		        return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
    	}

    public List<String> retrieveMessages(String senderId, String recipientId) {
    	Path filePath = Paths.get(messageDir, senderId + "_to_" + recipientId + ".txt");
    	System.out.println(filePath);
	    List<String> messages = new ArrayList<>();
	    if (!Files.exists(filePath)) {
	    	System.out.println("No messages");
	    	return messages; // No messages to retrieve
	    }
	
	    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
	    	StringBuilder message = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	        	if ("---".equals(line)) { // Message delimiter
	        		messages.add(message.toString());
	                message = new StringBuilder(); // Reset for the next message
	        	} else {
	        		message.append(line).append("\n");
	            }
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	      }
	    return messages;
    }
}
