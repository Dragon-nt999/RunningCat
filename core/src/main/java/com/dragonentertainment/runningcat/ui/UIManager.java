package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.GameStateManager;


public class UIManager {
    private Stage stage;
    private BitmapFont font;
    //private Label scoreLabel;
    private ImageButton pauseButton;
    //private Table scoreConatiner;


    public UIManager(AppGame game) {
        this.stage = new Stage(new FitViewport(GameGrid.WORLD_WIDTH, GameGrid.WORLD_HEIGHT));

        /**
         * ******************************************************
         * Score
         * ******************************************************
         * */
       // FreeTypeFontGenerator generator = new FreeTypeFontGenerator(game.assetManager.get(AssetsName.Fonts.SHOWG));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(AssetsName.Fonts.SHOWG));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (1 / 20);
        parameter.color = Color.valueOf("#8D4200");
        this.font = generator.generateFont(parameter);
        generator.dispose();

        Texture scoreText = game.assetManager.get(AssetsName.Game.Ui.SCORE_CONTAINER);
        Image scoreBg = new Image(scoreText);
        scoreBg.setSize(1, 1);
        scoreBg.setPosition(0, GameGrid.WORLD_HEIGHT - 1);
        Label.LabelStyle label = new Label.LabelStyle(this.font, Color.valueOf("#8D4200"));
        Label scoreLabel = new Label("90", label);

        // Giả sử scoreBg đã setSize(width, height) và setPosition(x, y)
        float bgX = scoreBg.getX();
        float bgY = scoreBg.getY();
        float bgWidth = scoreBg.getWidth();
        float bgHeight = scoreBg.getHeight();

        // Căn giữa Label trên ScoreBg
        scoreLabel.setPosition(
            bgX + (bgWidth - scoreLabel.getWidth()) / 2,
            bgY + (bgHeight - scoreLabel.getHeight()) / 2
        );

        this.stage.addActor(scoreBg);
        this.stage.addActor(scoreLabel);

        /**
         * ******************************************************
         * Pause button
         * ******************************************************
         * */
        Texture pauseTexture = game.assetManager.get(AssetsName.Game.Ui.BTN_PAUSE);
        Texture playTexture = game.assetManager.get(AssetsName.Game.Ui.BTN_PLAY);
        ImageButton.ImageButtonStyle pauseStyle = new ImageButton.ImageButtonStyle();
        pauseStyle.imageUp = new TextureRegionDrawable(new TextureRegion(pauseTexture));

        this.pauseButton = new ImageButton(pauseStyle);

        this.pauseButton.setSize(GameGrid.CELL_SIZE, GameGrid.CELL_SIZE);
        this.pauseButton.setPosition(0, 0);
        this.pauseButton.setTransform(true);
        this.pauseButton.setOrigin(pauseButton.getWidth() / 2f,
                                                    pauseButton.getHeight() / 2f);

        this.stage.addActor(this.pauseButton);

        this.pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                pauseButton.addAction(
                    Actions.sequence(
                        Actions.scaleTo(0.9f, 0.9f, 0.05f),
                        Actions.scaleTo(1f, 1f, 0.05f)
                    )
                );

                toolePause(pauseTexture, playTexture);
            }
        });

    }

    private void toolePause(Texture pauseTex, Texture playTex) {
        boolean paused = GameStateManager.getInstance().is(GameState.PAUSE);
        GameStateManager.getInstance().setState(paused ? GameState.PLAYING : GameState.PAUSE);

        Texture newTex = paused ? pauseTex : playTex;
        this.pauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(newTex));
    }

    public Stage getStage() {
        return this.stage;
    }

    public void draw() {
        this.stage.act();
        this.stage.draw();
    }

    public void dispose() {
        this.stage.dispose();
        this.font.dispose();
    }
}
