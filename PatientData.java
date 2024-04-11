package application;

public class PatientData {
	private String patientId;
    private String height;
    private String weight;
    private String bodyTemp;
    private String bloodPressureSys;
    private String bloodPressureDia;
    private String immunizationRecord;
    private String healthIssues;
    private String prescriptions;
    private String allergies;
    private String healthConcerns;
    private String pharmacyId;
    private String insuranceId;
    private String recommendations;
    
    public PatientData() {
    	
    }
    
    public PatientData(String patientId, String height, String weight, String bodyTemp, String bloodPressureSys,
            String bloodPressureDia, String immunizationRecord, String healthIssues, String prescriptions, String allergies,
            String healthConcerns, String pharmacyId, String insuranceId, String recommendations) {
		this.setPatientId(patientId);
		this.setHeight(height);
		this.setWeight(weight);
		this.setBodyTemp(bodyTemp);
		this.setBloodPressureSys(bloodPressureSys);
		this.setBloodPressureDia(bloodPressureDia);
		this.setImmunizationRecord(immunizationRecord);
		this.setHealthIssues(healthIssues);
		this.setPrescriptions(prescriptions);
		this.setAllergies(allergies);
		this.setHealthConcerns(healthConcerns);
		this.setPharmacyId(pharmacyId);
		this.setInsuranceId(insuranceId);
		this.setRecommendations(recommendations);
    }

	

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(String bodyTemp) {
		this.bodyTemp = bodyTemp;
	}

	public String getBloodPressureSys() {
		return bloodPressureSys;
	}

	public void setBloodPressureSys(String bloodPressureSys) {
		this.bloodPressureSys = bloodPressureSys;
	}
	
	public String getBloodPressureDia() {
		return bloodPressureDia;
	}

	public void setBloodPressureDia(String bloodPressureDia) {
		this.bloodPressureDia = bloodPressureDia;
	}

	public String getImmunizationRecord() {
		return immunizationRecord;
	}

	public void setImmunizationRecord(String immunizationRecord) {
		this.immunizationRecord = immunizationRecord;
	}

	public String getHealthIssues() {
		return healthIssues;
	}

	public void setHealthIssues(String healthIssues) {
		this.healthIssues = healthIssues;
	}

	public String getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(String prescriptions) {
		this.prescriptions = prescriptions;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getHealthConcerns() {
		return healthConcerns;
	}

	public void setHealthConcerns(String healthConcerns) {
		this.healthConcerns = healthConcerns;
	}

	public String getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
    
    
}
