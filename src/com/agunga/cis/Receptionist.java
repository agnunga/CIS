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

    public void registerReceptionist(){
        connection = DbUtil.connectDB(DbType.MYSQL);
        Receptionist receptionist = new Receptionist();

        System.out.print("Dear Receptionist, enter your National ID: ");
        receptionist.setNationalId(MyUtility.scanInt());
        if(checkPerson(receptionist.getNationalId())){

        }else {
            System.out.print("Enter Name: ");
            receptionist.setName(MyUtility.myScanner().next());

            System.out.print("Enter Phone Number: ");
            receptionist.setPhone(MyUtility.myScanner().next());

            System.out.print("Enter sex: ");
            receptionist.setSex(MyUtility.myScanner().next());

            System.out.print("Enter Date of birth: ");
            receptionist.setDob(MyUtility.myScanner().next());

            String sql_insert_person = "INSERT INTO persons "
                    + "(id, nationalid, name, dob, phone, sex) "
                    + "VALUES "
                    + "(NULL, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql_insert_person);
                preparedStatement.setLong(1, receptionist.getNationalId());
                preparedStatement.setString(2, receptionist.getName());
                preparedStatement.setString(3, receptionist.getDob());
                preparedStatement.setString(4, receptionist.getPhone());
                preparedStatement.setString(5, receptionist.getSex());

                if (DbUtil.insert(preparedStatement) > 0) System.out.println("Person registered.");
                else System.out.println("Person registration Failed.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean checkPerson(int nationalId){
        boolean exists= false;
        Patient patient = new Patient();
        String sql_select = "SELECT " +
                " nationalid, name, phone, dob, sex " +
                " FROM persons " +
                " WHERE persons.nationalid = "+nationalId+"";

        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = DbUtil.select(sql_select);
        try {
            while(resultSet.next()){
                exists = true;
                setNationalId(resultSet.getInt(1));
                setName(resultSet.getString(2));

//                System.out.print(resultSet.getInt(1)+"\t");
//                System.out.print(resultSet.getString(2)+"\t");
//                System.out.print(resultSet.getString(3)+"\t");
//                System.out.print(resultSet.getString(4)+"\t");
//                System.out.print(resultSet.getString(5)+"\t");
                System.out.println();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


    public boolean personExists(int nationalId){
        boolean exists= false;
        Patient patient = new Patient();
        String sql_select = "SELECT " +
                " persons.id, patients.id, " +
                " persons.nationalid, patientid, name, persons.phone, persons.dob, persons.sex, " +
                " patients.checkin, patients.checkout, patients.addedby " +
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
                " (nationalid, patientid, checkin, addedby) " +
                " VALUES " +
                " (?, ?, CURRENT_TIMESTAMP, ?)";

        PreparedStatement preparedStatement2;
        try {

            preparedStatement2 = connection.prepareStatement(sql_insert_patient);
            preparedStatement2.setInt(1, patient.getNationalId());
            preparedStatement2.setString(2, patient.getPatientId());
            preparedStatement2.setInt(3, getNationalId());

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

    public void viewPatientDetails() {
//        System.out.print("To view patient's details\n");
        System.out.print("Enter the national ID Number: ");
        int nationalId= MyUtility.scanInt();
        String sql_select = "SELECT " +
                " person.id, patient.id, " +
                " person.nationalid, patient.patientid, person.name, person.phone, person.dob, person.sex, " +
                " patient.checkin, patient.checkout, patient.addedby " +
                " FROM persons person, patients patient " +
                " WHERE patient.nationalid = person.nationalid " +
                " AND patient.nationalid = "+nationalId+"";

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
        Receptionist r = new Receptionist();
        r.registerPatient();

    }
}
