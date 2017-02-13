package com.agunga.cis;

import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
public class Receptionist extends Employee {
    private static String persons_table = "persons";
    private static String patients_table = "patients";
    public static Connection connection = null;

    private String assignment;

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public boolean checkReceptionist(Receptionist receptionist){
        connection = DbUtil.connectDB(DbType.MYSQL);
        boolean exists = false;
        String sql = "SELECT employeeno, assignment " +
                " FROM receptionists " +
                " WHERE employeeno = '"+receptionist.getEmployeeNo()+"'";
        ResultSet resultSet = DbUtil.select(sql);
        try {
            while (resultSet.next()){
                exists = true;
                receptionist.setEmployeeNo(resultSet.getString(1));
                receptionist.setAssignment(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void registerReceptionist(Receptionist receptionist){
        connection = DbUtil.connectDB(DbType.MYSQL);
//        Receptionist receptionist = new Receptionist();
        registerEmployee(receptionist);
        if(checkReceptionist(receptionist)){
            System.out.print("Receptionist exists. Assignment: "+getAssignment()+". ");
        }else {
            System.out.print("Enter receptionist's assignment office: ");
            receptionist.setAssignment(MyUtility.myScanner().nextLine());

            String sql = "INSERT INTO receptionists " +
                    " (employeeno, assignment, dateassigned) " +
                    " VALUES(?, ?, CURRENT_TIMESTAMP )";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, receptionist.getEmployeeNo());
                preparedStatement.setString(2, receptionist.getAssignment());
                if(DbUtil.insert(preparedStatement)>0){
                    System.out.print("Receptionist added. ");
                }else {
                    System.err.print("Failed to add receptionist. ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerPatient(Receptionist receptionist){
        connection = DbUtil.connectDB(DbType.MYSQL);
        Patient patient = new Patient();
        registerPerson(patient);

        if(patient.patientExists(patient.getNationalId())){
            System.out.print("Patient exists, "+patient.getName()+
                    "("+patient.getNationalId()+")" +
                    ", Last checkin: "+patient.getCheckin()+". ");
        }else{
            System.out.print("Assign the Patient a Patient ID: ");
            patient.setPatientId(MyUtility.myScanner().next());
        }

        String sql_insert_patient = "INSERT INTO patients " +
                " (nationalid, patientid, checkin, addedby) " +
                " VALUES " +
                " (?, ?, CURRENT_TIMESTAMP, ?)";

        PreparedStatement preparedStatement2;
        try {
            preparedStatement2 = connection.prepareStatement(sql_insert_patient);
            preparedStatement2.setInt(1, patient.getNationalId());
            preparedStatement2.setString(2, patient.getPatientId());
            preparedStatement2.setString(3, receptionist.getEmployeeNo());

            if(DbUtil.insert(preparedStatement2) > 0)System.out.println("Patient's new check-in registered. ");
            else System.err.println("Patient new checkin registration Failed. ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void viewPatientDetails() {
//        System.out.print("To view patient's details\n");
        Patient patient = new Patient();
        System.out.print("Enter the national ID Number: ");
        patient.setNationalId(MyUtility.myScanInt());
        String sql_select = "SELECT " +
                " person.id, patient.id, " +
                " person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, " +
                " patient.checkin, patient.checkout, patient.addedby " +
                " FROM persons person, patients patient " +
                " WHERE patient.nationalid = person.nationalid " +
                " AND patient.nationalid = "+patient.getNationalId()+" ORDER BY checkin DESC LIMIT 5";

        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = DbUtil.select(sql_select);

        try {
            System.out.println("Patient's history is: ");
            while (resultSet.next()) {
//                System.out.print(resultSet.getInt(1)+"\t");
//                System.out.print(resultSet.getString(2)+"\t");
//                System.out.print(resultSet.getString(3) + "\t");
                System.out.print(resultSet.getString(4) + "\t");
                System.out.print(resultSet.getString(5) + "\t");
                System.out.print(resultSet.getString(6) + "\t");
                System.out.print(resultSet.getString(7) + "\t");
                System.out.print(resultSet.getString(8) + "\t");
                System.out.print(resultSet.getString(9) + "\t");
                System.out.println();
            }
            System.out.println();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updatePatintDetails(Patient patient){
        viewPatientDetails();

        String sql_update = "UPDATE "+persons_table+" " +
                " SET name = ?, dob = ?, phone = ?, sex= ? " +
                " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getDob());
            preparedStatement.setString(3, patient.getPhone());
            preparedStatement.setString(4, patient.getSex());
            preparedStatement.setInt(5, patient.getNationalId());

            if(DbUtil.update(sql_update, preparedStatement)> 0)System.out.println("Employee deleted.");
            else System.out.println("Failed to delete employee.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Details After Updating.");
        viewPatientDetails();
    }

    @Override
    void work() {

    }
}
