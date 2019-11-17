package com.example.boundless;

public class GpaShop extends ShopItems {
    public GpaShop(){}

    public int[] itemsImage(){
        int[] items = new int[5];
        items[0] = R.drawable.basket;
        items[1] = R.drawable.bomb;
        items[2] = R.drawable.sleep;
        items[3] = R.drawable.heart;
        items[4] = R.drawable.time;
        return items;

    }

    public String[] itemsDescription(){
        String[] items = new String[5];
        items[0] = "coming soon-1";
        items[1] = "coming soon-2";
        items[2] = "coming soon-3";
        items[3] = "coming soon-4";
        items[4] = "coming soon-5";
        return items;
    }

}
