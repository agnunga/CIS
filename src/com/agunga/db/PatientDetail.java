package com.agunga.db;

import java.sql.PreparedStatement;

/**
 * Created by agunga on 06-Feb-17.
 */
public class PatientDetail {

    public void addPatient(){

        if (DbUtil.connectDB(DbType.ORACLE)!=null){
            System.out.println("Connected to oracle DB.");
        }

    }

    public static void fetchData(){
        PreparedStatement statement;
    }
}
