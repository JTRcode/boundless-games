package com.example.boundless.rotate_tile_game;

public class HardCodeSetUps {
    //The set-up for first level, nulls represent that any Tile can be placed
    public static TileEnum[][] game1_null =
                    {{TileEnum.I, TileEnum.L, null, null, null},
                    {TileEnum.L, TileEnum.X, null, null, null},
                    {TileEnum.I, null,TileEnum.T, TileEnum.L, null},
                    {TileEnum.T, null, TileEnum.I, TileEnum.L, TileEnum.X},
                    {TileEnum.X, TileEnum.I, TileEnum.L, null,TileEnum.L}};
    public static TileEnum[][] game1 =
            {{TileEnum.I, TileEnum.L, TileEnum.I, TileEnum.I, TileEnum.I},
                    {TileEnum.L, TileEnum.X, TileEnum.L, TileEnum.T, TileEnum.I},
                    {TileEnum.I,TileEnum.I,TileEnum.T, TileEnum.L, TileEnum.L},
                    {TileEnum.T, TileEnum.L, TileEnum.I, TileEnum.L, TileEnum.X},
                    {TileEnum.X, TileEnum.I, TileEnum.L, TileEnum.X,TileEnum.L}};
    //TODO: eventually, would be cool to make these null values choose a random tile and generate "random" maps that way



    /**
     * Initializes the tiles array and randomizes the rotation (easy mode).
     */
    public char[][] setUpTilesEasy() {
        return new char[][]{
                {'L', 'L', 'I', 'X'},
                {'L', 'I', 'I', 'L'},
                {'X', 'L', 'T', 'I'},
                {'L', 'I', 'I', 'L'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (medium mode).
     */
    public char[][] setUpTilesMedium() {
        return new char[][] {
                {'L', 'L', 'I', 'X', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'X', 'L', 'T', 'I', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'},
                {'L', 'I', 'I', 'L', 'L', 'X'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (hard mode).
     */
    public char[][] setUpTilesHard() {
        return new char[][]{
                {'L', 'L', 'L', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I'},
                {'L', 'L', 'L', 'I', 'I', 'I', 'I', 'I'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (expert mode), i.e. play all levels
     * and an expert stage.
     */
    public char[][] setUpTilesExpert() {
        return new char[][] {
                {'L', 'L', 'L', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'I', 'I', 'I', 'X', 'T', 'I', 'I', 'I', 'I', 'I'},
                {'L', 'L', 'L', 'I', 'I', 'I', 'I', 'I', 'I', 'I'}};
    }

}
