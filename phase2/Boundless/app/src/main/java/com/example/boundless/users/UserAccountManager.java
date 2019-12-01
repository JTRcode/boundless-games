package com.example.boundless.users;

import android.content.Context;

import com.example.boundless.utilities.FileStorage;
import com.example.boundless.utilities.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of users and loads/saves them
 */
public class UserAccountManager {
    /**
     * The current user
     */
    public static UserAccount currentUser;
    private static List<UserAccount> users = new ArrayList<>();
    private static String userFileDirectory;
    private Context context;

    public UserAccountManager(Context context) {
        this.context = context;
        userFileDirectory = context.getFilesDir().getPath();
        restorePreviousUsers();
    }

    /**
     * Attempts to log in with credentials.
     *
     * @param username The username provided to sign in with.
     * @param password The password provided to sign in with.
     * @return If the user can sign in with those credentials.
     */
    public UserAccount signIn(String username, String password) {
        if (username.equals("") || password.equals("")) return null;
        for (UserAccount user : users)
            if (user.checkCreds(username, password)) {
                Session.setUser(username, password);
                if (currentUser == null) {
                    currentUser = user;
                    FileStorage.saveUserData(userFileDirectory, user);
                }
                return user;
            }
        return null;
    }

    /**
     * Signs user up with a username and password.
     *
     * @param username The username to sign up with.
     * @param password The password to sign up with.
     */
    public boolean signUp(String username, String password) {
        if (username.equals("") || password.equals("")) return false;
        for (UserAccount user : users)
            if (user.sameUsername(username))
                return false;
        Session.setUser(username, password);
        currentUser = new UserAccount(username, password);
        updateUser();
        return saveUserToFile(username, password, currentUser) && users.add(new UserAccount(username, password));
    }

    /**
     * Updates the user information stored
     */
    static void updateUser() {
        FileStorage.saveUserData(userFileDirectory, currentUser);
    }

    /**
     * Logs the user out of the account.
     */
    public static void signOut() {
        currentUser = null;
        Session.clearUser();
        FileStorage.saveUserData(userFileDirectory, null);
    }

    private void restorePreviousUsers() {
        users = FileStorage.getAllUsersFromFile(context);
    }

    /**
     * Saves the new user to file, so they can sign in again later
     *
     * @param name     The username of the user.
     * @param password The password of the user.
     * @return If saving the user was successful.
     */
    private boolean saveUserToFile(String name, String password, UserAccount user) {
        FileStorage.saveUserData(userFileDirectory, user);
        return FileStorage.saveUserToFile(userFileDirectory, name, password);
    }

    /**
     * Tells if the user is signed in or not
     *
     * @return If the user is signed in
     */
    public boolean notSignedIn() {
        currentUser = FileStorage.getUserData(userFileDirectory);
        String[] credentials = Session.getUser();
        return (currentUser == null || signIn(credentials[0], credentials[1]) == null);
    }
}
