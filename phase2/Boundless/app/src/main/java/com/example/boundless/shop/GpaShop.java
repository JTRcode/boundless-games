package com.example.boundless.shop;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;

import java.util.ArrayList;

/**
 * A shop for buying tile-related items
 */
public class GpaShop extends ShopTypeTemplate {
    private Runnable pleaseGetRidOfMe = new Runnable() {
        @Override
        public void run() {
            //not implemented
        }
    };

    /**
     * A new GPA Game shop
     */
    public GpaShop() {
        shopItems = new ArrayList<>();
        shopItems.add(new InventoryItem("coming soon-1", R.drawable.basket, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-2", R.drawable.bomb, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-3", R.drawable.sleep, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-4", R.drawable.heart, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-5", R.drawable.time, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
    }
}
