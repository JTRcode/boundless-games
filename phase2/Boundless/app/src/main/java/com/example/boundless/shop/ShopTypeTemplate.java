package com.example.boundless.shop;

import java.util.List;

/**
 * Holds the info for a given shop
 */
public abstract class ShopTypeTemplate {
    /**
     * A list of the items in this shop
     */
    List<InventoryItem> shopItems;

    /**
     * The image that the item will have
     *
     * @return A list of resource ids
     */
    public abstract int[] itemsImage();

    /**
     * The description that the items will have
     *
     * @return A list of descriptions matching to each item
     */
    public abstract String[] itemsDescription();
}
