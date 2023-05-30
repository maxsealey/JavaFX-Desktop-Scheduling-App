package sealey.javafxdesktopschedulingapp.model;

/**
 * Description:
 *
 * @author Max Sealey
 * */
public class User {
    
    /**
     * User class constructor
     *
     * @param username username
     * @param userID id
     * @param password pass
     * */
    public User(int userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;

    }

    private final int userID;
    private final String username;
    private final String password;
    
    /**
     * @return userID id
     * */
    public int getUserID() {
        return userID;
    }

    /**
     * @return username name
     * */
    public String getUsername() {
        return username;
    }

    /**
     * @return password password
     * */
    public String getPassword() {
        return password;
    }
}
