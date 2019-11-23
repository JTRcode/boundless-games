package com.example.boundless;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.boundless.games.BusinessContext;
import com.example.boundless.games.Game;
import com.example.boundless.games.GameCreator;
import com.example.boundless.games.GamesEnum;

import com.example.boundless.stats.Achievements;
import com.example.boundless.stats.Statistics;

/**
 * The panel to show the game through
 */
public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * The width of the screen
     */
    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * The height of the screen.
     */
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    private MainThread thread;
    private Game game;
    private GamesEnum gameEnum;
    private static SurfaceView instance;

    public Panel(Context context) {
        super(context);
    }

    public Panel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Panel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Tell if the game has finished.
     *
     * @return If the game is over
     */
    public boolean isGameOver() {
        return game.isGameOver();
    }

    /**
     * Get the current game on this panel
     *
     * @return The game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Choose the game the panel displays
     *
     * @param gameToPlay The enum of the game to play
     */
    public void chooseGame(GamesEnum gameToPlay, int level) {
        GameCreator gameCreator = new GameCreator();
        setupPanel();
        setFocusable(true);
        gameEnum = gameToPlay;
        game = gameCreator.createGame(gameToPlay, level);
        Statistics.start();
        Statistics.startTimeByGame(gameToPlay);
    }

    /**
     * Pauses the game
     */
    public void pause() {
        thread.setUpdate(false);
    }

    /**
     * Resumes the game
     */
    public void resume() {
        thread.setUpdate(true);
    }

    /**
     * Setup the panel with a transparent background.
     */
    private void setupPanel() {
        instance = this;
        instance.setZOrderOnTop(true);
        SurfaceHolder holder = instance.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);

        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    public static SurfaceView getPanel() {
        return instance;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (thread == null) setupPanel();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //Do nothing because there's nothing to do
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Statistics.end();
        try {
            thread.setRunning(false);
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deals with user input on the screen
     *
     * @param event The event of the user input
     * @return If the game subscribes to the event
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        game.screenTouched(event);
        return BusinessContext.subscribesToTouch(gameEnum);
    }

    /**
     * Update the items on the panel
     */
    public void update() {
        game.update();
        if (game.checkGameOver()) {
            Statistics.sumTotalScore();
        }
    }

    /**
     * Draws the game on the panel
     * @param canvas The canvas to draw on
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            game.draw();
        }
    }

}
