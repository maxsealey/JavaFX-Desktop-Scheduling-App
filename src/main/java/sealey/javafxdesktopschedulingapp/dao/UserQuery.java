package sealey.javafxdesktopschedulingapp.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sealey.javafxdesktopschedulingapp.helpers.DBConnection;
import sealey.javafxdesktopschedulingapp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
    private static ObservableList<User> userList = FXCollections.observableArrayList();

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

    private static ObservableList<User> getUserList() {
        return userList;
    }

    public static int validateCredentials(String username, String password) throws SQLException {
        populateUserList();

        for(User a : getUserList()) {
            if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                return a.getUserID();
            }
            System.out.println(a.getUsername() + "\n");
        }
        return 0;
    }
}
