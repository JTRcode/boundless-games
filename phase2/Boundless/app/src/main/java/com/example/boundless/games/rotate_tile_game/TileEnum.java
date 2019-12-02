package com.example.boundless.games.rotate_tile_game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum TileEnum {
    I('I'),
    T('T'),
    L('L'),
    X('X'),
    ANY('A');

    private char value;
    private static Map map = new HashMap<>();

    TileEnum(char value) {
        this.value = value;
    }

    static {
        for (TileEnum type : TileEnum.values()) {
            map.put(type.value, type);
        }
    }

    /**
     * Converts a char to TileEnum
     * @param tileType
     * @return
     */
    public static TileEnum valueOf(char tileType) {
        if (tileType == 'A')
            return getRandom();
        return (TileEnum) map.get(tileType);
    }

    /**
     * returns a random TileEnum
     *
     * @return
     */
    public static TileEnum getRandom() {
        Random r = new Random();
        float xChance = r.nextFloat();
        TileEnum randomTile = values()[r.nextInt(4)];

        if (xChance < 0.5)
            while(randomTile == X)
                randomTile = values()[r.nextInt(4)];
        if (xChance < 0.7)
            while (randomTile == T || randomTile == X)
                randomTile = values()[r.nextInt(4)];
        return randomTile;
    }
}
