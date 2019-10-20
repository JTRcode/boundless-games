package com.example.boundless;

import com.example.boundless.PixelGame.PixelManager;
import com.example.boundless.PixelGame.PixelOptions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PixelManagerTests {
    private PixelManager pixelManager = new PixelManager(3);
    private int[][] level1 = {{0, 3, 0},
            {5, 0, 2},
            {1, 8, 10}};

    @Test
    public void labels_areCorrect() {
        //set up expected value
        List<List<Integer>> expected = new ArrayList<>();
        Integer[][] integers = {{1}, {1, 1}, {3}, {2}, {1, 1}, {2}};
        for (int i = 0; i < 3 * 2; i++) {
            List<Integer> intList = Arrays.asList(integers[i]);
            expected.add(intList);
        }
        //set up actual result
        pixelManager.addLevel(level1);
        List<List<Integer>> result = pixelManager.label(0);
        assertThat(result, is(expected));
    }

    @Test
    public void checkPixels_correctAnswer() {
        pixelManager.addLevel(level1);
        PixelOptions[][] userChoices = {
                {PixelOptions.X, PixelOptions.COLOUR, PixelOptions.EMPTY},
                {PixelOptions.COLOUR, PixelOptions.X, PixelOptions.COLOUR},
                {PixelOptions.COLOUR, PixelOptions.COLOUR, PixelOptions.COLOUR}
        };
        assertTrue(pixelManager.checkPixels(userChoices, 0));
    }

    @Test
    public void checkPixels_incorrectAnswer() {
        pixelManager.addLevel(level1);
        PixelOptions[][] userChoices = {
                {PixelOptions.X, PixelOptions.COLOUR, PixelOptions.EMPTY},
                {PixelOptions.COLOUR, PixelOptions.X, PixelOptions.COLOUR},
                {PixelOptions.COLOUR, PixelOptions.COLOUR, PixelOptions.X}
        };
        assertFalse(pixelManager.checkPixels(userChoices,0));
    }
}
