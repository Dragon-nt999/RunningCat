package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.player.AnimationSystem;
import com.dragonentertainment.runningcat.ui.HomeUI;
import com.dragonentertainment.runningcat.utils.AssetLoader;

public class HomeScreen extends BaseScreen{
    private final PooledEngine engine;
    public HomeScreen(AppGame game) {
        super(game);
        this.engine = new PooledEngine();
        // Get Background
        this.background = this.game.assetManager.get(AssetsName.Home.Backgrounds.BGHOME,
            Texture.class);

        // Render System
        this.engine.addSystem(new RenderSystem(this.batch));

        // Animation System
        this.engine.addSystem(new AnimationSystem());

        // UI
        this.ui = new HomeUI(this.game);
    }

    @Override
    protected void renderContent(float delta) {

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
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.unloadHomeScreenAssets(this.game.assetManager);
        this.ui.dispose();
    }

}
