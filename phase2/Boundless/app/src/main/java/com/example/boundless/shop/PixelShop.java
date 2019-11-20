package com.example.boundless.shop;

import com.example.boundless.R;

public class PixelShop extends ShopTypeTemplate {

    public PixelShop(){
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
