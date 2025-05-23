package com.dragonentertainment.runningcat.screens;

import com.badlogic.gdx.Gdx;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.Enum.ScreenType;
import com.dragonentertainment.runningcat.utils.AssetLoader;

public class LoadingScreen extends BaseScreen {
    private final ScreenType targetScreen;
    public LoadingScreen(AppGame game, ScreenType screenType) {
        super(game);
        this.targetScreen = screenType;
        switch (this.targetScreen) {
            case HOME:
                break;
            case GAME:
                AssetLoader.loadGameScreenAssets(this.game.assetManager);
                break;
            default:
                break;
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if(this.game.assetManager.update()) {
            switch (this.targetScreen){
                case HOME:
                    break;
                case GAME:
                    this.game.setScreen(new GameScreen(this.game));
                    break;
                default:
                    break;
            }

        } else {
            float progress = this.game.assetManager.getProgress();
        }
    }

}
