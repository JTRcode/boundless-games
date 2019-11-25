package com.example.boundless.shop;

import com.example.boundless.R;
import com.example.boundless.games.GamesEnum;

import java.util.ArrayList;

public class GpaShop extends ShopTypeTemplate {
    private Runnable pleaseGetRidOfMe = new Runnable() {
        @Override
        public void run() {
            //not implemented
        }
    };

    public GpaShop() {
        shopItems = new ArrayList<>();
        shopItems.add(new InventoryItem("coming soon-1", R.drawable.basket, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-2", R.drawable.bomb, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-3", R.drawable.sleep, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-4", R.drawable.heart, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
        shopItems.add(new InventoryItem("coming soon-5", R.drawable.time, GamesEnum.GPACATCHER, pleaseGetRidOfMe));
    }

    public int[] itemsImage() {
        int[] items = new int[5];
        items[0] = R.drawable.basket;
        items[1] = R.drawable.bomb;
        items[2] = R.drawable.sleep;
        items[3] = R.drawable.heart;
        items[4] = R.drawable.time;
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
