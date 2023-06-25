package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Description: This class contains static methods to interact with the database on user data
 *
 * @author maxsealey Sealey
 * */
public class UserDAO {
    private static User currentUser;

    /**
     * gets username of currently signed-in user
     *
     * @return currentUser
     * */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets username of currently signed-in user; called on Login form
     *
     * @param currentUser username
     * */
    public static void setCurrentUser(User currentUser) {
        UserDAO.currentUser = currentUser;
    }

    /**
     * Returns lists of users; called when validating credentials
     *
     * @return userList list of users
     * @throws SQLException
     * */
    public static ObservableList<User> getUserList() throws SQLException {
        ObservableList<User> list = FXCollections.observableArrayList();

        String sql = "SELECT User_ID, User_Name, Password FROM client_schedule.users";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet results = ps.executeQuery();

        while(results.next()){
            int userid = results.getInt("User_ID");
            String username = results.getString("User_Name");
            String password = results.getString("Password");
            list.add(new User(userid, username, password));
        }
        return list;
    }

    /**
     * Searches list for users for matching username and password, returns id# if found, 0 if not
     *
     * @param username username
     * @param password password
     * @return int 0 or user id
     * @throws SQLException
     * */
    public static int validateCredentials(String username, String password) throws SQLException {

        for(User a : getUserList()) {
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                return a.getUserID();
            }
        }
        return 0;
    }
}
