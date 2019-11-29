package com.example.boundless.games.rotate_tile_instructions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.game_utilities.GameResources;
import com.example.boundless.games.game_utilities.GridManager;
import com.example.boundless.games.rotate_tile_game.RotateTileDrawer;
import com.example.boundless.games.rotate_tile_game.TileLevel;
import com.example.boundless.games.rotate_tile_game.tiles.Tile;
import com.example.boundless.utilities.DrawUtility;

/**
 * A drawer for the rotate tile game instructions
 */
public class RotateTileInstructionDrawer extends RotateTileDrawer {
    private int startX = GameResources.TILE_START_X;
    private int startY = GameResources.TILE_START_Y;
    private int pointerX;
    private int pointerY;
    private int width;
    private Bitmap pointer;
    private int currentStep = 0;
    private Tile[][] userChoices;
    private int length = 100;

    /**
     * A new RotateTileDrawer
     *
     * @param manager The manager for the Rotate Tile game
     */
    public RotateTileInstructionDrawer(GridManager<Tile, TileLevel> manager) {
        super(manager);
        pointerX = startX;
        pointerY = startY;
        userChoices = manager.getUserChoices();
        width = manager.getLevel().getTileWidth(startX);
        pointer = BitmapFactory.decodeResource(Panel.getPanel().getResources(), R.drawable.pointer);
        pointer = Bitmap.createScaledBitmap(pointer, 46 * 3, 80 * 3, true);
    }

    private void nextStep() {
        currentStep++;
        length = 100;
        pointerX = startX;
    }

    @Override
    public void draw() {
        super.draw();

        int x = startX;
        int y = startY + width * 5;
        switch (currentStep) {
            case 0:
                drawString("Tap a tile to rotate it.", x, y);
                if (length == 50) userChoices[0][0].rotateTile();
                pointerX = startX;
                pointerY = startY + width / 2;
                if (length-- < 0) nextStep();
                break;
            case 1:
                drawString("Start from one end and rotate\nthe tiles to get to the other", x, y);
                pointerX += 4;
                pointerY += 4;
                if (length-- < 0) nextStep();
                break;
            case 2:
                drawString("See if you can finish the level\non your own.", x, y);
                drawString("Tap to try it out", x, startY + width * 7);
                if (length-- < 0) nextStep();
                break;
            default:
                break;
        }
        if (currentStep < 2) DrawUtility.drawBitmap(pointer, pointerX, pointerY);
        DrawUtility.drawString("TILE GAME TUTORIAL", x, startY - 100, Color.WHITE, 45);
    }

    private void drawString(String text, int x, int y) {
        String[] lines = text.split("\n");
        for (int lineNum = 0; lineNum < lines.length; lineNum++) {
            DrawUtility.drawString(lines[lineNum], x + lineNum * 10, y + lineNum * 40, Color.WHITE, 40);
        }
    }
}
