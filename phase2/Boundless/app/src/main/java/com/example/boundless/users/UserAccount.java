package com.example.boundless.users;

/**
 * An account for a user
 */
public class UserAccount {
    private String name;
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

    /**
     * Check if the user has the same username as another user
     *
     * @param name The name to check if they are equal to
     * @return If the username is the same
     */
    boolean sameUsername(String name) {
        //TODO: show a toast saying that someone already has this username
        return (this.name.equals(name));
    }

    public String getName() {
        return name;
    }
}
