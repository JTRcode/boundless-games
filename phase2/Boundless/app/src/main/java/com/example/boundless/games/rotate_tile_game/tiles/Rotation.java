package com.example.boundless.games.rotate_tile_game.tiles;

import java.util.HashMap;
import java.util.Map;

/**
 * An enum representing the rotation of a tile
 */
public enum Rotation {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    private int value;
    private static Map map = new HashMap<>();

    /**
     * A rotation with an integer rotation value
     *
     * @param value The degree value of the enum
     */
    Rotation(int value) {
        this.value = value;
    }

    /**
     * Get a random rotation
     *
     * @return A random rotation
     */
    public static Rotation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    static {
        for (Rotation rotation : Rotation.values()) {
            map.put(rotation.value, rotation);
        }
    }

    /**
     * Get the rotation based on the degree
     *
     * @param degrees The degree to get the rotation of
     * @return The rotation corresponding to the given degree
     */
    public static Rotation valueOf(int degrees) {
        return (Rotation) map.get(degrees);
    }

    /**
     * Get the degree amount associated with the value
     *
     * @return The degree corresponding to the rotation
     */
    public int getValue() {
        return value;
    }
}
