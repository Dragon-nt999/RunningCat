package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.Struct.AssetName;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.entities.ParallaxFactory;
import com.dragonentertainment.runningcat.systems.ParallaxSystems;
import com.dragonentertainment.runningcat.utils.AssetLoader;

public class GameScreen extends BaseScreen{
    private Engine engine;
    public GameScreen(AppGame game) {
        super(game);
        // Initail Engine
        this.engine = new Engine();
        // Get Background
        this.background = this.game.assetManager.get(AssetName.BACKGROUND_GAME, Texture.class);

        // Parallax background
        this.engine.addSystem(new ParallaxSystems(this.viewport, this.batch, this.camera));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_1), 0, 1));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_2), 6, 1));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_3), -2, 1));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_4), 5, 1));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_5), 7, 1));
        this.engine.addEntity(new ParallaxFactory().createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_6), 0, 1));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
        AssetLoader.unlodGameScreenAssets(this.game.assetManager);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.unlodGameScreenAssets(this.game.assetManager);
    }

}
