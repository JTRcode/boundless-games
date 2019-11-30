package com.example.boundless.shop;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.gpa_catcher_game.GPAGameDrawer;
import com.example.boundless.games.gpa_catcher_game.GPAGameStatus;
import com.example.boundless.games.gpa_catcher_game.StatusUpdater;

import java.util.ArrayList;
import java.util.List;

/**
 * A shop for buying tile-related items
 */
public class GpaShop extends ShopTypeTemplate {

    /**
     * A new GPA Game shop
     */
    public GpaShop() {
        shopItems = new ArrayList<>();
        shopItems.addAll(setItems());
    }

    private List<InventoryItem> setItems() {
        List<InventoryItem> inventoryList = new ArrayList<>();

        inventoryList.add(new InventoryItem(
                "Maximum life +1",
                R.drawable.heart,
                GamesEnum.GPACATCHER, GPAGameStatus::addLife));

        inventoryList.add(new InventoryItem(
                "Protection from the first bomb you catch",
                R.drawable.bomb,
                GamesEnum.GPACATCHER, GPAGameStatus::bombProtection));

        inventoryList.add(new InventoryItem(
                "More sleep! \n Increased drop rate for sleep",
                R.drawable.sleep,
                GamesEnum.GPACATCHER, StatusUpdater::moreSleep));

        inventoryList.add(new InventoryItem(
                "More time! \n Increased drop rate for time",
                R.drawable.time,
                GamesEnum.GPACATCHER, StatusUpdater::moreTime));

        inventoryList.add(new InventoryItem(
                "The first 12 assignments caught rewards 2x the normal GPA",
                R.drawable.assignment,
                GamesEnum.GPACATCHER, GPAGameStatus::doubleGPA));

        return inventoryList;

    }
}
