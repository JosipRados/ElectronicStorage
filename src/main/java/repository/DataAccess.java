package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccess {
    private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String jdbcURL = "jdbc:sqlserver://localhost:1433;databasename=Electronic_Storage;username=test;password=test01;trustServerCertificate=true;";
    public static Connection databaseConnection = null;

    public static void ConnectToDatabase(){

        try
        {
            Class.forName(jdbcDriver).newInstance();
            System.out.println("JDBC driver loaded");
        }
        catch (Exception err)
        {
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        }


        try {
            databaseConnection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to the database");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
