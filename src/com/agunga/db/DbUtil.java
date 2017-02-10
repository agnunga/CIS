package com.agunga.db;

import com.agunga.cis.MyUtility;

import java.sql.*;

/**
 * Created by agunga on 2/2/2017.
 */
public class DbUtil {
    public static Connection connection;
    private static final String O_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String O_USER = "SYSTEM";
    private static final String O_PASSWORD = "pwd";

    private static final String M_STRING = "jdbc:mysql://localhost/CIS";
    private static final String M_USER = "root";
    private static final String M_PASSWORD = "";

    public static Connection connectDB(DbType dbType){
        Connection connection = null;
        try{
            switch (dbType){
                case MYSQL:{
                    return  DriverManager.getConnection(M_STRING, M_USER, M_PASSWORD);
                }
                case ORACLE:{
                    return  DriverManager.getConnection(O_STRING, O_USER, O_PASSWORD);
                }
                default:
                    return null;
            }
        }catch(SQLException e){
            System.out.println("Connection failed: "+e.getMessage());
            return null;
        }
    }


    public static void createTable(String sql_create_table, String table_name) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql_create_table);
            preparedStatement.executeUpdate();
            System.out.println(" Table created");
            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
                //				System.err.println("Rolled back.");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            //			System.err.println(e.getMessage());
            if(e.getErrorCode() == 955){
                System.out.println("Table name already in use. Enter 1 to overwrite the table, 0 to exit");
                int action = MyUtility.myScanner().nextInt();
                if(action == 1){
                    PreparedStatement preparedStatement;
                    try {
                        preparedStatement = connection.prepareStatement("DROP TABLE "+table_name);
                        preparedStatement.executeUpdate();
                        System.out.println("Table droped, recreating the table..");
                        createTable(sql_create_table, table_name);

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        }
    }

    public static int insert(PreparedStatement preparedStatement) {
        int isInserted = 0;
        try {
            isInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            if(e.getErrorCode() == 1062)
                MyUtility.myPrintln("Patient will not be added. Record exists.");
        }
        System.out.println("isInserted =  "+isInserted);
        return isInserted;
    }

      public static ResultSet select(String sql_select) {
        connection = DbUtil.connectDB(DbType.MYSQL);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_select);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static int update(String sql_update, PreparedStatement preparedStatement) {
        int isUpdated = -1;
        try {
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }


    public static boolean delete(String sql_delete, int id) {
        boolean isDeleted = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_delete);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

}
