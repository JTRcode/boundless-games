package com.example.boundless.games;

public class PixelInstructions extends PixelGameFacade {

    PixelInstructions(GameBuilder builder) {
        super(builder);
    }

    @Override
    String getInstructions() {
        return "";
    }

    @Override
    String getGameOverText() {
        return "Good job! This is the end of the tutorial.\nClick ok to exit.";
    }
}

