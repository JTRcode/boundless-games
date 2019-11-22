package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.boundless.shop.ShopInventory;
import com.example.boundless.shop.ShopItemsBuilder;

public class ShopActivity extends AppCompatActivity {

    ShopInventory inventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        inventory = new ShopInventory(this);
    }



    public void shopGame(View view) {
        ShopItemsBuilder builder = new ShopItemsBuilder(this, "game");
        builder.build();

    }

    public void shopPixel(View view) {
        ShopItemsBuilder builder = new ShopItemsBuilder(this, "pixel");
        builder.build();
    }

    public void shopTile(View view) {
        ShopItemsBuilder builder = new ShopItemsBuilder(this, "tile");
        builder.build();
    }

    public void shopGpa(View view) {
        ShopItemsBuilder builder = new ShopItemsBuilder(this, "gpa");
        builder.build();
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void purchase1(View view) {

        int imageID = (int) findViewById(R.id.item1).getTag();
        inventory.addItem(imageID);
        inventory.displayInventory();
    }

    public void purchase2(View view) {
        int imageID = (int) findViewById(R.id.item2).getTag();
        inventory.addItem(imageID);
        inventory.displayInventory();
    }

    public void purchase3(View view) {
        int imageID = (int) findViewById(R.id.item3).getTag();
        inventory.addItem(imageID);
        inventory.displayInventory();
    }

    public void purchase4(View view) {
        int imageID = (int) findViewById(R.id.item4).getTag();
        inventory.addItem(imageID);
        inventory.displayInventory();
    }

    public void purchase5(View view) {
        int imageID = (int) findViewById(R.id.item5).getTag();
        inventory.addItem(imageID);
        inventory.displayInventory();
    }


}
