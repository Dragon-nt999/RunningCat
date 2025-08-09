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
    }

    @Override
    public void show() {
        // Text Percent loading
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = FontManager.getInstance().getFont(90, Color.valueOf("#2760A2"));
        this.percentLoading = new Label("0%", labelStyle);

        Label titleLoading = new Label("Loading", labelStyle);
        titleLoading.setAlignment(Align.center);
        titleLoading.setPosition(
            (Gdx.graphics.getWidth() - titleLoading.getWidth()) / 2,
            (Gdx.graphics.getHeight() - titleLoading.getHeight()) / 2.5f
        );

        this.percentLoading.setAlignment(Align.center);
        this.percentLoading.setPosition(
            (Gdx.graphics.getWidth() - this.percentLoading.getWidth()) / 2,
            (Gdx.graphics.getHeight() - this.percentLoading.getHeight()) / 2
        );

        Label nameGame = new Label("GROUCHY CAT", labelStyle);
        nameGame.setAlignment(Align.center);
        nameGame.setScale(1.5f);
        nameGame.setPosition(
            (Gdx.graphics.getWidth() - nameGame.getWidth()) / 2,
            (Gdx.graphics.getHeight() - nameGame.getHeight()) / 1.5f
        );

        this.stage.addActor(nameGame);
        this.stage.addActor(titleLoading);
        this.stage.addActor(this.percentLoading);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
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
        this.game.dispose();
    }
}
