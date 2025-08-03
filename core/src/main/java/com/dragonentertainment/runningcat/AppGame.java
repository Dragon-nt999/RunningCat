package com.dragonentertainment.runningcat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.screens.LoadingScreen;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.GameData;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class AppGame extends Game {
    public AssetManager assetManager;
    public boolean playerIsInjured = false;
    private LoadingScreen loadingScreen;

    @Override
    public void create() {
        this.assetManager = new AssetManager();
        // Create allPosition on Game Grid
        GameGrid.allPosition();

        ScreenType type = ScreenType.HOME;
        if(GameData.getInstance().getAttempts() <= 0) {
            type = ScreenType.GAME;
        }

        AssetLoader.loadHomeScreenAssets(this.assetManager);
        AssetLoader.loadGameScreenAssets(this.assetManager);

        this.loadingScreen = new LoadingScreen(this, type);
        this.setScreen(this.loadingScreen);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.unloadHomeScreenAssets(this.assetManager);
        AssetLoader.unloadGameScreenAssets(this.assetManager);
        this.assetManager.dispose();
        this.loadingScreen.dispose();
    }
}
