package com.example.boundless;

import com.example.boundless.games.rotate_tile_game.stage_creation.DifficultyEnum;
import com.example.boundless.games.rotate_tile_game.stage_creation.RandomLevelGeneration;
import com.example.boundless.games.rotate_tile_game.stage_creation.RotateStageBuilder;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class testing {

    @Test
    public void useAutoGeneration() {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(0);
        int[] value = {0,0};
        int[] testValue = {0,0};
        if (test.contains(0)){
            System.out.println("Yes");
        }else{
            System.out.println("NO");
        }

        RandomLevelGeneration level;
        RotateStageBuilder builder = new RotateStageBuilder();
        level = new RandomLevelGeneration(builder.setGridLength(3).setDifficulty(DifficultyEnum.EASY));
        System.out.println("FInsihed");
        //level.construct();

    }

}
