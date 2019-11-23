package com.example.boundless.games.rotate_tile_game.tiles;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * An individual tile that can be rotated.
 */
public abstract class Tile implements Cloneable {

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
     */
    public void resize(int newDimension) {
        originalImage = Bitmap.createScaledBitmap(originalImage, newDimension, newDimension, true);
        rotatedImage = Bitmap.createScaledBitmap(rotatedImage, newDimension, newDimension, true);
    }

    Tile(int[] exits, Bitmap originalImage) {
        this.exits = exits;
        this.originalImage = originalImage;
        rotatedImage = originalImage;
        setTile(Rotation.getRandom());
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
