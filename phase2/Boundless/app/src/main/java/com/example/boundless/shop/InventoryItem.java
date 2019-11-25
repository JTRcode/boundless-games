package com.example.boundless.shop;

import com.example.boundless.games.GamesEnum;

/**
 * An item that can be placed in the user's inventory
 */
public class InventoryItem {
    private String description;
    private int imageId;
    private GamesEnum game;
    private Runnable action;
    private boolean isUsedImmediately = false;

    /**
     * A new inventory item
     *
     * @param description A description of the item
     * @param imageId     The resource id of the image for the item
     * @param game        The game the item belongs to
     * @param action      The action occurring when the item is used
     */
    InventoryItem(String description, int imageId, GamesEnum game, Runnable action) {
        this.description = description;
        this.imageId = imageId;
        this.game = game;
        this.action = action;
    }

    /**
     * Set the action to be used immediately after purchasing it
     */
    public void setImmediate() {
        this.isUsedImmediately = true;
    }

    /**
     * Get if the action should be used immediately after purchasing
     *
     * @return If the action should be used immediately after purchasing
     */
    public boolean isImmediate() {
        return isUsedImmediately;
    }

    /**
     * Use the item
     */
    public void useItem() {
        action.run();
        if (!isUsedImmediately) ShopInventory.deleteItem(imageId);
    }

    /**
     * Check if the item is part of the game
     *
     * @param game The game to check if this item is part of
     * @return If the item is part of the game
     */
    public boolean isPartOfGame(GamesEnum game) {
        return this.game == game;
    }

    /**
     * Get the image id of the associated image
     *
     * @return The associated image id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Get the description of this item
     *
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }
}
