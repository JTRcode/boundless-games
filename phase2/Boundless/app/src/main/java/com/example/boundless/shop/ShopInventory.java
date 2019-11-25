package com.example.boundless.shop;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.users.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * An inventory for the shop
 */
public class ShopInventory {
    private Activity activity;
    private static List<Integer> inventory = new ArrayList<>(); //each inventory item is stored by their image id
    private static String user = UserAccountManager.currentUser.getName();
    private int numItems;
    private LinearLayout layout;
    private static SharedPreferences preferences;

    /**
     * A new shop inventory
     *
     * @param activity The current activity
     */
    public ShopInventory(Activity activity) {
        this.activity = activity;
        layout = activity.findViewById(R.id.inventory_linearlayout);

        updateInventory(activity);
        displayInventory();
    }

    private static void updateInventory(Activity activity) {
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);

        int numItems = preferences.getInt(user, 0);
        for (int i = 0; i < numItems; i++) {
            inventory.add(preferences.getInt(user + i, 0));
        }
    }

    /**
     * Gets the inventory for the game
     *
     * @param activity The current activity
     * @param game     The game to get the inventory for
     * @return A list of image id's for this game
     */
    public static List<InventoryItem> getInventoryForGame(Activity activity, GamesEnum game) {
        if (inventory.isEmpty()) updateInventory(activity);
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);
        ShopTypeTemplate shop;
        switch (game) {
            case PIXELS:
                shop = new PixelShop();
                break;
            case ROTATETILE:
                shop = new TileShop();
                break;
            default:
                shop = new GpaShop();
                break;
        }
        List<InventoryItem> shopInventory = new ArrayList<>(shop.shopItems);
        shopInventory.removeIf(item -> inventory.contains(item.getImageId()));
        return shopInventory;
    }

    /**
     * Adds an item to the inventory
     *
     * @param image The image of the item to add
     */
    public void addItem(int image) {
        if (inventory.size() >= 20) return;
        inventory.add(image);
        displayImage(inventory.size() - 1);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(user + numItems, image);
        numItems++;
        editor.putInt(user, numItems);
        editor.apply();
    }

    /**
     * Deletes an item from the shop inventory
     *
     * @param image The item image to delete
     * @return If the delete was successful
     */
    static boolean deleteItem(int image) {
        // removes the first item in inventory that matches int image
        // returns true when the item has been deleted
        boolean deleted = false;
        List<Integer> newInventory = new ArrayList<>();

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == image && !deleted)
                deleted = true;
            else
                newInventory.add(inventory.get(i));
        }
        inventory = newInventory;

        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < inventory.size(); i++) {
            editor.putInt(user + i, image);
        }
        editor.putInt(user, inventory.size());
        editor.apply();

        return deleted;
    }

    private void displayImage(int index) {
        ImageView image = new ImageView(activity);
        image.setImageResource(inventory.get(index));
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(160, 160);
        layoutParams.setMargins(10, 10, 10, 10);
        image.setLayoutParams(layoutParams);
        layout.addView(image);
    }

    private void displayInventory() {
        layout.removeAllViews();
        for (int i = 0; i < inventory.size(); i++) {
            displayImage(i);
        }
    }
}
