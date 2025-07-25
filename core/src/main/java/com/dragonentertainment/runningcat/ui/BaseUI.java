package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dragonentertainment.runningcat.AppGame;

public abstract class BaseUI {
    protected final Stage stage;
    protected final AppGame game;
    public BaseUI(AppGame game){
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.init();
    }

    protected abstract void init();

    public void draw() {
        this.stage.act();
        this.stage.draw();
    }

    public void resize(){
        this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void dispose() {
        this.stage.dispose();
    }

    public Stage getStage() {
        return this.stage;
    }
}
