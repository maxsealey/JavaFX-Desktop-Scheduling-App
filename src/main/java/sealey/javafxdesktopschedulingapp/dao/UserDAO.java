package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class UserDAO {
    private static ObservableList<User> userList = FXCollections.observableArrayList();

    /**
     * Populates list of users from data in database
     * */
    private static void populateUserList() throws SQLException {
        String sql = "SELECT User_ID, User_Name, Password FROM client_schedule.users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            int userid = results.getInt("User_ID");
            String username = results.getString("User_Name");
            String password = results.getString("Password");
            userList.add(new User(userid, username, password));
        }
    }

    /**
     * Returns lists of users; called when validating credentials
     *
     * @return userList list of users
     * */
    private static ObservableList<User> getUserList() {
        return userList;
    }

    /**
     * Searches list for users for matching username and password, returns id# if found, 0 if not
     *
     * @param username username
     * @param password password
     * @return int 0 or user id
     * */
    public static int validateCredentials(String username, String password) throws SQLException {
        populateUserList();

        for(User a : getUserList()) {
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                return a.getUserID();
            }
        }
        return 0;
    }
}
