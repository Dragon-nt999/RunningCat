package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.factory.PlayerFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.GravitySystem;
import com.dragonentertainment.runningcat.systems.RicochetEffectSystem;
import com.dragonentertainment.runningcat.systems.player.AnimationSystem;
import com.dragonentertainment.runningcat.systems.CollisionSystem;
import com.dragonentertainment.runningcat.systems.MovementSystem;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.TouchSystem;
import com.dragonentertainment.runningcat.systems.brick.BrickCreateSystem;
import com.dragonentertainment.runningcat.systems.parallax.ParallaxCreateSystem;
import com.dragonentertainment.runningcat.systems.player.JumpSystem;
import com.dragonentertainment.runningcat.systems.player.PlayerSystem;
import com.dragonentertainment.runningcat.ui.UIManager;
import com.dragonentertainment.runningcat.utils.AssetLoader;
import com.dragonentertainment.runningcat.utils.GameStateManager;

public class GameScreen extends BaseScreen{
    private final PooledEngine engine;
    private float timeToRestart = -1;
    private final UIManager uiManager;

    public GameScreen(AppGame game) {
        super(game);

        // Initial Engine
        this.engine = new PooledEngine();

        // Initial UI
        this.uiManager = new UIManager(game);

        // Get Background
        this.background = this.game.assetManager.get(AssetsName.Game.Backgrounds.BGGAME_DAY,
                                                    Texture.class);

        // Create Cat
        PlayerFactory.createCat(game, this.engine);

        // Parallax
        this.engine.addSystem(new ParallaxCreateSystem(this.game, this.engine));

        // Brick
        Texture brick = this.game.assetManager.get(AssetsName.Game.Items.BRICK, Texture.class);
        this.engine.addSystem(new BrickCreateSystem(this.engine, brick, this.game));

        // Player
        this.engine.addSystem(new PlayerSystem(this.game));

        // Animation
        this.engine.addSystem(new AnimationSystem());

        // Render
        this.engine.addSystem(new RenderSystem(this.batch));

        // Collision
        this.engine.addSystem(new CollisionSystem());

        // Movement
        this.engine.addSystem(new MovementSystem(this.engine));

        // Render Bounds for text Collision
        //this.engine.addSystem(new BoundsRenderSystem(this.camera));

        // Gravity
        this.engine.addSystem(new GravitySystem());

        // Jump
        this.engine.addSystem(new JumpSystem());

        // Touch
        TouchSystem touchSystem = new TouchSystem(this.engine);
        this.engine.addSystem(touchSystem);

        // Ricochet effect
        this.engine.addSystem(new RicochetEffectSystem());

        // Reset Game state
        GameStateManager.getInstance().setState(GameState.PLAYING);

        // Input processor
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.uiManager.getStage());
        multiplexer.addProcessor(touchSystem);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {

        if(GameStateManager.getInstance().is(GameState.OVER)) {
            GameStateManager.getInstance().setState(GameState.RESTART);
        } else {
            super.render(delta);
            this.engine.update(delta);

            // Render UI
            this.uiManager.draw();
        }

        if(GameStateManager.getInstance().is(GameState.RESTART)) {
            if(this.timeToRestart < 0f) {
                this.timeToRestart = 0f;
            } else {
                this.timeToRestart += delta;
                if(this.timeToRestart >= 1f) {
                    this.game.setScreen(new LoadingScreen(this.game, ScreenType.GAME));
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.uiManager.getStage().getViewport().update(width, height, true);
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
        this.uiManager.dispose();
    }

}
