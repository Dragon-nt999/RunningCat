package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.Struct.AssetName;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.entities.ParallaxFactory;
import com.dragonentertainment.runningcat.systems.BrickPoolSystem;
import com.dragonentertainment.runningcat.systems.BrickRenderSystem;
import com.dragonentertainment.runningcat.systems.ParallaxSystems;
import com.dragonentertainment.runningcat.utils.AssetLoader;

public class GameScreen extends BaseScreen{
    private PooledEngine engine;
    public GameScreen(AppGame game) {
        super(game);
        // Initial Engine
        this.engine = new PooledEngine();
        // Get Background
        this.background = this.game.assetManager.get(AssetName.BACKGROUND_GAME, Texture.class);

        // Brick Texture
        Texture brickTexture = this.game.assetManager.get(AssetName.BRICK);

        this.engine.addSystem(new BrickPoolSystem(this.engine, brickTexture));
        this.engine.addSystem(new BrickRenderSystem(this.batch, this.engine));

        // Parallax background
        /*this.engine.addSystem(new ParallaxSystems(this.batch, this.camera));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_1), 0, 1));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_2), 6, 1));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_3), -2, 1));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_4), 5, 1));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_5), 7, 1));
        this.engine.addEntity(ParallaxFactory.createEntity(this.game.assetManager.get(AssetName.MOUNTAIN_6), 0, 1));*/
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
