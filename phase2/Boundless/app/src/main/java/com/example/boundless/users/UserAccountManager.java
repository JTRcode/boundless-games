package com.example.boundless.users;

import android.content.Context;
import android.util.Log;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.utilities.Session;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of users and loads/saves them
 */
public class UserAccountManager {
    /**
     * The current user
     */
    public static UserAccount currentUser = null;
    private static List<UserAccount> users = new ArrayList<>();
    private static final String FILE_NAME = "/prfs.ckz";
    private static final String TAG = "UserAccountManager";
    private Context context;
    private String dataSeparator = "`";

    public UserAccountManager(Context context) {
        this.context = context;
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
        if (username == "" || password == "") return null;
        for (UserAccount user : users)
            if (user.checkCreds(username, password)) {
                Session.setUser(username, password);
                currentUser = user;
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
        //TODO: show a toast saying invalid username or password
        if (username.equals("") || password.equals("")) return false;
        for (UserAccount user : users) {
            if (user.sameUsername(username)) {
                return false;
            }
        }
        Session.setUser(username, password);
        currentUser = new UserAccount(username, password);

        return saveUserToFile(username, password, currentUser) && users.add(new UserAccount(username, password));
    }

    /**
     * Logs the user out of the account.
     */
    public void signOut() {
        Session.clearUser();
        currentUser = null;
    }

    private void restorePreviousUsers() {
        String line;
        try {
            File file = new File(context.getFilesDir() + FILE_NAME);
            if (!file.exists()) return;

            FileInputStream fileInputStream = new FileInputStream(file);
            Log.w("MANAGER", "FILE " + context.getFilesDir() + FILE_NAME + " has been opened!!");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                //separate line by `, restore name and password...
                String[] userInfo = line.split(dataSeparator, 4);
                addToUsers(userInfo);
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    private void addToUsers(String[] userInfo) {
        try {
            users.add(new UserAccount(userInfo[0], userInfo[1], Integer.getInteger(userInfo[2]), Integer.getInteger(userInfo[3])));
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            users.add(new UserAccount(userInfo[0], userInfo[1]));
        }
    }

    /**
     * Saves the new user to file, so they can sign in again later
     *
     * @param name     The username of the user.
     * @param password The password of the user.
     * @return If saving the user was successful.
     */
    private boolean saveUserToFile(String name, String password, UserAccount user) {
        String data = name + dataSeparator + password + dataSeparator + user.getUnlocked(GamesEnum.PIXELS) + dataSeparator + user.getUnlocked(GamesEnum.ROTATETILE);
        FileOutputStream fileOutputStream = null;
        try {
            File usersFile = new File(context.getFilesDir() + FILE_NAME);
            if (!usersFile.exists()) Log.w(TAG, "FILE didn't exist before");
            if (!usersFile.exists() && !usersFile.createNewFile())
                Log.d(TAG, "Failed to create usersFile");
            fileOutputStream = new FileOutputStream(usersFile, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            Log.w(TAG, "reached end of saveUserToFile");
            return true;
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return false;
    }

    /**
     * Tells if the user is signed in or not
     *
     * @return If the user is signed in
     */
    public boolean notSignedIn() {
        String[] credentials = Session.getUser();
        currentUser = signIn(credentials[0], credentials[1]);
        return currentUser == null;
    }
}
