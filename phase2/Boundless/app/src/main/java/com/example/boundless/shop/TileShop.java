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
                R.drawable.medal, GamesEnum.ROTATETILE, () -> Achievements.twentySecondsOrLessRotate = true));
        inventoryList.get(0).setImmediate();
        inventoryList.add(new InventoryItem("Skip the next level",
                R.drawable.cross_filled_pipe, GamesEnum.ROTATETILE,
                () -> UserAccountManager.currentUser.addUnlocked(GamesEnum.ROTATETILE)));
        inventoryList.get(1).setImmediate();
        inventoryList.add(new InventoryItem("Put a cross-pipe anywhere",
                R.drawable.cross_pipe, GamesEnum.ROTATETILE, () -> RotateTileTouchHandler.setFreeTile('X')));
        return inventoryList;
    }

    public int[] itemsImage() {
        int[] items = new int[5];
        items[0] = R.drawable.cross_pipe;
        items[1] = R.drawable.cross_filled_pipe;
        items[2] = R.drawable.t_filled_pipe;
        items[3] = R.drawable.t_pipe;
        items[4] = R.drawable.straight_filled_pipe;
        return items;

    }

    public String[] itemsDescription() {
        String[] items = new String[5];
        items[0] = "coming soon-1";
        items[1] = "coming soon-2";
        items[2] = "coming soon-3";
        items[3] = "coming soon-4";
        items[4] = "coming soon-5";
        return items;
    }
}
