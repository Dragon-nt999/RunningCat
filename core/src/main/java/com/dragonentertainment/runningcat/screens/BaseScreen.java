package com.dragonentertainment.runningcat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.utils.GameGrid;

public abstract class BaseScreen implements Screen {
    protected final AppGame game;
    protected final OrthographicCamera camera;
    protected final Viewport viewport;
    protected final SpriteBatch batch;

    public BaseScreen(final AppGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(GameGrid.GRID_WIDTH, GameGrid.GRID_HEIGHT, this.camera);
        this.batch = new SpriteBatch();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.viewport.apply();

        this.camera.position.set(GameGrid.GRID_WIDTH / 2f, GameGrid.GRID_HEIGHT / 2f, 0f);
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);

        // Clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        this.viewport.update(width, height, true);
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
        this.batch.dispose();
        this.game.dispose();
    }
}
