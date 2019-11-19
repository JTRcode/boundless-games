package com.example.boundless.games.rotate_tile_game;

/**
 * Holds the hardcoded levels for the Tile Game
 */
class HardCodeSetUps {

    private HardCodeSetUps(){}

    /**
     * Get the given level from the game
     *
     * @param level The level to get
     * @return The level
     */
    public static TileLevel getLevel(int level) {
        char[][] layout;
        switch (level) {
            case 0:
                layout = setUpTilesEasy();
                break;
            case 1:
                layout = setUpTilesMedium();
                break;
            case 2:
                layout = setUpTilesHard();
                break;
            case 3:
                layout = setUpTilesExpert();
                break;
            default:
                layout = setUpTilesIntro();
                break;
        }
        TileLevel tileLevel = new TileLevel();
        tileLevel.setTiles(layout);
        return tileLevel;
    }

    private static char[][] setUpTilesEasy() {
        return new char[][]{
                {'L', 'A', 'A', 'A'},
                {'T', 'I', 'T', 'L'},
                {'A', 'A', 'A', 'I'},
                {'A', 'A', 'A', 'L'}};
    }

    private static char[][] setUpTilesMedium() {
        return new char[][] {
                {'L', 'A', 'A', 'A', 'A', 'A'},
                {'T', 'I', 'T', 'L', 'A', 'A'},
                {'A', 'A', 'A', 'I', 'A', 'A'},
                {'A', 'A', 'A', 'L', 'L', 'A'},
                {'A', 'A', 'A', 'A', 'L', 'T'},
                {'A', 'A', 'A', 'A', 'A', 'L'}};
    }

    private static char[][] setUpTilesHard() {
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

    private static char[][] setUpTilesExpert() {
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

    private static char[][] setUpTilesIntro() {
        return new char[][]{
                {'L', 'L'},
                {'L', 'L'}};
    }

}
