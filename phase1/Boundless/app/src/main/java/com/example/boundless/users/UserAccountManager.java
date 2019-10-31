package com.example.boundless.users;

import android.content.Context;
import android.util.Log;

import com.example.boundless.Session;

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
     * A list of all users
     */
    private static List<UserAccount> users = new ArrayList<>();
    /**
     * The path to store users at
     */
    private final String path;
    private static final String FILE_NAME = "prfs.ckz";
    private static final String TAG = "UserAccountManager";
    /**
     * The current context, used to get the file directory
     */
    private Context context;

    public UserAccountManager(Context context) {
        this.context = context;
        path = context.getFilesDir() + File.pathSeparator;
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
        for (UserAccount user : users)
            if (user.checkCreds(username, password)) {
                Session.setUser(username, password);
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
        return (saveUserToFile(username, password) && users.add(new UserAccount(username, password)));
    }

    /**
     * Logs the user out of the account.
     */
    public void signOut() {
        Session.clearUser();
    }

    private void restorePreviousUsers() {
        String line;
        try {
            File file = new File(path + FILE_NAME);
            if (!file.exists()) return;

            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                //separate line by `, restore name and password...
                String[] userInfo = line.split("`", 2);
                users.add(new UserAccount(userInfo[0], userInfo[1]));
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    /**
     * Saves the new user to file, so they can sign in again later
     *
     * @param name     The username of the user.
     * @param password The password of the user.
     * @return If saving the user was successful.
     */
    private boolean saveUserToFile(String name, String password) {
        String data = name + "`" + password;
        FileOutputStream fileOutputStream = null;
        try {
            File storageDir = new File(context.getFilesDir(), path);
            if (!storageDir.exists() && !storageDir.mkdirs())
                Log.d(TAG, "Failed to create directory");
            File usersFile = new File(path + FILE_NAME);
            if (!usersFile.exists() && !usersFile.createNewFile())
                Log.d(TAG, "Failed to create usersFile");
            fileOutputStream = new FileOutputStream(usersFile, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
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
        return (credentials.length == 0);
    }
}
