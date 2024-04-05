package application;

public class Patient {

	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String phoneNumber;
	private String email;
	private int insuranceID;
	private String patientID;
	
	public Patient(String username, String password, String firstname, String lastname, String dateofbirth, String phonenumber, String email, int insuranceid) {
		this.userName = username;
		this.passWord = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.dateOfBirth = dateofbirth;
		this.phoneNumber = phonenumber;
		this.email = email;
		this.insuranceID = insuranceid;
		this.patientID = createPatientID(firstName,lastName,dateOfBirth);
	}
	
	private String createPatientID(String firstName, String lastName, String dateOfBirth) {
		String dateOfBirthSubstring = dateOfBirth.substring(Math.max(dateOfBirth.length() - 2, 0));
		String createdPatientID = firstName + lastName + dateOfBirthSubstring;
		return createdPatientID;
	}
	
	public void setUserName(String username) {
		this.userName = username;
	}
	
	public void setPassWord(String password) {
		this.passWord = password;
	}
	
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	
	public void setDateOfBirth(String dateofbirth) {
		this.dateOfBirth = dateofbirth;
	}
	
	public void setPhoneNumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setInsuranceID(int insuranceid) {
		this.insuranceID = insuranceid;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public int getInsuranceID() {
		return this.insuranceID;
	}
	
	public String getPaitentID() {
		return this.patientID;
	}
}
