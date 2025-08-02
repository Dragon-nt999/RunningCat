package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameData {
    private final static GameData instance = new GameData();
    private static final String PREF_NAME = "runningcat_game";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ATTEMPTS = "attempts";
    private static final String KEY_CAT_IS_INJURED = "is_injured";
    private static int ATTEMPTS = 0;
    private static int IS_INJURED = 0;
    private final Preferences prefs;

    public GameData() {
        prefs = Gdx.app.getPreferences(PREF_NAME);
    }

    public static GameData getInstance() {
        return instance;
    }

    public void saveScore(int score) {
        prefs.getInteger(KEY_SCORE, score);
        prefs.flush();
    }

    public int getScore() {
        return  prefs.getInteger(KEY_SCORE, 0);
    }

    public void increaseAttempts() {
        ATTEMPTS++;
        prefs.putInteger(KEY_ATTEMPTS, ATTEMPTS);
        prefs.flush();
    }

    public int getAttempts() {
        return prefs.getInteger(KEY_ATTEMPTS, 0);
    }

    public void clear(){
        prefs.clear();
        prefs.flush();
    }
    public void setCatIsInjured() {
        IS_INJURED = 1;
        prefs.putInteger(KEY_CAT_IS_INJURED, IS_INJURED);
        prefs.flush();
    }
    public boolean catIsInjured() {
        return prefs.getInteger(KEY_CAT_IS_INJURED, 0) == 1;
    }
}
