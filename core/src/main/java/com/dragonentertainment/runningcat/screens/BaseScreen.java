package com.dragonentertainment.runningcat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.ui.GameUI;
import com.dragonentertainment.runningcat.utils.FontManager;
import com.dragonentertainment.runningcat.utils.GameGrid;

public abstract class BaseScreen implements Screen {
    protected final AppGame game;
    protected final OrthographicCamera camera;
    protected final Viewport viewport;
    protected final SpriteBatch batch;
    protected Texture background;

    public BaseScreen(final AppGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(GameGrid.WORLD_WIDTH, GameGrid.WORLD_HEIGHT, this.camera);
        this.batch = new SpriteBatch();
    }
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        this.viewport.apply();

        this.camera.position.set(GameGrid.WORLD_WIDTH / 2f, GameGrid.WORLD_HEIGHT / 2f, 0f);
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);

        // Clear screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        this.batch.begin();

        if(this.background != null) {
            this.batch.draw(this.background, 0, 0, GameGrid.WORLD_WIDTH, GameGrid.WORLD_HEIGHT);
        }

        this.batch.end();
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
