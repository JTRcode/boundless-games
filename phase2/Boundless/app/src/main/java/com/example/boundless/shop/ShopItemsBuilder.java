package com.example.boundless.shop;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.boundless.R;
import com.example.boundless.activities.ShopActivity;
import com.example.boundless.games.GamesEnum;

import java.util.List;

/**
 * Builds the shop items
 */
public class ShopItemsBuilder {
    private Activity activity;
    private ShopTypeTemplate shop;

    /**
     * The shop builder
     *
     * @param activity The current activity on the screen
     * @param game     The game to show the shop for
     */
    public ShopItemsBuilder(Activity activity, GamesEnum game) {
        this.activity = activity;
        if (game == null) {
            shop = new GameShop();
            return;
        }
        switch (game) {
            case PIXELS:
                shop = new PixelShop();
                break;
            case ROTATETILE:
                shop = new TileShop();
                break;
            case GPACATCHER:
                shop = new GpaShop();
                break;
            default:
                shop = new GameShop();
                break;
        }
    }

    private void buildImages(final ShopActivity shopActivity) {
        LinearLayout layout = activity.findViewById(R.id.scrollview_linear_layout);
        layout.removeAllViews();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopActivity.purchase(view);
            }
        };
        for (InventoryItem item : shop.shopItems) {
            TableRow tableRow = new TableRow(activity.getApplicationContext());
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.addView(createItemImage(item.getImageId(), onClickListener, 190));
            tableRow.addView(createItemDescription(item, onClickListener, 190));
            layout.addView(tableRow);
        }
    }

    private ImageButton createItemImage(int resourceId, OnClickListener onClickListener, int height) {
        ImageButton image = new ImageButton(activity.getApplicationContext());
        image.layout(10, 10, 0, 0);
        image.setOnClickListener(onClickListener);
        image.setTag(resourceId);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setBackgroundResource(resourceId);
        image.setLayoutParams(new TableRow.LayoutParams(0, height, 1.0f));
        return image;
    }

    private Button createItemDescription(InventoryItem item, OnClickListener onClickListener, int height) {
        Button itemDescription = new Button(activity.getApplicationContext());

        itemDescription.setLayoutParams(new TableRow.LayoutParams(0, height, 3.0f));
        itemDescription.layout(10, 10, 0, 0);
        itemDescription.setOnClickListener(onClickListener);
        itemDescription.setTag(item.getImageId());
        itemDescription.setText(item.getDescription());
        return itemDescription;
    }

    public List<InventoryItem> build(ShopActivity shopActivity) {
        buildImages(shopActivity);
        return shop.shopItems;
    }
}
