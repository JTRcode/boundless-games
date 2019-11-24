package com.example.boundless.shop;

/**
 * Holds the info for a given shop
 */
public abstract class ShopTypeTemplate {
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
