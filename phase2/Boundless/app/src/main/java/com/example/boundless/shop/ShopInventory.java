package com.example.boundless.shop;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.users.UserAccount;
import com.example.boundless.users.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * An inventory for the shop
 */
public class ShopInventory {
    private Activity activity;
    private static UserAccount user = UserAccountManager.currentUser;
    private LinearLayout layout;

    /**
     * A new shop inventory
     *
     * @param act The current activity
     */
    public ShopInventory(Activity act) {
        activity = act;
        layout = activity.findViewById(R.id.inventory_linearlayout);
        displayInventory();
    }

    /**
     * Gets the inventory for the game
     *
     * @param game     The game to get the inventory for
     * @return A list of image id's for this game
     */
    public static List<InventoryItem> getInventoryForGame(GamesEnum game) {
        List<InventoryItem> gameInventory = new ArrayList<>();
        for (InventoryItem item : user.getInventoryItems())
            if (item.isPartOfGame(game)) gameInventory.add(item);
        return gameInventory;
    }

    /**
     * Adds an item to the inventory
     *
     * @param item The item to add
     */
    public void addItem(InventoryItem item) {
        if (user.getInventoryItems().size() >= 20) return;
        user.addInventoryItem(item);
        displayImage(item.getImageId());
    }

    /**
     * Deletes all items currently in the inventory
     */
    public void deleteAll() {
        List<InventoryItem> inventory = new ArrayList<>(user.getInventoryItems());
        for (InventoryItem item : inventory)
            deleteItem(item);

        displayInventory();
    }

    /**
     * Deletes an item from the shop inventory
     *
     * @param item The item to delete
     */
    static void deleteItem(InventoryItem item) {
        user.removeInventoryItem(item);
    }

    private void displayImage(int imageId) {
        ImageView image = new ImageView(activity);
        image.setImageResource(imageId);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(160, 160);
        layoutParams.setMargins(10, 10, 10, 10);
        image.setLayoutParams(layoutParams);
        layout.addView(image);
    }

    private void displayInventory() {
        layout.removeAllViews();
        List<InventoryItem> items = user.getInventoryItems();
        for (InventoryItem item : items)
            displayImage(item.getImageId());
    }
}
