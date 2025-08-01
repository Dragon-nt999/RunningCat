package com.dragonentertainment.runningcat.screens;

import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.FontManager;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

public class LoadingScreen extends BaseScreen {
    private final ScreenType targetScreen;
    //private final FontManager fontManager;
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

    @Override
    protected void renderContent(float delta) {

    }

}
