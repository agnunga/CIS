package com.agunga.cis;

/**
 * Created by agunga on 1/18/17.
 */
abstract public class Employee extends Person {
    private String emp_no;
    private String date_employed;
    private String salary;
    private String title;


    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getDate_employed() {
        return date_employed;
    }

    public void setDate_employed(String date_employed) {
        this.date_employed = date_employed;
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

}
