package com.example.boundless.shop;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.rotate_tile_game.RotateTileTouchHandler;
import com.example.boundless.stats.Achievements;
import com.example.boundless.users.UserAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A shop for buying tile-related items
 */
public class TileShop extends ShopTypeTemplate {

    /**
     * A new tile shop
     */
    public TileShop() {
        shopItems = new ArrayList<>();
        shopItems.addAll(setItems());
    }

    private List<InventoryItem> setItems() {
        List<InventoryItem> inventoryList = new ArrayList<>();
        inventoryList.add(new InventoryItem("Unlock the 'Get the correct answer in less than 20 seconds' achievement",
                R.drawable.medal, GamesEnum.ROTATETILE, Achievements::setTwentySecondsOrLessRotate));
        inventoryList.get(0).setImmediate();
        inventoryList.add(new InventoryItem("Skip the next level",
                R.drawable.cross_filled_pipe, GamesEnum.ROTATETILE,
                () -> UserAccountManager.currentUser.addUnlocked(GamesEnum.ROTATETILE)));
        inventoryList.get(1).setImmediate();
        inventoryList.add(new InventoryItem("Put a cross-pipe anywhere",
                R.drawable.cross_pipe, GamesEnum.ROTATETILE, () -> RotateTileTouchHandler.setFreeTile('X')));
        return inventoryList;
    }
}
