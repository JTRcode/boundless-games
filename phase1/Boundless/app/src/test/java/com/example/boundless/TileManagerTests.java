package com.example.boundless;

import com.example.boundless.RotateTileGame.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileManagerTests {
    private TileManager tileManager = new TileManager(4);

    @Test
    public void gameOver_correctAnswer() {
        char[][] levelChars = {
                {'L', 'I', 'I', 'T'},
                {'I', 'L', 'L', 'I'},
                {'I', 'L', 'T', 'T'},
                {'L', 'I', 'I', 'I'}
        };
        Tile[][] levelTiles = tileManager.convertCharToTile(levelChars, 4);
        tileManager.randomizeTiles(levelTiles);
        levelTiles[0][0].setTile(Rotation.SOUTH);
        levelTiles[1][0].setTile(Rotation.NORTH);
        levelTiles[2][0].setTile(Rotation.NORTH);
        levelTiles[3][0].setTile(Rotation.NORTH);
        levelTiles[3][1].setTile(Rotation.EAST);
        levelTiles[3][2].setTile(Rotation.WEST);
        levelTiles[3][3].setTile(Rotation.EAST);

        assertTrue(tileManager.gameOver(levelTiles));
    }

    @Test
    public void gameOver_correctAnswerComplex() {
        char[][] levelChars = {
                {'L', 'L', 'X', 'X'},
                {'I', 'I', 'I', 'I'},
                {'I', 'L', 'X', 'X'},
                {'L', 'T', 'T', 'L'}
        };
        Tile[][] levelTiles = tileManager.convertCharToTile(levelChars, 4);
        tileManager.randomizeTiles(levelTiles);

        levelTiles[0][0].setTile(Rotation.SOUTH);
        levelTiles[1][0].setTile(Rotation.NORTH);
        levelTiles[2][0].setTile(Rotation.SOUTH);
        levelTiles[3][0].setTile(Rotation.NORTH);

        levelTiles[0][1].setTile(Rotation.EAST);
        levelTiles[1][1].setTile(Rotation.SOUTH);
        levelTiles[2][1].setTile(Rotation.EAST);
        levelTiles[3][1].setTile(Rotation.WEST);

        levelTiles[1][2].setTile(Rotation.NORTH);
        levelTiles[3][2].setTile(Rotation.SOUTH);

        levelTiles[1][3].setTile(Rotation.NORTH);
        levelTiles[3][3].setTile(Rotation.NORTH);

        assertTrue(tileManager.gameOver(levelTiles));
    }

    /*@Test
    public void calculateNewCoord_test(){
        int currentX = 1;
        int currentY = 1;
        for (int exit = 0; exit < 4; exit ++) {
            int newX, newY;
            newX = tileManager.calculateNewCoord(currentX, exit, 0);
            newY = tileManager.calculateNewCoord(currentY, exit, 1);
            System.out.println("from ("+currentX+","+currentY+") on exit "+exit+" to ("+newX+","+newY+")");
        }
        int newX = tileManager.calculateNewCoord(1, 0, 0);
    }*/

    @Test
    public void gameOver_incorrectAnswer() {
        char[][] levelChars = {
                {'I', 'I', 'I', 'T'},
                {'L', 'L', 'L', 'I'},
                {'L', 'L', 'T', 'T'},
                {'I', 'I', 'L', 'L'}
        };

        Tile[][] levelTiles = tileManager.convertCharToTile(levelChars, 4);
        tileManager.randomizeTiles(levelTiles);
        levelTiles[3][3].setTile(Rotation.EAST);

        assertFalse(tileManager.gameOver(levelTiles));
    }
}
