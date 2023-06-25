package sealey.javafxdesktopschedulingapp.helpers;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * Description: Connect to MySql database
 * Note to evaluator: if timezone issue, may need to comment out 16 and uncomment 17. Did not use VM
 * */
public abstract class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    //private static final String jdbcUrl = protocol + vendor + location + databaseName + "?serverTimeZone = UTC";
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    /**
     * Gets connection to the database
     * */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Successfully connected to database.");
        }
        catch(Exception e)
        {
            System.out.println("Error connecting to database:" + e.getMessage());
        }
    }

    /**
     * Closes connection to the database
     * */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Database connection successfully closed.");
        }
        catch(Exception e)
        {
            System.out.println("Error closing database connection:" + e.getMessage());
        }
    }
}
