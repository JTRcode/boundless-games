package com.example.boundless.shop;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.pixel_game.PixelDrawer;
import com.example.boundless.stats.Achievements;
import com.example.boundless.users.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A shop for buying pixel game-related items
 */
public class PixelShop extends ShopTypeTemplate {

    /**
     * A new pixel shop
     */
    public PixelShop(){
        shopItems = new ArrayList<>();
        shopItems.addAll(setItems());
    }

    private List<InventoryItem> setItems() {
        List<InventoryItem> inventoryList = new ArrayList<>();
        inventoryList.add(new InventoryItem("Unlock the next pixel level",
                R.drawable.level_button_locked, GamesEnum.PIXELS, () ->
                UserAccountManager.currentUser.addUnlocked(GamesEnum.PIXELS)));
        inventoryList.get(0).setImmediate();
        inventoryList.add(new InventoryItem("View the solution for a second",
                R.drawable.pixel_game, GamesEnum.PIXELS, PixelDrawer::showHint));
        inventoryList.add(new InventoryItem("Unlock the 'Get the correct answer is less than 20 seconds' achievement",
                R.drawable.medal, GamesEnum.PIXELS, Achievements::setTwentySecondsOrLessPixel));
        inventoryList.get(2).setImmediate();
        return inventoryList;
    }
}
