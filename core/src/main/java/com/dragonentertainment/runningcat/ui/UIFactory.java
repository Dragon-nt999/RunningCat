package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.ScreenType;

public class UIFactory {
    public static BaseUI createUI(AppGame game, ScreenType screen) {
        switch (screen){
            case GAME:
                return new GameUI(game);
            case HOME:
                return new HomeUI(game);
            default:
                throw new IllegalArgumentException("Unknown Screen: " + screen);
        }
    }
}
