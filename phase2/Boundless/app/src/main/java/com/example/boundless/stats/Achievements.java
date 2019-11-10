package com.example.boundless.stats;

public class Achievements {

    private static boolean maxGpaAchieve = false;
    private static boolean failGpaAchieve = false;

    private static int numFailsPixel;
    public static boolean pixelPlayed = false;

    private static int numFailsRotate;
    public static boolean rotatePlayed = false;


    public static void setMaxGpaAchieve() {
        maxGpaAchieve = true;
    }

    public static void setFailGpaAchieve() {
        failGpaAchieve = true;
    }

    private static String maxGpaCompleted() {
        if (maxGpaAchieve) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }

    private static String failGpaCompleted() {
        if (failGpaAchieve) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }

    static public void sumNumFailsPixel() {
        numFailsPixel += 1;
    }

    private static String noFailsPixelCompleted() {
        if (numFailsPixel == 0 && pixelPlayed) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }

    private static String fiveFailsPixelCompleted() {
        if (numFailsPixel >= 5) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }


    static public void sumNumFailsRotate() {
        numFailsRotate += 1;
    }

    private static String noFailsRotateCompleted() {
        if (numFailsRotate == 0 && rotatePlayed) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }

    private static String fiveFailsRotateCompleted() {
        if (numFailsRotate >= 5) {
            return "Achieved!";
        }
        else {
            return "Not Completed.";
        }
    }

    //TODO: Add above method calls into each game
    public static String printAchievements() {
        return "Your Achievements:" + "\n" +
                "GPA Catcher Game" + "\n" +
                "Achieve a 4.0 GPA: " + maxGpaCompleted() + "\n" +
                "Achieve a 0.0 GPA: " + failGpaCompleted() + "\n" +
                "Pixel Game" + "\n" +
                "Get the correct answer on your first try: " + "\n" + noFailsPixelCompleted() +"\n" +
                "Get the incorrect answer five or more times: " + "\n" + fiveFailsPixelCompleted() + "\n" +
                "Rotate Tile Game" + "\n" +
                "Get the correct answer on your first try: " + "\n" +  noFailsRotateCompleted() + "\n" +
                "Get the incorrect answer five or more times: " + "\n" + fiveFailsRotateCompleted() + "\n";
    }

}