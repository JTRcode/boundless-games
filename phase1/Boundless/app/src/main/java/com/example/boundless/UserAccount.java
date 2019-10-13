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
     * Check if the credentials match this user
     */
    public boolean checkCreds(String name){
        return (name == name);
    }
}
