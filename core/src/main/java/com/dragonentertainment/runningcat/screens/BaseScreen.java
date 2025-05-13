package com.dragonentertainment.runningcat.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.utils.GameGrid;

public abstract class BaseScreen implements Screen {
    protected final AppGame game;
    protected final OrthographicCamera camera;
    protected final Viewport viewport;
    //public static final float WORLD_WIDTH = GameGrid.GRID_WIDTH;
    //public static final float WORLD_HEIGHT = GameGrid.GRID_HEIGHT;

    public BaseScreen(final AppGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(GameGrid.GRID_WIDTH, GameGrid.GRID_HEIGHT, this.camera);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.viewport.apply();

        this.camera.position.set(GameGrid.GRID_WIDTH / 2f, GameGrid.GRID_HEIGHT / 2f, 0f);
        this.camera.update();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
