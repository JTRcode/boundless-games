package com.example.boundless.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.shop.InventoryItem;
import com.example.boundless.shop.ShopInventory;
import com.example.boundless.shop.ShopItemsBuilder;
import com.example.boundless.users.UserAccount;
import com.example.boundless.users.UserAccountManager;

import java.util.List;

/**
 * A shop where you can buy items related to different games
 */
public class ShopActivity extends AppCompatActivity {

    /**
     * The inventory for the shop
     */
    ShopInventory inventory;

    /**
     * A list of items in the inventory
     */
    List<InventoryItem> inventoryItems;
    private UserAccount user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        inventory = new ShopInventory(this);
        refreshButtonColour();
        findViewById(R.id.pixel).setBackgroundColor(Color.GREEN);
        ShopItemsBuilder builder = new ShopItemsBuilder(this, GamesEnum.PIXELS);
        inventoryItems = builder.build(this);
        user = UserAccountManager.currentUser;
    }

    private void refreshButtonColour() {
        findViewById(R.id.pixel).setBackgroundColor(Color.WHITE);
        findViewById(R.id.tile).setBackgroundColor(Color.WHITE);
        findViewById(R.id.gpa).setBackgroundColor(Color.WHITE);
    }

    /**
     * Opens the pixel game shop
     *
     * @param view The button clicked
     */
    public void shopPixel(View view) {
        refreshButtonColour();
        findViewById(R.id.pixel).setBackgroundColor(Color.GREEN);
        ShopItemsBuilder builder = new ShopItemsBuilder(this, GamesEnum.PIXELS);
        inventoryItems = builder.build(this);
    }

    /**
     * Opens the rotate tile game shop
     *
     * @param view The button clicked
     */
    public void shopTile(View view) {
        refreshButtonColour();
        findViewById(R.id.tile).setBackgroundColor(Color.GREEN);
        ShopItemsBuilder builder = new ShopItemsBuilder(this, GamesEnum.ROTATETILE);
        inventoryItems = builder.build(this);
    }

    /**
     * Opens the GPA game shop
     *
     * @param view The button clicked
     */
    public void shopGpa(View view) {
        refreshButtonColour();
        findViewById(R.id.gpa).setBackgroundColor(Color.GREEN);
        ShopItemsBuilder builder = new ShopItemsBuilder(this, GamesEnum.GPACATCHER);
        inventoryItems = builder.build(this);
    }

    /**
     * Goes back to the main menu
     *
     * @param view The button clicked
     */
    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Buy an item from the shop
     *
     * @param view The button clicked
     */
    public void purchase(View view) {
        if (user.getUserPoints() - GameResources.ITEM_COST < 0) {
            showToast("Insufficient funds");
            return;
        }

        user.addUserPoints(-GameResources.ITEM_COST);
        TextView points = findViewById(R.id.points_remaining);
        points.setText(String.valueOf(user.getUserPoints()));

        int imageID = (int) view.getTag();
        for (InventoryItem item : inventoryItems) {
            if (item.getImageId() == imageID) {
                if (item.isImmediate()) {
                    item.useItem();
                    showToast("Purchased, and used!");
                    return;
                }
                inventory.addItem(item);
                break;
            }
        }
        showToast("Purchased!");
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Delete the user's inventory
     *
     * @param view The button clicked
     */
    public void clearInventory(View view) {
        inventory.deleteAll();
    }
}
