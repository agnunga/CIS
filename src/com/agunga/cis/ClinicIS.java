package com.agunga.cis;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by agunga on 1/18/17.
 */
public class ClinicIS {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws IOException{
//        MyUtility.myPrintln("PERSON EXIST T/F: "+new Receptionist().personExists(142536));
        System.out.println(" Welcome");
        controlFlow();
    }

    public static void controlFlow(){
        String x= checkForRole();
        promptRole(x);
    }

    public static String  checkForRole () {
        System.out.println("Main menu: Choose Role ");
        System.out.println(" 1. for Receptionist \n 2. for Lab tech \n 3. for Doctor \n 4. for Nurse \n 0. to exit");

        String role = scanner.next();
        return role;
    }

    public static void promptRole(String role) {

        backAndMenu(role);

        switch (role) {
            case "1": {
                receptionistRole();
                break;
            }
            case "2": {
                labTechRole();
                break;
            }
            case "3": {
                doctorRole();
                break;
            }
            case "4": {
                nurseRole();
                break;
            }
            default: {
                System.out.println("Invalid option. Try again");
                controlFlow();
            }
        }
    }

    public static void receptionistRole(){
        Receptionist receptionist = new Receptionist();
        /*Confirm if the receptionist exists, if not, the receptionist registers first before performing any operation*/
        System.out.print("Hi Receptionist, Confirm your details. ");
        receptionist.registerReceptionist(receptionist);
        System.out.println("Hi "+receptionist.getName()+", Choose a course of action: ");
        System.out.println("1: Register patient \t 2: View patient record \t 9: for main menu \t 0: to exit");

        String task = scanner.next();

        backAndMenu(task);

        switch (task) {
            case "1": {
                String i;
                do {
                    /*Add new patient*/
                    System.out.print("Register patient. ");
                    receptionist.registerPatient(receptionist);
                    System.out.println("Press 1 to register a new patient, 0 to exit this");
                    i=MyUtility.myScanner().next();
                }while (i.equals("1"));
                promptRole("1");
                break;
            }
            case "2": {
                System.out.println("View patient details");
                String i = "1";
                do {
                    switch (i){
                        case "0":{
                            promptRole("1");
                            break;
                        }
                        case "1":{
                            receptionist.viewPatientDetails();
                            break;
                        }
                        case "2":{
                            MyUtility.myPrintln("Update patient's record");
                            break;
                        }
                        case "3":{
                            MyUtility.myPrintln("Delete patient's record");
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                    System.out.println("Press 1. to view another patient's details, 2. to update, 3. to delete 0. to quit");
                    i=MyUtility.myScanner().next();
                }while (i!="");
                promptRole("1");
                break;
            }
            default: {
                System.out.println("Invalid option. Try again");
                promptRole("1");
            }
        }
    }


    public static void doctorRole(){
        Doctor doctor = new Doctor();
        System.out.println("Hi Doctor, Choose a course of action: ");
        System.out.println("1. Diagnosis \t 2. Prescription \t 9: for main menu \t 0. to exit");
        String task = (scanner.next());

        backAndMenu(task);

        switch (task) {
            case "1": {
                System.out.println(" Diagnosis ");
                int i;
                do {
                    doctor.recordDiagnosis();
                    System.out.println("Press 1. to Diagnose another patient, 0. to quit this");
                    i=MyUtility.myScanner().nextInt();
                }while (i!=0);
                promptRole("3");
                break;
            }
            case "2": {
                System.out.println("Prescription");
                int i;
                do {
                    doctor.recordPrescription();
                    System.out.println("Press 1. to Prescribe another patient, 0. to quit this");
                    i=MyUtility.myScanner().nextInt();
                }while (i!=0);
                promptRole("3");
                break;
            }
            default: {
                System.out.println("Invalid option. Try again");
                promptRole("3");
            }
        }
    }

    public static void nurseRole(){
        System.out.println("Hi Nurse, Choose a course of action: ");
        System.out.println("1. Dispatch drugs \t 9: for main menu \t 0. to exit");// 	 2. Write report
        String task = scanner.next();

        backAndMenu(task);

        switch (task) {
            case "1": {
                System.out.println("Drugs catalogue");
                Nurse nurse = new Nurse();
                int i;
                do {
                    nurse.dispatchDrugs();
                    System.out.println("Press 1. to record another patient's drugs, 0. to quit this");
                    i=MyUtility.myScanner().nextInt();
                }while (i!=0);
                promptRole("4");
                break;
            }
//            case "2": {
//                System.out.println(" patient report");
//                break;
//            }
            default: {
                System.out.println("Invalid option. Try again");
                promptRole("4");
            }
        }

    }

    public static void labTechRole(){
        System.out.println("Hi Labtech, Choose a course of action: ");
        System.out.println("1. Test patient \t 9: for main menu 0. to exit");
        String task = scanner.next();

        backAndMenu(task);

        switch (task) {
            case "1": {
                int i;
                do {
                    Labtech labtech = new Labtech();
                    labtech.recordTestResults();
                    System.out.println("Press 1. to test another patient, 0. to quit this");
                    i=MyUtility.myScanner().nextInt();
                }while (i!=0);
                promptRole("2");
                break;
            }
            default: {
                System.out.println("Invalid option. Try again");
                promptRole("2");
            }
        }

    }

    private static void backAndMenu(String task){
        switch (task) {
            case "9":{
                controlFlow();
                break;
            }
            case "0": {
                System.out.println("Exiting...");
                System.exit(0);
                break;
            }
        }
    }
}
