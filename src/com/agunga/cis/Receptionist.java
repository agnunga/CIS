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
    private static String table_name = "a_employee";
    public static Connection connection = null;

    public boolean personExists(int nationalId){
        boolean exists= false;
        Patient patient = new Patient();
        String sql_select = "SELECT " +
                " persons.id, patients.id, " +
                " persons.nationalid, patientid, name, persons.phone, persons.dob, persons.sex, " +
                " patients.bloodtype, patients.checkin, patients.checkout, patients.addedby " +
                " FROM persons JOIN patients " +
                " ON persons.nationalid  = patients.nationalid" +
                " WHERE persons.nationalid = "+nationalId+" ORDER BY patientid ASC LIMIT 1";

        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = DbUtil.select(sql_select);
        try {
//            System.out.println("This person's details exist in our system.");
            while(resultSet.next()){
                exists = true;

                patient.setNationalId(resultSet.getInt("nationalid"));
                patient.setPatientId(resultSet.getString("patientid"));

//                System.out.print(resultSet.getInt(1)+"\t");
//                System.out.print(resultSet.getString(2)+"\t");
                System.out.print(resultSet.getString(3)+"\t");
                System.out.print(resultSet.getString(4)+"\t");
                System.out.print(resultSet.getString(5)+"\t");
                System.out.print(resultSet.getString(6)+"\t");
                System.out.print(resultSet.getString(7)+"\t");
                System.out.print(resultSet.getString(8)+"\t");
                System.out.print(resultSet.getString(9)+"\t");
                System.out.print(resultSet.getString(10)+"\t");
                System.out.print(resultSet.getString(11)+"\t");
                System.out.print(resultSet.getString(12)+"\t");
                System.out.println();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void registerPatient(){
        Patient patient = new Patient();
        connection = DbUtil.connectDB(DbType.MYSQL);

        System.out.print("\nAdd new Patient Details \n");
        System.out.print("Enter National ID: ");
        patient.setNationalId(MyUtility.scanInt());

        if(new Receptionist().personExists(patient.getNationalId())){
            MyUtility.myPrintln("Person's details already exist, confirm details above. 1 to edit, 0 to exit this.");
            int action = MyUtility.scanInt();
            switch (action){
                case 1:{
                    System.out.print("Calling edit method goes here.");
                    break;
                }
                case 0:{
                    break;
                }
            }
        }else {
            System.out.print("Enter Name: ");
            patient.setName(MyUtility.myScanner().next());

            System.out.print("Enter Phone Number: ");
            patient.setPhone(MyUtility.myScanner().next());

            System.out.print("Enter sex: ");
            patient.setSex(MyUtility.myScanner().next());

            System.out.print("Enter Date of birth: ");
            patient.setDob(MyUtility.myScanner().next());

            //Using com.agunga.cis.MyUtility.myScanner() to feed in patient Specidic details
            System.out.print("Assign patient an ID: ");
            patient.setPatientId(MyUtility.myScanner().next());

            String sql_insert_person = "INSERT INTO persons "
                    + "(id, nationalid, name, dob, phone, sex) "
                    + "VALUES "
                    + "(NULL, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql_insert_person);
                preparedStatement.setLong(1, patient.getNationalId());
                preparedStatement.setString(2, patient.getName());
                preparedStatement.setString(3, patient.getDob());
                preparedStatement.setString(4, patient.getPhone());
                preparedStatement.setString(5, patient.getSex());

                if(DbUtil.insert(preparedStatement) > 0)System.out.println("Person registered.");
                else System.out.println("Person registration Failed.");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        String sql_insert_patient = "INSERT INTO patients " +
                "(nationalid, patientid, checkin, addedby) " +
                "VALUES " +
                "(?, ?, CURRENT_TIMESTAMP, ?)";

        PreparedStatement preparedStatement2;
        try {

            preparedStatement2 = connection.prepareStatement(sql_insert_patient);
            preparedStatement2.setInt(1, patient.getNationalId());
            preparedStatement2.setString(2, patient.getPatientId());
            preparedStatement2.setString(3, "REC01");

            if(DbUtil.insert(preparedStatement2) > 0)System.out.println("Patient registered.");
            else System.out.println("Patient registration Failed.");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void viewPatient() {
        System.out.print("To view patient's details\n");
        System.out.print("Enter the ID Number: ");
        long id= MyUtility.scanInt();
        String sql_select = "SELECT " +
                " person.id, patient.id, " +
                " person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, " +
                " patient.bloodtype, patient.weight, patient.diagnosis, " +
                " patient.prescription, patient.drugs, patient.checkin, patient.checkout, patient.addedby " +
                " FROM persons person, patients patient " +
                " WHERE patient.nationalid = person.nationalid " +
                " AND patient.nationalid = "+id+"";

        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = DbUtil.select(sql_select);

        try {

            System.out.println("Patient's details are:");

            while (resultSet.next()) {
//                System.out.print(resultSet.getInt(1)+"\t");
//                System.out.print(resultSet.getString(2)+"\t");
                System.out.print(resultSet.getString(3) + "\t");
                System.out.print(resultSet.getString(4) + "\t");
                System.out.print(resultSet.getString(5) + "\t");
                System.out.print(resultSet.getString(6) + "\t");
                System.out.print(resultSet.getString(7) + "\t");
                System.out.print(resultSet.getString(8) + "\t");
                System.out.print(resultSet.getString(9) + "\t");
                System.out.print(resultSet.getString(10) + "\t");
                System.out.print(resultSet.getString(11) + "\t");
                System.out.print(resultSet.getString(12) + "\t");
                System.out.print(resultSet.getString(13) + "\t");
                System.out.print(resultSet.getString(14) + "\t");
                System.out.print(resultSet.getString(15) + "\t");
                System.out.print(resultSet.getString(16) + "\t");
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

    @Override
    void work() {
        Receptionist r = new Receptionist();
        r.registerPatient();

    }
}
