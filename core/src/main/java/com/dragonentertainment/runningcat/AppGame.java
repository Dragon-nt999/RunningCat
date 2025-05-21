package com.dragonentertainment.runningcat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.dragonentertainment.runningcat.Enum.ScreenType;
import com.dragonentertainment.runningcat.screens.GameScreen;
import com.dragonentertainment.runningcat.screens.LoadingScreen;

public class AppGame extends Game {
    public AssetManager assetManager;
    private LoadingScreen loadingScreen;

    @Override
    public void create() {
        this.assetManager = new AssetManager();
        this.loadingScreen = new LoadingScreen(this, ScreenType.GAME);
        this.setScreen(this.loadingScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.assetManager.dispose();
        this.loadingScreen.dispose();
    }
}
