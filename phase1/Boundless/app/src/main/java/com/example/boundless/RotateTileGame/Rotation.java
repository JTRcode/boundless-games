package com.example.boundless.RotateTileGame;

public enum Rotation {
    NORTH, SOUTH, EAST, WEST;

    public static Rotation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
