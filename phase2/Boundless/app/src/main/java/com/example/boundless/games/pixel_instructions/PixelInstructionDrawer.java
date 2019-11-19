package com.example.boundless.games.pixel_instructions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.boundless.Panel;
import com.example.boundless.R;
import com.example.boundless.games.game_utilities.*;
import com.example.boundless.games.pixel_game.PixelDrawer;
import com.example.boundless.games.pixel_game.PixelLevel;
import com.example.boundless.games.pixel_game.PixelOptions;
import com.example.boundless.utilities.DrawUtility;

public class PixelInstructionDrawer extends PixelDrawer implements IGridDrawer {

    private static int startX = GameResources.PIXEL_START_X;
    private int startY = GameResources.PIXEL_START_Y;
    private int width;
    private PixelLevel level;
    private Bitmap pointer;
    private int[] oldCoords = new int[]{-1, -1};
    private static int currentStep;
    private static int pointerX;
    private PixelOptions[][] userChoices;

    public PixelInstructionDrawer(IGridManager<PixelOptions, PixelLevel> manager, Resources res) {
        super(manager);
        currentStep = 0;
        pointerX = startX;
        userChoices = manager.getUserChoices();
        pointer = BitmapFactory.decodeResource(res, R.drawable.pointer);
        pointer = Bitmap.createScaledBitmap(pointer, 46 * 3, 80 * 3, true);
        level = manager.getLevel();
        width = level.getWidth(startX);
    }

    private void showTap() {
        if (pointerX > startX + width * 9) {
            drawString("Tap to continue", 10, startY + width * 15);
            return;
        }
        pointerX += 4;
        DrawUtility.drawBitmap(pointer, pointerX, (int) (startY + width * 3.5));
        int[] coordsIJ = convertCoordToIJ(pointerX + 45, (int) (startY + width * 3.5));
        if (oldCoords[0] == coordsIJ[0] && oldCoords[1] == coordsIJ[1]) return;
        switchPixel(coordsIJ[0], coordsIJ[1]);
        oldCoords = coordsIJ;
    }

    public static boolean readyForUser() {
        return currentStep > 4;
    }

    static void nextStep() {
        currentStep++;
        pointerX = startX;
    }

    @Override
    public void draw() {
        super.draw();
        int x = 10;
        int y = startY + width * 12;
        switch (currentStep) {
            case 0:
                drawString("Tap or drag along the grid to change\nthe tiles.", x, y);
                showTap();
                break;
            case 1:
                drawString("Tap or drag along the same pixels to\nindicate these are NOT selected.", x, y);
                showTap();
                break;
            case 2:
                drawString("Tap or drag along the same pixels one\nmore time to deselect them.", x, y);
                showTap();
                break;
            case 3:
                drawString("The labels tell how many consecutive\npixels should be filled. Eg. '10'\nmeans that the whole row is filled", x, y);
                drawString("Tap to continue", 10, startY + width * 15);
                break;
            case 4:
                drawString("See if you can finish the level on your\nown.", x, y);
                drawString("Tap to try it out", 10, startY + width * 15);
                break;
            default:
                break;
        }
        DrawUtility.drawString("PIXEL GAME TUTORIAL", 10, startY - 100, Color.WHITE, 45);
    }

    private void switchPixel(int i, int j) {
        switch (userChoices[i][j]) {
            case EMPTY: //empty, change to color
                userChoices[i][j] = PixelOptions.COLOUR;
                break;
            case COLOUR: //has color, change to X
                userChoices[i][j] = PixelOptions.X;
                break;
            case X: //has an X, change to empty
                userChoices[i][j] = PixelOptions.EMPTY;
                break;
            default:
                break;
        }
    }

    private void drawString(String text, int x, int y) {
        String[] lines = text.split("\n");
        for (int lineNum = 0; lineNum < lines.length; lineNum++) {
            DrawUtility.drawString(lines[lineNum], x + lineNum * 10, y + lineNum * 40, Color.WHITE, 40);
        }
    }

    private int[] convertCoordToIJ(int x, int y) {
        int[] newIJ = new int[2];
        newIJ[0] = (x - startX) / width;
        newIJ[1] = (y - startY) / width;
        return newIJ;
    }
}
