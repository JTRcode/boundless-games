package com.example.boundless.stats;

import com.example.boundless.games.GamesEnum;
import com.example.boundless.games.pixel_game.PixelManager;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The achievements for a given game
 */
public class Achievements {

    public static boolean maxGpaAchieve = false;
    public static boolean threeGpaAchieve = false;
    public static boolean failGpaAchieve = false;

    public static int numPixelTaps = 0;
    public static boolean twentySecondsOrLessPixel = false;
    public static boolean pixelPlayed = false;

    public static int numRotateTaps = 0;
    public static boolean twentySecondsOrLessRotate = false;
    public static boolean rotatePlayed = false;


    public static void gpaChecker(double gpa) {
        Double gpa_holder = gpa;
        if (gpa_holder.equals(4.0)) {
            maxGpaAchieve = true;
        } else if (gpa_holder.equals(3.0)) {
            threeGpaAchieve = true;
        } else if (gpa_holder.equals(0.0)) {
            failGpaAchieve = true;
        }
    }

    static void checkTime(GamesEnum gamesEnum, long elapsedTime) {

        if (gamesEnum == GamesEnum.ROTATETILE) {
            if (elapsedTime <= 20) {
                twentySecondsOrLessRotate = true;
            }
        } else if (gamesEnum == GamesEnum.PIXELS) {
            if (elapsedTime <= 20) {
                twentySecondsOrLessPixel = true;
            }
        }
    }
}