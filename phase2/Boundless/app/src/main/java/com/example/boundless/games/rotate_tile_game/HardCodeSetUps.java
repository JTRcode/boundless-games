package com.example.boundless.games.rotate_tile_game;

/**
 * Holds the levels for the tilegame
 */
class HardCodeSetUps {
    private HardCodeSetUps(){}

    /**
     * Initializes the tiles array and randomizes the rotation (easy mode).
     */
    static char[][] setUpTilesEasy() {
        return new char[][]{
                {'L', 'A', 'A', 'A'},
                {'T', 'I', 'T', 'L'},
                {'A', 'A', 'A', 'I'},
                {'A', 'A', 'A', 'L'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (medium mode).
     */
    static char[][] setUpTilesMedium() {
        return new char[][] {
                {'L', 'A', 'A', 'A', 'A', 'A'},
                {'T', 'I', 'T', 'L', 'A', 'A'},
                {'A', 'A', 'A', 'I', 'A', 'A'},
                {'A', 'A', 'A', 'L', 'L', 'A'},
                {'A', 'A', 'A', 'A', 'L', 'T'},
                {'A', 'A', 'A', 'A', 'A', 'L'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (hard mode).
     */
    static char[][] setUpTilesHard() {
        return new char[][]{
                {'L', 'L', 'L', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'I', 'I', 'A', 'A', 'A', 'A', 'A'},
                {'T', 'I', 'I', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'T', 'I', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'I', 'T', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'I', 'I', 'A', 'A', 'T', 'L', 'A'},
                {'I', 'T', 'I', 'A', 'A', 'I', 'I', 'A'},
                {'L', 'L', 'L', 'I', 'I', 'L', 'I', 'I'}};
    }

    /**
     * Initializes the tiles array and randomizes the rotation (expert mode), i.e. play all levels
     * and an expert stage.
     */
    static char[][] setUpTilesExpert() {
        return new char[][] {
                {'L', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'T', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'L', 'T', 'T', 'L', 'A', 'A', 'A', 'A', 'A'},
                {'I', 'I', 'L', 'I', 'T', 'A', 'A', 'T', 'I', 'L'},
                {'I', 'I', 'I', 'A', 'A', 'A', 'A', 'I', 'A', 'I'},
                {'L', 'L', 'L', 'T', 'I', 'I', 'T', 'L', 'A', 'T'}};
    }

}
