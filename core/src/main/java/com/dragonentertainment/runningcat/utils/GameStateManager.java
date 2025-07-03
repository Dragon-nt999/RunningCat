package com.dragonentertainment.runningcat.utils;

import com.dragonentertainment.runningcat.enums.GameState;

public class GameStateManager {

    private static final GameStateManager instance = new GameStateManager();

    private GameState currentState = GameState.PLAYING;

    private GameStateManager(){}

    public static GameStateManager getInstance(){
        return instance;
    }

    public GameState getState() {
        return this.currentState;
    }

    public void setState(GameState newState) {
        this.currentState = newState;
    }

    public boolean is(GameState state) {
        return this.currentState == state;
    }
}
