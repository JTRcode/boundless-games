package com.example.boundless.games.rotate_tile_game.stage_creation;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FindSolution {

    private static final String TAG = "TileGame_FindSolution";
    private int[][] pastLocation;
    private int gridLength;
    private DifficultyEnum difficulty;
    private List<Integer> pathToFinish;
    private int[] end;

    FindSolution(int[] start, int[] end, int gridLength, DifficultyEnum difficulty) {
        pastLocation = new int[gridLength][gridLength];
        pathToFinish = new ArrayList<>();
        this.difficulty = difficulty;
        this.end = end;
        this.gridLength = gridLength;
        while (!path(start, pastLocation, 1)) {
            pastLocation = new int[gridLength][gridLength];
        }
        System.out.println(pathToFinish);
    }

    private boolean isOnFinishGrid(int[] position) {
        return (position[0] == end[0] && position[1] == end[1]);
    }

    private boolean path(int[] location, int[][] pastLocation, int steps) {
        if (outOfRange(location) || hasBeenToBefore(location)) {
            return false;
        } else if (isOnFinishGrid(location) && isDifficultEnough(steps)) {
            pastLocation[location[1]][location[0]] = 1;
            this.pastLocation = pastLocation;
            return true;
        }
        pastLocation[location[1]][location[0]] = 1;
        printPastLocation(pastLocation);
        ArrayList<Integer> choices = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Log.d(TAG, "Size of choices is +" + choices.size());
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(choices.size());
            int move = choices.get(index);
            Log.d(TAG, "MOVE is " + move);
            choices.remove(index);
            int[] newLocation = location.clone();
            calculateNewCoord(newLocation, move);
            if (path(newLocation, pastLocation.clone(), steps + 1)) {
                pathToFinish.add(0, move);
                return true;
            }
        }
        return false;
    }

    private boolean isDifficultEnough(int steps) {
        int minMoves;
        switch (difficulty) {
            case HARD:
                minMoves = gridLength * 4;
                break;
            case MEDIUM:
                minMoves = gridLength * 3;
                break;
            case EASY:
            default:
                minMoves = 0;
                break;
        }
        return steps >= minMoves;
    }

    private boolean hasBeenToBefore(int[] position) {
        return pastLocation[position[1]][position[0]] == 1;
    }

    private void printPastLocation(int[][] pastLocation) {
        Log.d(TAG, "Grid of Past locations");
        for (int[] row : pastLocation) {
            for (int value : row) {
                System.out.print(value);
            }
            System.out.println();
        }
        System.out.print(pathToFinish);
    }

    private boolean outOfRange(int[] location) {
        for (int xy_position : location)
            if (xy_position >= gridLength || xy_position < 0) {
                return true;
            }
        return false;
    }

    void calculateNewCoord(int[] coord, int move) {
        switch (move) {
            case 0:
                coord[1] -= 1;
                break;
            case 1:
                coord[0] += 1;
                break;
            case 2:
                coord[1] += 1;
                break;
            case 3:
                coord[0] -= 1;
                break;
            default:
        }
    }

    List<Integer> getPathToFinish() {
        return pathToFinish;
    }

}
