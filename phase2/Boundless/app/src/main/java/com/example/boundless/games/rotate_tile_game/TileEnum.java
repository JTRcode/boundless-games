package com.example.boundless.games.rotate_tile_game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The enum for a type of tile
 */
public enum TileEnum {
    I('I'),
    T('T'),
    L('L'),
    X('X'),
    ANY('A');

    private char value;
    private static Map map = new HashMap<>();

    /**
     * A enum with a char value
     *
     * @param value The char value of the enum
     */
    TileEnum(char value) {
        this.value = value;
    }

    static {
        for (TileEnum type : TileEnum.values()) {
            map.put(type.value, type);
        }
    }

    /**
     * Get the tile enum related to a given char
     *
     * @param tileType The type of tile enum to get
     * @return The Tile enum associated with that character
     */
    public static TileEnum valueOf(char tileType) {
        if (tileType == 'A')
            return getRandom();
        return (TileEnum) map.get(tileType);
    }

    /**
     * Get the character value of a tile enum
     *
     * @return The character value of the enum
     */
    public char getValue() {
        return value;
    }

    /**
     * Gets a random tile enum
     *
     * @return A random tile enum
     */
    public static TileEnum getRandom() {
        Random r = new Random();
        float xChance = r.nextFloat();
        TileEnum randomTile = values()[r.nextInt(4)];

        if (xChance < 0.5)
            while (randomTile == X)
                randomTile = values()[r.nextInt(4)];
        if (xChance < 0.7)
            while (randomTile == T || randomTile == X)
                randomTile = values()[r.nextInt(4)];
        return randomTile;
    }
}
