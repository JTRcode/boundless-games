package com.example.boundless.shop;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.boundless.R;
import com.example.boundless.users.UserAccountManager;


public class ShopInventory {
    private Activity activity;
    private int[] inventory = new int[20]; //each inventory item is stored by their image id
    private String user = UserAccountManager.currentUser.getName();;
    private int numItems;
    private LinearLayout layout;
    private SharedPreferences preferences;


    public ShopInventory(Activity activity){
        this.activity = activity;
        layout = activity.findViewById(R.id.inventory_linearlayout);

        preferences = activity.getPreferences(Activity.MODE_PRIVATE);

        numItems = preferences.getInt(user, 0);
        for (int i=0; i<numItems; i++){
            inventory[i] = preferences.getInt(user+i, 0);
        }
        displayInventory();

    }

    public void addItem(int image){
        if (numItems == 20){
            return;
        }
        inventory[numItems] = image;
        displayImage(numItems);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(user+numItems, image);
        numItems++;
        editor.putInt(user, numItems);
        editor.apply();



    }

    public boolean deleteItem(int image){
        // removes the first item in inventory that matches int image
        // returns true when the item has been deleted
        boolean deleted = false;
        int[] newInventory = new int[20];
        int index = 0;

        for (int i=0; i < numItems; i++){
            if (inventory[i] == image && !deleted){
                deleted = true;
            }
            else {
                newInventory[index] = inventory[i];
                index++;
            }
        }
        if (deleted){
            numItems--;
        }
        inventory = newInventory;

        SharedPreferences.Editor editor = preferences.edit();
        for (int i=0; i<numItems; i++){
            editor.putInt(user+i, image);
        }
        editor.putInt(user, numItems);
        editor.apply();


        return deleted;
    }

    private void displayImage(int index){
        if (index > numItems){
            return;
        }
        ImageView image = new ImageView(activity);
        image.setImageResource(inventory[index]);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(160, 160);
        layoutParams.setMargins(10, 10, 10, 10);
        image.setLayoutParams(layoutParams);
        layout.addView(image);

    }

    private void displayInventory(){
        layout.removeAllViews();
        for (int i=0; i < numItems; i++){
            displayImage(i);
        }
    }
}
