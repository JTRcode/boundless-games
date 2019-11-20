package com.example.boundless.shop;

import com.example.boundless.R;

public class TileShop extends ShopTypeTemplate {
    public TileShop(){}

    public int[] itemsImage(){
        int[] items = new int[5];
        items[0] = R.drawable.cross_pipe;
        items[1] = R.drawable.cross_filled_pipe;
        items[2] = R.drawable.t_filled_pipe;
        items[3] = R.drawable.t_pipe;
        items[4] = R.drawable.straight_filled_pipe;
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
