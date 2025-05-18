package com.dragonentertainment.runningcat;

import com.badlogic.gdx.Game;
import com.dragonentertainment.runningcat.screens.GameScreen;

public class AppGame extends Game {

    private GameScreen gameScreen;
    @Override
    public void create() {
        this.gameScreen = new GameScreen(this);
        this.setScreen(this.gameScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.gameScreen.dispose();
    }
}
