package com.example.boundless;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

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


}
