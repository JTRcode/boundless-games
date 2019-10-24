package com.example.boundless;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserAccountManager {
    /**
     * A list of all users
     */
    private List<UserAccount> users = new ArrayList<>();
    private final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/boundless/readwrite/";
    private final static String TAG = "UserAccountManager";
    private final static String FILE_NAME = "prfs.ckz";

    public UserAccountManager() {
        users.add(new UserAccount("Aaa", "aaa"));
        restorePreviousUsers();
    }

    /**
     * Attempts to log in with credentials.
     *
     * @param username The username provided to sign in with.
     * @param password The password provided to sign in with.
     * @return If the user can sign in with those credentials.
     */
    public boolean signIn(String username, String password) {
        for (UserAccount user : users)
            if (user.checkCreds(username, password)) {
                //TODO: Nyah save to preferences
                return true;
            }
        return false;
    }

    /**
     * Signs user up with a username and password.
     *
     * @param name     The username to sign up with.
     * @param password The password to sign up with.
     */
    public void signUp(String name, String password) {
        users.add(new UserAccount(name, password));
        saveUserToFile(name, password);
    }

    /**
     * Restores the previous users and allows them to sign in again.
     */
    private void restorePreviousUsers() {
        String line;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path + FILE_NAME));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                //separate line by `, restore name and password...
                String[] userInfo = line.split("`", 1);
                users.add(new UserAccount(userInfo[0], userInfo[1]));
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    private void saveUserToFile(String name, String password) {
        String data = name + "`" + password;
        try {
            new File(path).mkdir();
            File file = new File(path + FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
    }
}
