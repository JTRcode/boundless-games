package com.example.boundless;

import com.example.boundless.PixelGame.PixelManager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PixelManagerTests {
    private PixelManager pixelManager = new PixelManager();
    private int[][] level1 = {{0, 1, 0},
            {1, 0, 1},
            {1, 1, 1}};

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
        List<List<Integer>> result = pixelManager.label(level1);
        assertThat(result, is(expected));
    }
}
