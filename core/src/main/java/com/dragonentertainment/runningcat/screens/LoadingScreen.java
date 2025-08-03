package com.dragonentertainment.runningcat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.FontManager;

public class LoadingScreen implements Screen {
    private final ScreenType targetScreen;
    private final Stage stage;
    private final AppGame game;
    private Label percentLoading;

    public LoadingScreen(AppGame game, ScreenType screenType) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.targetScreen = screenType;
        switch (this.targetScreen) {
            case HOME:
                AssetLoader.loadHomeScreenAssets(game.assetManager);
                break;
            case GAME:
                AssetLoader.loadGameScreenAssets(game.assetManager);
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        // Text Percent loading
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = FontManager.getInstance().getFont(90, Color.WHITE);
        this.percentLoading = new Label("0%", labelStyle);

        Label title = new Label("Loading", labelStyle);
        title.setAlignment(Align.center);
        title.setPosition(
            (Gdx.graphics.getWidth() - title.getWidth()) / 2,
            (Gdx.graphics.getHeight() - title.getHeight()) / 3
        );

        this.percentLoading.setAlignment(Align.center);
        this.percentLoading.setPosition(
            (Gdx.graphics.getWidth() - this.percentLoading.getWidth()) / 2,
            (Gdx.graphics.getHeight() - this.percentLoading.getHeight()) / 2
        );
        this.stage.addActor(title);
        this.stage.addActor(this.percentLoading);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(this.game.assetManager.update()) {
            switch (this.targetScreen) {
                case HOME:
                    this.game.setScreen(new HomeScreen(this.game));
                    break;
                case GAME:
                    this.game.setScreen(new GameScreen(this.game));
                    break;
                default:
                    break;
            }
        } else {
            float progress = this.game.assetManager.getProgress();
            int percent = MathUtils.round(progress * 100) + 1;
            this.percentLoading.setText(percent + "%");
            this.stage.act(delta);
            this.stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
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
        this.stage.dispose();
    }
}
