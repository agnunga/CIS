package com.agunga.cis;

/**
 * Created by agunga on 1/18/17.
 */
public class Patient extends Person {
    private String patientId;
    private String checkin;
    private String checkout;
    private String weight;
    private String diagnosis;
    private String blood_type;
    private String prescription;
    private String drugs;


    public Patient() {
    }

    public Patient(String role) {
        switch (role){
            case "1":{
                add();
                break;
            }
            case "2":{
                test();
                break;
            }
            case "3":{
                diagnose();
                break;
            }
            case "4":{
                dispatchDrugs();
                break;
            }
            case "5":{
                prescribe();
                break;
            }
            default:{
                break;
            }
        }
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public void add(){
        //Using com.agunga.cis.MyUtility.myScanner() to take the patients details as a person.

        System.out.print("\nAdd new Patient Details \n");
        System.out.print("Enter PatientDetail's National ID: ");
        setNationalId(MyUtility.scanInt());

        System.out.print("Enter the PatientDetail's Name: ");
        setName(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's Phone Number: ");
        setPhone(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's sex: ");
        setSex(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's Date of birth: ");
        setDob(MyUtility.myScanner().next());

        //Using com.agunga.cis.MyUtility.myScanner() to feed in patient Specidic details
        System.out.print("Enter the PatientDetail's ID: ");
        setPatientId(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's check in time: ");
        setCheckin(MyUtility.myScanner().next());
    }

    public void diagnose(){
        System.out.print("Enter PatientDetail's National ID: ");
        setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the PatientDetail's diagnosis: ");
        setDiagnosis(MyUtility.myScanner().nextLine());
    }

    public void test(){
        System.out.print("Enter PatientDetail's National ID: ");
        setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the PatientDetail's Blood type: ");
        setBlood_type(MyUtility.myScanner().next());

        System.out.print("Enter the PatientDetail's weight: ");
        setWeight(MyUtility.myScanner().next());
    }

    public void prescribe(){
        System.out.print("Enter PatientDetail's National ID: ");
        setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the PatientDetail's prescription: ");
        setPrescription(MyUtility.myScanner().nextLine());
    }

    public void dispatchDrugs(){
        System.out.print("Enter PatientDetail's National ID: ");
        setNationalId(MyUtility.scanInt());

        System.out.print("Record drugs dispatched to the parient: ");
        setDrugs(MyUtility.myScanner().nextLine());
    }
}
