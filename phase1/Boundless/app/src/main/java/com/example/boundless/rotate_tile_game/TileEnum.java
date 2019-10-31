package com.example.boundless.rotate_tile_game;

import java.util.HashMap;
import java.util.Map;

public enum TileEnum {
    I('I'),
    T('T'),
    X('X'),
    L('L');


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

    public static TileEnum valueOf(char tileType) {
        return (TileEnum) map.get(tileType);
    }

    public char getValue() {
        return value;
    }
}
