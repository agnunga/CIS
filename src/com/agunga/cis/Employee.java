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
abstract public class Employee extends Person {
    private String employeeNo;
    private String dateEmployed;
    private String salary;
    private String title;

    Connection connection = null;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(String dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    abstract void work();

    public  void createEployeeTable(){
        String sql = "create table employees(id int(11) AUTO_INCREMENT PRIMARY KEY, " +
                " nationalid int(8) NOT NULL, " +
                " employeeno varchar(15) NOT NULL, " +
                " salary varchar(12) NOT NULL, " +
                " title varchar(255));";
        DbUtil.createTable(sql, "employees");
    }

    public boolean checkEmployee(int nationalId){
        connection = DbUtil.connectDB(DbType.MYSQL);

        boolean exists = false;
        String sql = "SELECT employeeno " +
                " FROM employees " +
                " WHERE nationalid = "+nationalId+";";
        ResultSet resultSet = DbUtil.select(sql);
        try {
            while (resultSet.next()){
                exists = true;
                setEmployeeNo(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void registerEmployee(Employee employee) {
        connection = DbUtil.connectDB(DbType.MYSQL);
        super.registerPerson(employee);
        if (checkEmployee(employee.getNationalId())) {
            System.out.print("Employee Exists. ");
        } else {
            System.out.print("Enter Employee Number: ");
            employee.setEmployeeNo(MyUtility.myScanner().next());

            System.out.print("Enter Date Employed: ");
            employee.setDateEmployed(MyUtility.myScanner().next());

            System.out.print("Enter Salary: ");
            employee.setSalary(MyUtility.myScanner().next());

            System.out.print("Enter Job Title: ");
            employee.setTitle(MyUtility.myScanner().next());

            String sql = "INSERT INTO employees " +
                    " (nationalid, employeeno, dateemployed, salary, title) " +
                    " VALUES(?, ?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, employee.getNationalId());
                preparedStatement.setString(2, employee.getEmployeeNo());
                preparedStatement.setString(3, employee.getDateEmployed());
                preparedStatement.setString(4, employee.getSalary());
                preparedStatement.setString(5, employee.getTitle());

                if(DbUtil.insert(preparedStatement)>0){
                    System.out.print("Employee registered. ");
                }else {
                    System.err.print("Employee registration failed. ");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
