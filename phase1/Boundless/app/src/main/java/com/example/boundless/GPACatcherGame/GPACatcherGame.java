package com.example.boundless.GPACatcherGame;

import com.example.boundless.Game;

/**
 * A GPA catcher game, where you catch falling objects to get a good grade!
 */
public class GPACatcherGame extends Game {

    static double gpa; // current GPA
    static int life; // current life remaining (max 3)
    static int time; // current time remaining  (overall time to be determined)
    //TODO

    static void addGpa(double gpa) {
        GPACatcherGame.gpa += gpa;
    }

    static void addLife(int life) {
        GPACatcherGame.life += life;
    }

    static void addTime(int time) {
        GPACatcherGame.time += time;
    }

    @Override
    public boolean gameOver() {
        //TODO
        return false;
    }
}
