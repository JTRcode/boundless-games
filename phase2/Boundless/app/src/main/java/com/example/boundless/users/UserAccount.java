package com.example.boundless.users;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.shop.InventoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * An account for a user
 */
public class UserAccount {
    private String name;
    private String password;
    private int pixelUnlocked;
    private int tileUnlocked;
    private List<InventoryItem> inventoryItems = new ArrayList<>();
    private int userPoints;

    /**
     * A user's account with a name and password
     *
     * @param name     The user's name
     * @param password The user's password
     */
    public UserAccount(String name, String password) {
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
        return (this.name.equals(name));
    }

    /**
     * Add to the number of unlocked levels for a given game
     *
     * @param game The game to add an unlocked level to
     */
    public void addUnlocked(GamesEnum game) {
        switch (game) {
            case PIXELS:
                pixelUnlocked++;
                break;
            case ROTATETILE:
                tileUnlocked++;
                break;
            default:
                break;
        }
        UserAccountManager.updateUser();
    }

    /**
     * Get the number of unlocked levels for a given game
     *
     * @param game The game to get the number of unlocked levels for
     * @return The number of unlocked levels for the given game
     */
    public int getUnlocked(GamesEnum game) {
        switch (game) {
            case PIXELS:
                return pixelUnlocked;
            case ROTATETILE:
                return tileUnlocked;
            default:
                return 0;
        }
    }

    /**
     * Gets the name of the user
     *
     * @return The user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Adds an inventory item
     *
     * @param item The item to add
     */
    public void addInventoryItem(InventoryItem item) {
        inventoryItems.add(item);
        UserAccountManager.updateUser();
    }

    /**
     * Removes an item from the inventory
     *
     * @param item The item to remove
     */
    public void removeInventoryItem(InventoryItem item) {
        inventoryItems.remove(item);
        UserAccountManager.updateUser();
    }

    /**
     * Gets a list of the user's inventory items
     *
     * @return The user's inventory items
     */
    public List<InventoryItem> getInventoryItems() {
        if (inventoryItems == null) inventoryItems = new ArrayList<>();
        return inventoryItems;
    }

    /**
     * Add an amount of points to the user
     *
     * @param pointsToAdd The number of points to add to the user
     */
    public void addUserPoints(int pointsToAdd) {
        userPoints = Math.max(0, userPoints + pointsToAdd);
        UserAccountManager.updateUser();
    }

    /**
     * Get the amount of points that the user has
     *
     * @return The user's points
     */
    public int getUserPoints() {
        return userPoints;
    }
}
