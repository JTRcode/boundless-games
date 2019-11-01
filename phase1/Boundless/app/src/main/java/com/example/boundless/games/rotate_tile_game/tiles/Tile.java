package com.example.boundless.games.rotate_tile_game.tiles;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * An individual tile that can be rotated.
 */
public abstract class Tile {

    //TODO: add javadocs
    /**
     * Used in checking if the game is over.
     */
    public boolean visited = true;
    /**
     * The image of the tile, correctly rotated on the screen
     */
    public Bitmap rotatedImage;
    Bitmap originalImage;
    private int[] exits;
    private Rotation rotation;

    /**
     * Resizes the bitmap to fit it onto the grid
     *
     * @param newDimension The dimension to resize the bitmap to
     */
    void resize(int newDimension) {
        //TODO: should be taken out of the tile class, put into some drawing class
        originalImage = Bitmap.createScaledBitmap(rotatedImage, newDimension, newDimension, true);
    }

    Tile(int[] exits) {
        this.exits = exits;
        rotation = Rotation.getRandom();
    }

    /**
     * Get the exits of the tile.
     */
    public int[] getExits() {
        switch (rotation) {
            case EAST:
                return rotatedExit(3);
            case SOUTH:
                return rotatedExit(2);
            case WEST:
                return rotatedExit(1);
            default:
                return exits;
        }
    }

    private int[] rotatedExit(int newNorth) {
        int[] newExits = new int[4];
        int count = 0;
        for (int i = newNorth; i < newNorth + 4; i++) {
            newExits[count] = exits[i % 4];
            count++;
        }
        return newExits;
    }

    /**
     * Get the rotation of the tile.
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Set the tile rotation.
     *
     * @param rotation the rotation to set the tile to.
     */
    public void setTile(Rotation rotation) {
        this.rotation = rotation;
        rotateBitmap(rotation.getValue());
    }

    /**
     * Rotates the tile.
     */
    public void rotateTile() {
        rotation = Rotation.valueOf((rotation.getValue() + 90) % 360);
        setTile(rotation);
    }

    private void rotateBitmap(float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        rotatedImage = Bitmap.createBitmap(originalImage, 0, 0, originalImage.getWidth(),
                originalImage.getHeight(), matrix, true);
    }
}
