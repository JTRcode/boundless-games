package com.example.boundless;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

public class ShopItemsBuilder {

    Activity activity;
    ShopItems shop;

    public ShopItemsBuilder(Activity activity, String category){

        this.activity = activity;

        switch (category){
            case "game":
                shop = new GameShop();
                break;
            case "pixel":
                shop = new PixelShop();
                break;
            case "tile":
                shop = new TileShop();
                break;
            case "gpa":
                shop = new GpaShop();
                break;

        }
    }

    private void buildImages(){
        int[] images = shop.itemsImage();
        ImageButton item = activity.findViewById(R.id.item1);
        item.setImageResource(images[0]);

        item = activity.findViewById(R.id.item2);
        item.setImageResource(images[1]);

        item = activity.findViewById(R.id.item3);
        item.setImageResource(images[2]);

        item = activity.findViewById(R.id.item4);
        item.setImageResource(images[3]);

        item = activity.findViewById(R.id.item5);
        item.setImageResource(images[4]);

    }
    private void buildDescriptions(){
        String[] descriptions = shop.itemsDescription();
        Button item = activity.findViewById(R.id.item1_description);
        item.setText(descriptions[0]);

        item = activity.findViewById(R.id.item2_description);
        item.setText(descriptions[1]);

        item = activity.findViewById(R.id.item3_description);
        item.setText(descriptions[2]);

        item = activity.findViewById(R.id.item4_description);
        item.setText(descriptions[3]);

        item = activity.findViewById(R.id.item5_description);
        item.setText(descriptions[4]);

    }

    public void build(){
        buildImages();
        buildDescriptions();
    }



}
