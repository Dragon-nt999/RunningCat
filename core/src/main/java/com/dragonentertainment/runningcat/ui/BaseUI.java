package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.utils.GameGrid;

public abstract class BaseUI {
    protected final Stage stage;
    protected final AppGame game;
    public BaseUI(AppGame game){
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.init();
    }

    protected abstract void init();
    protected abstract void update(float deltaTime);

    public void draw(float delta) {
        this.stage.getViewport().apply(true);
        this.stage.act();
        this.stage.draw();
        this.update(delta);
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
