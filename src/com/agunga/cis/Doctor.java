package com.agunga.cis;

import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
public class Doctor extends Employee {
    private static String persons_table = "persons";
    private static String patients_table = "patients";

    public static Connection connection = null;
    private String licence_no;
    private String speciality;

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    void work() {
        recordDiagnosis();
    }

    public void recordDiagnosis(){
        connection = DbUtil.connectDB(DbType.MYSQL);
        Patient patient = new Patient();
        System.out.print("Enter patient's National ID: ");
        patient.setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the patient's diagnosis: ");
        patient.setDiagnosis(MyUtility.myScanner().nextLine());

        String sql_update = "UPDATE "+patients_table+" " +
                " SET diagnosis = ? " +
                " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setInt(2, patient.getNationalId());

            if(DbUtil.update(sql_update, preparedStatement)>0)System.out.println("Diagnosis recorded.");
            else System.out.println("Failed to record diagnosis.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordPrescription(){
        connection = DbUtil.connectDB(DbType.MYSQL);
        Patient patient = new Patient();
        System.out.print("Enter patient's National ID: ");
        patient.setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the patient's prescription: ");
        patient.setDiagnosis(MyUtility.myScanner().nextLine());

        String sql_update = "UPDATE "+patients_table+" " +
                " SET prescription = ? " +
                " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setInt(2, patient.getNationalId());

            if(DbUtil.update(sql_update, preparedStatement)>0)System.out.println("Prescription recorded.");
            else System.out.println("Failed to record prescription.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createEmployeeTable(){
//        String sql_create_table = "create table "+table_name
//                + "( "
//                + "id NUMBER(11) NOT NULL, "
//                + "emp_no VARCHAR2(12) NOT NULL, "
//                + "date_employed VARCHAR2(15) NOT NULL, "
//                + "salary VARCHAR2(12) NOT NULL, "//title
//                + "title VARCHAR2(30) NOT NULL "//
//                + ") ";
//
//        DbUtil.createTable(sql_create_table, table_name);

    }

    public void addEmployee(){

//        String sql_insert = "INSERT INTO "+table_name
//                + " (emp_no, date_employed, salary, title ) "
//                + " VALUES(?, ?, ?, ?)";
////        Employee employee = new Employee("1425", "25/07/2014", "8900", "Sineor developer pro");
////
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql_insert);
////            preparedStatement.setString(2, employee.getEmp_no());
////            preparedStatement.setString(3, employee.getDate_employed());
////            preparedStatement.setString(4, employee.getSalary());
////            preparedStatement.setString(5, employee.getTitle());
//
//            if(DbUtil.insert(preparedStatement) >= 0)System.out.println("Employee added.");
//            else System.out.println("Failed to add employee.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    public void viewEmployee(){
//        String sql_select = "SELECT id, emp_no, date_employed, salary, title "
//                + " FROM "+table_name;
//        ResultSet resultSet = DbUtil.select(sql_select);
//
//        try {
//
//            System.out.println("Employee details are:");
//
//            while(resultSet.next()){
//                System.out.print(resultSet.getInt(1)+"\t");
//                System.out.print(resultSet.getString(2)+"\t");
//                System.out.print(resultSet.getString(3)+"\t");
//                System.out.print(resultSet.getString(4)+"\t");
//                System.out.println(resultSet.getString(5)+"\t");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void updateEmployee(){
//        String sql_update = "UPDATE "+table_name+" SET emp_no = ?, date_employed = ?, salary = ?, title= ? WHERE id = ?";
//
//        System.out.println("Details Before Updating.");
//        viewEmployee();
//
//        System.out.print("Enter id of column you want to update: ");
//        int id  =  MyUtility.myScanner().nextInt();
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement(sql_update);
//            preparedStatement.setString(1, "111");
//            preparedStatement.setString(2, "10/12/205");
//            preparedStatement.setString(3, "8320");
//            preparedStatement.setString(4, "Software Engineer XXX");
//            preparedStatement.setInt(5, id);
//
//            if(DbUtil.update(sql_update, preparedStatement))System.out.println("Employee deleted.");
//            else System.out.println("Failed to delete employee.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Details After Updating.");
//        viewEmployee();
    }

    public void deleteEmployee(){

//        System.out.println("Details Before Deleting.");
//        viewEmployee();
//
//        String sql_delete = "DELETE FROM "+table_name+" WHERE id = ?";
//        System.out.print("Enter id of column you want to delete: ");
//        int id  = MyUtility.myScanner().nextInt();
//
//        if(DbUtil.delete(sql_delete, id))System.out.println("Employee deleted.");
//        else System.out.println("Failed to delete employee.");
//
//        System.out.println("Details After Deleting.");
//        viewEmployee();

    }

}
