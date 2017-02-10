package com.agunga.cis;

import com.agunga.db.DbType;
import com.agunga.db.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by agunga on 1/18/17.
 */
public class Nurse extends Employee {
    private static String persons_table = "persons";
    private static String patients_table = "patients";
    public static Connection connection = null;

    @Override
    void work() {

    }

    public void dispatchDrugs(){
        connection = DbUtil.connectDB(DbType.MYSQL);
        Patient patient = new Patient();
        System.out.print("Enter patient's National ID: ");
        patient.setNationalId(MyUtility.myScanner().nextInt());

        System.out.print("Enter the drugs dispatched to patient: ");
        patient.setDiagnosis(MyUtility.myScanner().nextLine());

        String sql_update = "UPDATE "+patients_table+" " +
                " SET drugs = ?, checkout = CURRENT_TIMESTAMP " +
                " WHERE nationalid = ?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql_update);
            preparedStatement.setString(1, patient.getDiagnosis());
            preparedStatement.setInt(2, patient.getNationalId());

            if(DbUtil.update(sql_update, preparedStatement)>0)System.out.println("Dispatched drugs recorded.");
            else System.out.println("Failed to record dispatched drugs.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
