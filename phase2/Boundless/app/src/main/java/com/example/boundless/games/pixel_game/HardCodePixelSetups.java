package com.example.boundless.games.pixel_game;

import android.graphics.Color;

/**
 * Holds the hardcoded levels for the pixel game
 */
public final class HardCodePixelSetups {

    private HardCodePixelSetups() {
    }

    /**
     * Get the given level from the game
     *
     * @param level The level to get
     * @return The level
     */
    public static PixelLevel getLevel(int level) {
        switch (level) {
            case 0:
                return hardCodeLevel1();
            case 1:
                return hardCodeLevel2();
            case 2:
                return hardCodeLevel3();
            case 3:
                return hardCodeLevel4();
            case 4:
                return hardCodeLevel5();
            case 5:
                return hardCodeLevel6();
            default:
                return introLevel();
        }
    }

    /**
     * The first level
     *
     * @return the picture of the first level
     */
    private static PixelLevel hardCodeLevel1() {
        int[][] heart = new int[10][10]; //The first level is a heart
        heart[1][2] = 1;
        heart[1][7] = 1;
        for (int i = 0; i < 3; i++) {
            heart[2][1 + i] = 1;
            heart[2][6 + i] = 1;
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 10; j++)
                heart[3 + i][j] = 1;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 8 - 2 * i; j++)
                heart[6 + i][1 + i + j] = 1;
        PixelLevel level = new PixelLevel();
        level.setPixels(heart);
        level.setColor(Color.rgb(226, 60, 121));
        return level;
    }

    /**
     * The second one
     *
     * @return the picture of second level
     */
    private static PixelLevel hardCodeLevel2() {
        int[][] android = new int[10][10];//the second level is android
        //The head
        android[0][4] = 1;
        android[0][5] = 1;
        //The body and the arm
        for (int i = 0; i < 4; i++)
            android[1][3 + i] = 1;
        for (int i = 0; i < 3; i++) {
            android[4 + i][0] = 1;
            android[4 + i][9] = 1;
        }
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
                android[2 + i][2 + j] = 1;
        android[3][1] = 1;
        android[3][8] = 1;
        android[2][3] = 0;
        android[2][6] = 0;

        //The leg
        android[8][3] = 1;
        android[8][6] = 1;

        PixelLevel level = new PixelLevel();
        level.setPixels(android);
        level.setColor(Color.GREEN);
        return level;
    }

    private static PixelLevel hardCodeLevel3() {
        int[][] panda = new int[10][10]; // the third one is panda
        for (int i = 0; i < 2; i++) {
            panda[0][i] = 1;
            panda[1][i] = 1;
            panda[0][8 + i] = 1;
            panda[1][8 + i] = 1;
            panda[4][2 + i] = 1;
            panda[4][6 + i] = 1;
            panda[5][1 + i] = 1;
            panda[5][4 + i] = 1;
            panda[5][7 + i] = 1;
            panda[6][2 + i] = 1;
            panda[6][6 + i] = 1;
            panda[7][4 + i] = 1;
        }
        panda[1][2] = 1;
        panda[9][2] = 1;
        for (int j = 0; j < 5; j++) {
            panda[1][3 + j] = 1;
            panda[9][3 + j] = 1;
            panda[3 + j][0] = 1;
            panda[3 + j][9] = 1;
        }
        panda[2][1] = 1;
        panda[2][8] = 1;
        panda[8][1] = 1;
        panda[8][8] = 1;

        PixelLevel level = new PixelLevel();
        level.setPixels(panda);
        level.setColor(Color.WHITE);
        return level;
    }

    /**
     * The hardest level
     *
     * @return the picture of the hardest level
     */
    private static PixelLevel hardCodeLevel4() {
        int[][] taiji = new int[10][10]; // The third one is taiji
        for (int i = 0; i < 4; i++) {
            taiji[0][3 + i] = 1;
            taiji[9][3 + i] = 1;
            taiji[3 + i][0] = 1;
            taiji[3 + i][9] = 1;
            taiji[5][1 + i] = 1;
        }
        for (int i = 0; i < 6; i++)
            taiji[1][2 + i] = 1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 6; j++)
                taiji[i + 2][1 + j] = 1;
        for (int i = 0; i < 2; i++) {
            taiji[6 + i][1] = 1;
            taiji[2 + i][8] = 1;
        }
        taiji[8][2] = 1;
        taiji[8][7] = 1;
        taiji[7][6] = 1;
        taiji[7][8] = 1;
        taiji[2][3] = 0;

        PixelLevel level = new PixelLevel();
        level.setPixels(taiji);
        level.setColor(Color.WHITE);
        return level;
    }

    private static PixelLevel introLevel() {
        int[][] intro = new int[10][10];
        for (int i = 0; i < 10; i++) {
            intro[3][i] = 1;
            intro[4][i] = 1;
        }
        intro[4][4] = 0;
        PixelLevel level = new PixelLevel();
        level.setPixels(intro);
        level.setColor(Color.CYAN);
        return level;
    }
    private static PixelLevel hardCodeLevel5(){
        int[][] mickey = new int[12][12]; //level 5 is mickey mouse
        for(int i = 0; i<4; i++){
            mickey[1][3+i] = 1;
            mickey[2][3+i] = 1;
            mickey[0+i][4] = 1;
            mickey[0+i][5] = 1;
            mickey[4][8+i] = 1;
            mickey[5][8+i] = 1;
            mickey[3+i][9] = 1;
            mickey[3+i][10] = 1;
            mickey[6+i][2] = 1;
            mickey[10][3+i] = 1;
        }
        for(int i = 0; i<3; i++){
            mickey[4][4+i] = 1;
            mickey[6+i][1] = 1;
            mickey[6+i][8] = 1;
            mickey[5+i][0] = 1;
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                mickey[5+i][3+j] = 1;
            }
        }
        PixelLevel level = new PixelLevel();
        level.setPixels(mickey);
        level.setColor(Color.BLUE);
        return level;
    }

    private static PixelLevel hardCodeLevel6(){
        int[][] deer = new int[16][16]; //level 6 is a deer
        for(int i = 0; i < 6; i++){
            deer[3][9+i] = 1;
            deer[4][9+i] = 1;
        }
        for(int i = 0; i < 7; i++){
            deer[7][1+i] = 1;
            deer[10][1+i] = 1;
            deer[11][1+i] = 1;
            deer[5+i][9] = 1;
            deer[5+i][10] = 1;
        }
        for(int i = 0; i < 4; i++){
            deer[12+i][1] = 1;
            deer[12+i][3] = 1;
            deer[12+i][6] = 1;
            deer[12+i][10] = 1;
            deer[5][9+i] = 1;
            deer[12][1+i] = 1;
        }
        for(int i = 0; i < 3; i++){
            deer[7+i][8] = 1;
        }
        for(int i = 0; i < 2; i++){
            deer[6+i][0] = 1;
            deer[8+i][1] = 1;
            deer[8+i][7] = 1;
            deer[1][6+i] = 1;
            deer[1][12+i] = 1;
        }
        deer[0][5] = 1;
        deer[0][8] = 1;
        deer[0][11] = 1;
        deer[0][14] = 1;
        deer[2][8] = 1;
        deer[2][11] = 1;
        deer[2][15] = 1;
        deer[8][3] = 1;
        deer[8][5] = 1;
        deer[9][2] = 1;
        deer[9][4] = 1;
        deer[9][6] = 1;
        PixelLevel level = new PixelLevel();
        level.setPixels(deer);
        level.setColor(Color.YELLOW);
        return level;
    }

}
