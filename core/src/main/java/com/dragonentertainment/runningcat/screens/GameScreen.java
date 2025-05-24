package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.MovementSystem;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.parallax.ParallaxCreateSystem;
import com.dragonentertainment.runningcat.utils.AssetLoader;

public class GameScreen extends BaseScreen{
    private final PooledEngine engine;
    public GameScreen(AppGame game) {
        super(game);
        // Initial Engine
        this.engine = new PooledEngine();
        // Get Background
        this.background = this.game.assetManager.get(AssetsName.Game.Backgrounds.BGGAME_DAY,
                                                    Texture.class);

        // Parallax
        this.engine.addSystem(new ParallaxCreateSystem(this.game, this.engine));
        this.engine.addSystem(new RenderSystem(this.batch));

        // Movement
        this.engine.addSystem(new MovementSystem(this.engine));

        // Parallax Entity
        /*ParallaxRenderSystems parallaxSystems = new ParallaxRenderSystems(
                                                    this.game,
                                                    this.engine, this.batch);
        parallaxSystems.generateParallax_layer01();
        parallaxSystems.generateParallax_layer02();

        // Get Brick Texture
        Texture brick = this.game.assetManager.get(AssetsName.Game.Items.BRICK, Texture.class);
        this.engine.addSystem(parallaxSystems);
        // Add System
        this.engine.addSystem(new BrickRenderSystem(this.engine, this.batch, brick));
        this.engine.addSystem(new BrickMovementSystem(this.engine));

        // Movement System
        this.engine.addSystem(new ParallaxMovementSystem(this.viewport));
        parallaxSystems.generateParallax_layer03();*/
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
        AssetLoader.unloadGameScreenAssets(this.game.assetManager);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.unloadGameScreenAssets(this.game.assetManager);
    }

}
