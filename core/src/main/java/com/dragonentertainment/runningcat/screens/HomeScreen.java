package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.CatTextureType;
import com.dragonentertainment.runningcat.factory.PlayerFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.player.AnimationSystem;
import com.dragonentertainment.runningcat.systems.player.PlayerSystem;
import com.dragonentertainment.runningcat.ui.HomeUI;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.GameData;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class HomeScreen extends BaseScreen{
    private final PooledEngine engine;
    private String musicName = AssetsName.Sounds.Home.HOME_BACKGROUND_MUSIC;
    public HomeScreen(AppGame game) {
        super(game);
        this.engine = new PooledEngine();
        // Get Background
        this.background = this.game.assetManager.get(AssetsName.Home.Backgrounds.BGHOME,
            Texture.class);

        // Create Cat
        CatTextureType textureType = CatTextureType.CAT_IN_HOME_IDLE;

        if(GameData.getInstance().catIsInjured()) {
            textureType = CatTextureType.CAT_IN_HOME_INJURED;
        }
        PlayerFactory.createCat(game, this.engine, textureType,
                                (float) (GameGrid.WORLD_WIDTH / 3), 5);

        // Player
        this.engine.addSystem(new PlayerSystem(this.game));

        // Animation
        this.engine.addSystem(new AnimationSystem());

        // Render
        this.engine.addSystem(new RenderSystem(this.batch));

        // UI
        this.ui = new HomeUI(this.game);

        Gdx.input.setInputProcessor(this.ui.getStage());

        // Load Sound
        SoundManager.getInstance().init(this.game.assetManager);
    }

    @Override
    public void show() {
        super.show();
        SoundManager.getInstance().playMusic(musicName);

        if(this.game.adController != null) {
            this.game.adController.showBannerAds();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.ui.draw();
    }

    @Override
    protected void renderContent(float delta) {
        this.engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.ui.resize();
    }

    @Override
    public void hide() {
        super.hide();
        AssetLoader.unloadHomeScreenAssets(this.game.assetManager);
        SoundManager.getInstance().stopMusic(musicName);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.unloadHomeScreenAssets(this.game.assetManager);
        this.ui.dispose();
    }

}
