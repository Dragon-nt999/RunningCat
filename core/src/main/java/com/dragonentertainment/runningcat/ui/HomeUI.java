package com.dragonentertainment.runningcat.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.screens.LoadingScreen;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.FontManager;
import com.dragonentertainment.runningcat.utils.GameData;
import com.dragonentertainment.runningcat.utils.HealthManager;
import com.dragonentertainment.runningcat.utils.LevelManager;
import com.dragonentertainment.runningcat.utils.ScoreManager;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class HomeUI extends BaseUI{

    public HomeUI(AppGame game) {
        super(game);
    }

    @Override
    protected void init() {
        stage.getViewport().apply(true);
        this.drawScore();
        this.drawBtnPlay();
        this.drawLevel();
    }

    @Override
    protected void update(float delta) {

    }
    /**
     * ******************************************************
     * Score
     * ******************************************************
     * */
    private void drawScore() {
        Texture scoreText = this.game.assetManager.get(AssetsName.Home.Ui.HOME_SCORE_CONTAINER);
        Image scoreBg = new Image(scoreText);

        float w = scoreText.getWidth();
        float h = scoreText.getHeight();

        scoreBg.setSize(w, h);
        scoreBg.setPosition((((float) Gdx.graphics.getWidth() / 2) - (w / 2)), (float) Gdx.graphics.getHeight() / 1.7f);

        this.stage.addActor(scoreBg);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = FontManager.getInstance().getFont(90, Color.valueOf("#8D4200"));
        Label scoreLabel = new Label(GameData.getInstance().getScore() + "", labelStyle);
        Label scoreName = new Label("HIGH SCORE", labelStyle);

        scoreName.setAlignment(Align.center);
        scoreName.setPosition(
            (scoreBg.getX() + scoreBg.getWidth() / 2) - (scoreName.getWidth() / 2),
            scoreBg.getY() * 1.13f
        );

        scoreLabel.setAlignment(Align.center);
        scoreLabel.setPosition(
            (scoreBg.getX() + scoreBg.getWidth() / 2) - (scoreLabel.getWidth() / 2),
            scoreBg.getY() * 1.02f
        );
        this.stage.addActor(scoreName);
        this.stage.addActor(scoreLabel);
    }

    /**
     * ******************************************************
     * Pause button
     * ******************************************************
     * */
    private void drawBtnPlay() {
        Texture playTexture = this.game.assetManager.get(AssetsName.Home.Ui.BTNPLAY);
        ImageButton.ImageButtonStyle playStyle = new ImageButton.ImageButtonStyle();
        playStyle.imageUp = new TextureRegionDrawable(new TextureRegion(playTexture));

        ImageButton playButton = getImageButton(playStyle);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                // Play sound effect
                SoundManager.getInstance().playSound(AssetsName.Sounds.Common.TOUCH);

                playButton.addAction(
                    Actions.sequence(
                        Actions.scaleTo(0.9f, 0.9f, 0.05f),
                        Actions.scaleTo(1f, 1f, 0.05f)
                    )
                );
                GameData.getInstance().increaseAttempts();
                LevelManager.getInstance().setLevel(1);
                HealthManager.getInstance().reset();
                ScoreManager.getInstance().resetScore();
                LevelManager.getInstance().resetNumBrickPerLevel();
                // Save user's attempts
                game.setScreen(new LoadingScreen(game, ScreenType.GAME));
            }
        });

        this.stage.addActor(playButton);
    }

    private ImageButton getImageButton(ImageButton.ImageButtonStyle pauseStyle) {
        ImageButton playButton = new ImageButton(pauseStyle);

        float w = playButton.getWidth();
        float h = playButton.getHeight();

        playButton.setSize(w, h);
        playButton.setPosition((((float) Gdx.graphics.getWidth() / 2) - (w / 2)), (float) Gdx.graphics.getHeight() / 14);
        playButton.setTransform(true);
        playButton.setOrigin(playButton.getWidth() / 2f, playButton.getHeight() / 2);

        return playButton;
    }

    /**
     * ******************************************************
     * Level
     * ******************************************************
     * */
    private void drawLevel() {
        Texture lv1texture = this.game.assetManager.get(AssetsName.Home.Ui.THUMB_LV1);
        ImageButton.ImageButtonStyle lv1Style = new ImageButton.ImageButtonStyle();
        lv1Style.imageUp = new TextureRegionDrawable(new TextureRegion(lv1texture));

        ImageButton lv1 = new ImageButton(lv1Style);

        float w = lv1.getWidth();
        float h = lv1.getHeight();

        lv1.setSize(w, h);
        lv1.setPosition((((float) Gdx.graphics.getWidth() / 2) - (w / 2)), (float) Gdx.graphics.getHeight() / 6);
        lv1.setTransform(true);
        lv1.setOrigin(lv1.getWidth() / 2f, lv1.getHeight() / 2f);

        this.stage.addActor(lv1);

        Texture lv2texture = this.game.assetManager.get(AssetsName.Home.Ui.THUMB_LV2);
        ImageButton.ImageButtonStyle lv2Style = new ImageButton.ImageButtonStyle();
        lv2Style.imageUp = new TextureRegionDrawable(new TextureRegion(lv2texture));

        ImageButton lv2 = new ImageButton(lv2Style);

        float w2 = lv2.getWidth();
        float h2 = lv2.getHeight();

        lv2.setSize(w2, h2);
        lv2.setPosition((((float) Gdx.graphics.getWidth() - w2) / 1.03f ), (float) Gdx.graphics.getHeight() / 5.2f);
        lv2.setTransform(true);
        lv2.setOrigin(lv2.getWidth() / 2f, lv2.getHeight() / 2f);

        this.stage.addActor(lv2);
    }

}
