package com.example.boundless;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The statistics for a given game
 */
public class Statistics {

    private static long startTime;
    private static long timeElapsedSeconds;
    private static int totalScore;
    private static int counterClicks;

    /**
     * Starts measuring time
     */
    static void start() {
        startTime = new Date().getTime();
    }

    /**
     * Stops measuring time
     */
    static void end() {
        long endTime = new Date().getTime();
        long timeElapsed = endTime - startTime;
        timeElapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
    }

    /**
     * returns a random number between 400-600
     */
    private static int addRandomScore() {
        Random ran = new Random();
        return ran.nextInt(201) + 400;
    }

    /**
     * adds output of addRandomScore() to total score
     */
    public static void sumTotalScore() {
        totalScore += addRandomScore();;
    }

    /**
     * counts the number of clicks on the screen during the game
     */
    static void clickEvent() {
        counterClicks += 1;
    }

    /**
     * returns the total number of clicks
     */
    public static int totalClicks() {
        return counterClicks;
    }

    /**
     * returns string formatting the statistics (to be called at the end after all 3 games)
     */
    static String printStats() {
        return "Your Statistics (For All Games):" + "\n" +
                "Total App Runtime (Seconds): " + timeElapsedSeconds + "\n" +
                "Total Points: " + totalScore + "\n" +
                "Total Number of Taps on Screen: " + counterClicks + "\n" +
                "Thanks for Playing!";
    }

}
