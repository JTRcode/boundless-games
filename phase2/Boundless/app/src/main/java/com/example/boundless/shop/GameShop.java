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

    public int[] itemsImage(){
        int[] items = new int[6];
        items[0] = R.drawable.red_cross;
        items[1] = R.drawable.red_cross;
        items[2] = R.drawable.red_cross;
        items[3] = R.drawable.red_cross;
        items[4] = R.drawable.red_cross;
        items[5] = R.drawable.red_cross;
        return items;

    }

    public String[] itemsDescription(){
        String[] items = new String[6];
        items[0] = "coming soon-1";
        items[1] = "coming soon-2";
        items[2] = "coming soon-3";
        items[3] = "coming soon-4";
        items[4] = "coming soon-5";
        items[5] = "coming soon-6";
        return items;
    }
}
