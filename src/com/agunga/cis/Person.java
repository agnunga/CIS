package com.agunga.cis;

import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by agunga on 1/18/17.
 */
public class Person {
    public static Connection connection = null;
    private int nationalId;
    private String name;
    private String phone;
    private String sex;
    private String dob;

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPersonDetails(int id, String name, String phone, String sex, String dob){
        setNationalId(id);
        setName(name);
        setPhone(phone);
        setSex(sex);
        setDob(dob);
    }

    public Person() {
        Scanner scanner = new Scanner(System.in);
/*
//Using Scanner to take the patients details as a person.
        System.out.print("Enter the com.agunga.cis.Person's National ID No: ");
        setNationalId(scanner.nextInt());

        System.out.print("Enter the com.agunga.cis.Person's Name: ");
        setName(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's Phone Number: ");
        setPhone(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's sex: ");
        setSex(scanner.next());

        System.out.print("Enter the com.agunga.cis.Person's Date of birth: ");
        setDob(scanner.next());
*/
    }

    public boolean checkPerson(int nationalId){
        boolean exists= false;
        String sql_select = "SELECT " +
                " nationalid, name " +
                " FROM persons" +
                " WHERE nationalid = "+nationalId+"";

        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = DbUtil.select(sql_select);
        try {
            while(resultSet.next()){
                exists = true;
                setNationalId(resultSet.getInt(1));
                setName(resultSet.getString(2));
                System.out.println();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void registerPerson(Person person){
        connection = DbUtil.connectDB(DbType.MYSQL);

        System.out.print("Enter National ID: ");
        person.setNationalId(MyUtility.scanInt());
        if(checkPerson(person.getNationalId())){
            System.out.print("Person exists. ");
        }else {
            System.out.print("Enter Name: ");
            person.setName(MyUtility.myScanner().next());

            System.out.print("Enter Phone Number: ");
            person.setPhone(MyUtility.myScanner().next());

            System.out.print("Enter sex: ");
            person.setSex(MyUtility.myScanner().next());

            System.out.print("Enter Date of birth: ");
            person.setDob(MyUtility.myScanner().next());

            String sql_insert_person = "INSERT INTO persons "
                    + "(id, nationalid, name, dob, phone, sex) "
                    + "VALUES "
                    + "(NULL, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql_insert_person);
                preparedStatement.setLong(1, person.getNationalId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getDob());
                preparedStatement.setString(4, person.getPhone());
                preparedStatement.setString(5, person.getSex());

                if (DbUtil.insert(preparedStatement) > 0) System.out.print("Person registered. ");
                else System.err.print("Person registration failed. ");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPersonDetails(){
        return String.format("ID: %d, Name: %s, Pnone: %s, Sex: %s", getNationalId(), getName(), getPhone(), getSex(), getDob());
    }
}
