package com.example.boundless.utilities;

import android.content.Context;
import android.util.Log;

import com.example.boundless.users.UserAccount;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and retrieves information in a JSON format
 * Created with help from Pratik Butani on StackOverflow
 */
public class FileStorage {
    private static String fileName = "storage.json";
    private static final String ALL_USERS_FILE_NAME = "users.ckz";
    private static final String DATA_SEPARATOR = "`";

    private FileStorage() {
    }

    /**
     * Store the user's data
     *
     * @param dirPath    The application file directory
     * @param userToSave The user to save to file
     */
    public static void saveUserData(String dirPath, UserAccount userToSave) {
        String mJsonResponse = (new Gson()).toJson(userToSave);
        mJsonResponse = removeInventoryItems(mJsonResponse);
        try (FileWriter file = new FileWriter(dirPath + "/" + fileName)) {
            file.write(mJsonResponse);
            file.flush();
        } catch (IOException e) {
            warnException(e);
        }
    }

    /**
     * Get user's data
     *
     * @param dirPath The directory path of the user's file
     * @return The current user's data
     */
    public static UserAccount getUserData(String dirPath) {
        File file = new File(dirPath + "/" + fileName);
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            String jsonData = new String(buffer);
            jsonData = removeInventoryItems(jsonData);

            return (new Gson()).fromJson(jsonData, UserAccount.class);
        } catch (IOException e) {
            warnException(e);
            return null;
        }
    }

    private static String removeInventoryItems(String jsonData) {
        try {
            JSONObject json = new JSONObject(jsonData);
            json.remove("inventoryItems");
            return json.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Save a new user to file
     *
     * @param dirPath  The directory path of the user's file
     * @param name     The user's name
     * @param password The user's password
     * @return Whether the save was successful
     */
    public static boolean saveUserToFile(String dirPath, String name, String password) {
        String data = name + DATA_SEPARATOR + password;
        FileOutputStream fileOutputStream = null;
        File usersFile = new File(dirPath + ALL_USERS_FILE_NAME);
        if (!usersFile.exists())
            Log.d(FileStorage.class.getCanonicalName(), "FILE didn't exist before");
        try {
            if (!usersFile.exists() && !usersFile.createNewFile())
                Log.e(File.class.getCanonicalName(), "Failed to create usersFile");
            fileOutputStream = new FileOutputStream(usersFile, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            Log.d(File.class.getCanonicalName(), "reached end of saveUserToFile");
            return true;
        } catch (IOException ex) {
            warnException(ex);
        }
        return false;
    }

    /**
     * Get a list of the users from file
     *
     * @param context The current context
     * @return A list of users
     */
    public static List<UserAccount> getAllUsersFromFile(Context context) {
        List<UserAccount> users = new ArrayList<>();
        String line;
        File file = new File(context.getFilesDir() + ALL_USERS_FILE_NAME);
        if (!file.exists()) return users;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Log.i(FileStorage.class.getCanonicalName(), "FILE " + context.getFilesDir() + ALL_USERS_FILE_NAME + " has been opened!!");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                //separate line by `, restore name and password...
                String[] userInfo = line.split(DATA_SEPARATOR, 2);
                users.add(new UserAccount(userInfo[0], userInfo[1]));
            }
            bufferedReader.close();
        } catch (IOException ex) {
            warnException(ex);
        }
        return users;
    }

    private static void warnException(Exception e) {
        Log.e(FileStorage.class.getCanonicalName(), e.getLocalizedMessage());
    }
}
