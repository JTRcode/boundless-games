package com.example.boundless.shop;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.boundless.R;

public class ShopInventory {
    Activity activity;
    int[] inventory = new int[20]; //each inventory item is stored by their image id
    int numItems;
    LinearLayout layout;

    public ShopInventory(Activity activity){
        this.activity = activity;
        layout = activity.findViewById(R.id.inventory_linearlayout);
    }

    public void addItem(int image){
        inventory[numItems] = image;
        numItems++;
    }

    public void deleteItem(int image){
        for (int i=0; i < inventory.length; i++){
            if (inventory[i] == image){
                inventory[i] = 0;
                break;
            }
        }
    }

    public void displayInventory(){

        for (int i=0; i < numItems; i++){
            ImageView image = new ImageView(activity); //todo
            image.setImageResource(inventory[i]);
            layout.addView(image);
        }
    }
}
