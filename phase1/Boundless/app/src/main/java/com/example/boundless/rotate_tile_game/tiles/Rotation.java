package com.example.boundless.rotate_tile_game.tiles;

import java.util.HashMap;
import java.util.Map;

public enum Rotation {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    public static Rotation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    private int value;
    private static Map map = new HashMap<>();

    Rotation(int value) {
        this.value = value;
    }

    static {
        for (Rotation rotation : Rotation.values()) {
            map.put(rotation.value, rotation);
        }
    }

    public static Rotation valueOf(int pageType) {
        return (Rotation) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
