package com.example.boundless;

/**
 * An account for a user
 */
class UserAccount {
    /**
     * The username of the user
     */
    private String name;
    /**
     * The password of the user;
     */
    private String password;

    UserAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Check if the credentials match this user
     */
    boolean checkCreds(String name, String password) {
        return (this.name.equals(name) && this.password.equals(password));
    }

    boolean sameUsername(String name) {
        //TODO: show a toast saying that someone already has this username
        return (this.name.equals(name));
    }
}
