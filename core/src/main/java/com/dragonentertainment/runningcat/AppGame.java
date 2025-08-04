package com.dragonentertainment.runningcat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.dragonentertainment.runningcat.ads.AdController;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.screens.LoadingScreen;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.GameData;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class AppGame extends Game {
    public AssetManager assetManager;
    public boolean playerIsInjured = false;
    private LoadingScreen loadingScreen;

    public AdController adController;

    public AppGame(AdController adController){
        this.adController = adController;
    }

    @Override
    public void create() {
        this.assetManager = new AssetManager();
        // Create allPosition on Game Grid
        GameGrid.allPosition();

        ScreenType type = ScreenType.HOME;
        if(GameData.getInstance().getAttempts() <= 0) {
            type = ScreenType.GAME;
        }

        AssetLoader.loadGameScreenAssets(this.assetManager);
        AssetLoader.loadHomeScreenAssets(this.assetManager);

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
        AssetLoader.unloadGameScreenAssets(this.assetManager);
        AssetLoader.unloadHomeScreenAssets(this.assetManager);
        this.assetManager.dispose();
        this.loadingScreen.dispose();
    }
}
