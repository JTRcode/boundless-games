package com.example.boundless.shop;

import com.example.boundless.R;

import java.util.ArrayList;

/**
 * A shop for buying game-related items
 */
public class GameShop extends ShopTypeTemplate {
    private Runnable pleaseGetRidOfMe = new Runnable() {
        @Override
        public void run() {
            //not implemented
        }
    };

    /**
     * A new game shop
     */
    GameShop() {
        shopItems = new ArrayList<>();
        shopItems.add(new InventoryItem("coming soon-1", R.drawable.red_cross, null, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-2", R.drawable.red_cross, null, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-3", R.drawable.red_cross, null, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-4", R.drawable.red_cross, null, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-5", R.drawable.red_cross, null, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-6", R.drawable.red_cross, null, pleaseGetRidOfMe));
    }
}
