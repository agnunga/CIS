package com.agunga.cis;

import java.util.Scanner;

/**
 * Created by agunga on 1/18/17.
 */
public class Person {

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

//    public String getPersonDetails(){
//
//        return String.format("ID: %d, Name: %s, Pnone: %s, Sex: %s", getNationalId(), getName(), getPhone(), getSex(), getDob());
//    }
}
