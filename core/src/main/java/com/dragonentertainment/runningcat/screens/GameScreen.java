package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.BoundsRenderSystem;
import com.dragonentertainment.runningcat.systems.GravitySystem;
import com.dragonentertainment.runningcat.systems.player.AnimationSystem;
import com.dragonentertainment.runningcat.systems.CollisionSystem;
import com.dragonentertainment.runningcat.systems.MovementSystem;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.TouchSystem;
import com.dragonentertainment.runningcat.systems.brick.BrickCreateSystem;
import com.dragonentertainment.runningcat.systems.parallax.ParallaxCreateSystem;
import com.dragonentertainment.runningcat.systems.player.JumpSystem;
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

        // Brick
        Texture brick = this.game.assetManager.get(AssetsName.Game.Items.BRICK, Texture.class);
        this.engine.addSystem(new BrickCreateSystem(this.engine, brick));

        // Cat
        this.engine.addSystem(new AnimationSystem(this.game, this.engine));

        // Render
        this.engine.addSystem(new RenderSystem(this.game, this.batch));

        // Collision
        this.engine.addSystem(new CollisionSystem());

        // Movement
        this.engine.addSystem(new MovementSystem(this.engine));

        // Render Bounds for text Collision
        this.engine.addSystem(new BoundsRenderSystem(this.camera));

        // Gravity
        this.engine.addSystem(new GravitySystem());

        // Jump
        this.engine.addSystem(new JumpSystem());

        // Touch
        this.engine.addSystem(new TouchSystem(this.engine));
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
