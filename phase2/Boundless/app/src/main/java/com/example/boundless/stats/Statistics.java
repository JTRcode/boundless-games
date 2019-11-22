package com.example.boundless.stats;

import com.example.boundless.games.GamesEnum;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * The statistics for a given game
 */
public class Statistics {

    private static long startTime;
    private static long startTimePixel;
    private static long startTimeRotate;

    private static long timeElapsedSeconds;
    private static int totalScore;
    private static int counterClicks;

    /**
     * Starts measuring time
     */
    public static void start() {
        startTime = new Date().getTime();
    }

    public static void startTimeByGame(GamesEnum gamesEnum) {

        if (gamesEnum == GamesEnum.PIXELS) {
            startTimePixel = new Date().getTime();
        }
        else if (gamesEnum == GamesEnum.ROTATETILE) {
            startTimeRotate = new Date().getTime();
        }
    }

    /**
     * Stops measuring time
     */
    public static void end() {
        long endTime = new Date().getTime();
        long timeElapsed = endTime - startTime;
        timeElapsedSeconds += TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
    }

    public static void endTimeByGame(GamesEnum gamesEnum) {

        if (gamesEnum == GamesEnum.PIXELS) {

            long timeElapsedSecondsPixel = 0;
            long endTime = new Date().getTime();
            long timeElapsed = endTime - startTimePixel;
            timeElapsedSecondsPixel += TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
            if (timeElapsedSecondsPixel <= 20) {
                Achievements.checkTime(gamesEnum, timeElapsedSecondsPixel);
            }
        } else if (gamesEnum == GamesEnum.ROTATETILE) {

            long timeElapsedSecondsRotate = 0;
            long endTime = new Date().getTime();
            long timeElapsed = endTime - startTimeRotate;
            timeElapsedSecondsRotate += TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
            if (timeElapsedSecondsRotate <= 20) {
                Achievements.checkTime(gamesEnum, timeElapsedSecondsRotate);
            }
        }
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
    public static void clickEvent() {
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
    public static String printStats() {
        return "Your Statistics:" + "\n" +
                "Total Time in Game (Seconds): " + timeElapsedSeconds + "\n" +
                "Total Points: " + totalScore + "\n" +
                "Total Number of Taps on Screen: " + counterClicks + "\n" +
                "Thanks for Playing!";
    }

}
