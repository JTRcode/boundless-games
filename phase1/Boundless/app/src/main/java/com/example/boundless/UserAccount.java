package com.example.boundless;

/**
 * An account for a user
 */
public class UserAccount {
    /**
     * The username of the user
     */
    private String name;
    /**
     * The password of the user;
     */
    private String password;

    public UserAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Check if the credentials match this user
     */
    public boolean checkCreds(String name, String password) {
        return (this.name.equals(name) && this.password.equals(password));
    }
}
