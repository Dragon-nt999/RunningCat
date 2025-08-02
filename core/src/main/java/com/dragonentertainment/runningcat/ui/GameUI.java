package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.FontManager;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.ScoreManager;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class GameUI extends BaseUI{
    private Label scoreLabel;

    public GameUI(AppGame game) {
        super(game);
    }

    @Override
    protected void init() {
        stage.getViewport().apply(true);
        this.drawScore();
        this.drawBtnPause();
    }

    @Override
    protected void update() {
        this.scoreLabel.setText(ScoreManager.getInstance().getScore());
    }

    /**
     * ******************************************************
     * Score
     * ******************************************************
     * */
    private void drawScore() {
        Texture scoreText = this.game.assetManager.get(AssetsName.Game.Ui.SCORE_CONTAINER);
        Image scoreBg = new Image(scoreText);

        float w = scoreText.getWidth();
        float h = scoreText.getHeight();

        scoreBg.setSize(w, h);
        scoreBg.setPosition(0, Gdx.graphics.getHeight() - h);

        this.stage.addActor(scoreBg);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = FontManager.getInstance().getFont(90, Color.valueOf("#8D4200"));

        this.scoreLabel =  new Label(ScoreManager.getInstance().getScore() + "", labelStyle);
        this.scoreLabel.setAlignment(Align.center);
        this.scoreLabel.setPosition(
            this.scoreLabel.getWidth() * 2,
            scoreBg.getY()
        );
        this.stage.addActor(this.scoreLabel);
    }

    /**
     * ******************************************************
     * Pause button
     * ******************************************************
     * */
    private void drawBtnPause() {
        Texture pauseTexture = this.game.assetManager.get(AssetsName.Game.Ui.BTN_PAUSE);
        Texture playTexture = this.game.assetManager.get(AssetsName.Game.Ui.BTN_PLAY);
        ImageButton.ImageButtonStyle pauseStyle = new ImageButton.ImageButtonStyle();
        pauseStyle.imageUp = new TextureRegionDrawable(new TextureRegion(pauseTexture));

        ImageButton pauseButton = getImageButton(pauseStyle);

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(GameStateManager.getInstance().is(GameState.STOP)
                    || GameStateManager.getInstance().is(GameState.OVER)) return;

                // Play sound effect
                SoundManager.getInstance().playSound(AssetsName.Sounds.Common.TOUCH);

                pauseButton.addAction(
                    Actions.sequence(
                        Actions.scaleTo(0.9f, 0.9f, 0.05f),
                        Actions.scaleTo(1f, 1f, 0.05f)
                    )
                );

                boolean paused = GameStateManager.getInstance().is(GameState.PAUSE);
                GameStateManager.getInstance().setState(paused ? GameState.PLAYING : GameState.PAUSE);

                Texture newTex = paused ? pauseTexture : playTexture;
                pauseButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(newTex));

            }
        });

        this.stage.addActor(pauseButton);
    }

    private ImageButton getImageButton(ImageButton.ImageButtonStyle pauseStyle) {
        ImageButton pauseButton = new ImageButton(pauseStyle);

        float w = pauseButton.getWidth();
        float h = pauseButton.getHeight();

        pauseButton.setSize(w, h);
        pauseButton.setPosition((((float) Gdx.graphics.getWidth() / 2) - (w / 2)), 0);
        pauseButton.setTransform(true);
        pauseButton.setOrigin(pauseButton.getWidth() / 2f, pauseButton.getHeight() / 2f);

        return pauseButton;
    }
}
