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
                R.drawable.medal, GamesEnum.PIXELS, () -> Achievements.twentySecondsOrLessPixel = true));
        inventoryList.get(2).setImmediate();
        return inventoryList;
    }

    public int[] itemsImage(){
        int[] items = new int[5];
        items[0] = R.drawable.heart;
        items[1] = R.drawable.heart;
        items[2] = R.drawable.heart;
        items[3] = R.drawable.heart;
        items[4] = R.drawable.heart;
        return items;

    }

    public String[] itemsDescription(){
        String[] items = new String[6];
        items[0] = "coming soon-1";
        items[1] = "coming soon-2";
        items[2] = "coming soon-3";
        items[3] = "coming soon-4";
        items[4] = "coming soon-5";
        return items;
    }
}
