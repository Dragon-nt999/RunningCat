package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.enums.CatTextureType;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.factory.PlayerFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.systems.FlyingSystem;
import com.dragonentertainment.runningcat.systems.GravitySystem;
import com.dragonentertainment.runningcat.systems.RicochetEffectSystem;
import com.dragonentertainment.runningcat.systems.ScaleSystem;
import com.dragonentertainment.runningcat.systems.player.AnimationSystem;
import com.dragonentertainment.runningcat.systems.CollisionSystem;
import com.dragonentertainment.runningcat.systems.MovementSystem;
import com.dragonentertainment.runningcat.systems.RenderSystem;
import com.dragonentertainment.runningcat.systems.TouchSystem;
import com.dragonentertainment.runningcat.systems.brick.BrickCreateSystem;
import com.dragonentertainment.runningcat.systems.parallax.ParallaxCreateSystem;
import com.dragonentertainment.runningcat.systems.player.JumpSystem;
import com.dragonentertainment.runningcat.systems.player.PlayerSystem;
import com.dragonentertainment.runningcat.ui.UIFactory;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameData;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.HealthManager;
import com.dragonentertainment.runningcat.utils.LevelManager;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class GameScreen extends BaseScreen {
    private final PooledEngine engine;
    private float timeToRestart = -1;
    private boolean transitioning = false;
    public GameScreen(AppGame game) {
        super(game);

        // Initial Engine
        this.engine = new PooledEngine();

        // Get Background
        this.background = this.game.assetManager.get(AssetsName.Game.Backgrounds.BGGAME_DAY,
                                                    Texture.class);
        this.initGame();

        // Load Sound
        SoundManager.getInstance().init(this.game.assetManager);
    }

    @Override
    public void show() {
        super.show();
        SoundManager.getInstance().playMusic(AssetsName.Sounds.Game.BACKGROUND_MUSIC);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.ui.draw(delta);
    }

    @Override
    protected void renderContent(float delta) {
        if(GameStateManager.getInstance().is(GameState.OVER)) {
            if(this.timeToRestart < 0f) {
                this.timeToRestart = 0f;
                // Stop music and Play sound effect
                SoundManager.getInstance().stopMusic(AssetsName.Sounds.Game.BACKGROUND_MUSIC);
                SoundManager.getInstance().playSound(AssetsName.Sounds.Game.FAILURE);
            } else {
                this.timeToRestart += delta;
                if(this.timeToRestart >= 2f) {
                    this.timeToRestart = -1f;
                    if(HealthManager.getInstance().getHealth() > 0) {
                        this.resetGame();
                    } else {
                        this.game.playerIsInjured = true;
                        if(GameData.getInstance().getAttempts() >= Config.MAX_ATTEMPTS_TO_SHOW_ADS) {
                            this.game.setScreen(new AdsScreen(this.game));
                        } else {
                            this.game.setScreen(new HomeScreen(this.game));
                        }
                    }
                }
            }
        }

        if(this.engine.getEntities().size() > 0 && this.engine.getSystems().size() > 0) {
            this.engine.update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.ui.resize();
    }

    @Override
    public void hide() {
        super.hide();
        this.ui.dispose();
        SoundManager.getInstance().stopMusic(AssetsName.Sounds.Game.BACKGROUND_MUSIC);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.ui.dispose();
        SoundManager.getInstance().stopMusic(AssetsName.Sounds.Game.BACKGROUND_MUSIC);
    }

    private void initGame() {
        GameStateManager.getInstance().setState(GameState.PLAYING);
        // Create Cat
        PlayerFactory.createCat(game, this.engine, CatTextureType.CAT_IN_GAME, 3, 4);

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
        this.engine.addSystem(new CollisionSystem(this.game, this.engine));

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

        // Flying effect
        this.engine.addSystem(new FlyingSystem());

        // Scale effect
        this.engine.addSystem(new ScaleSystem(this.game, this.engine));

        // UI
        this.ui = UIFactory.createUI(this.game, ScreenType.GAME);

        // Input processor
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this.ui.getStage());
        multiplexer.addProcessor(touchSystem);

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void resetGame() {
        HealthManager.getInstance().decreaseHealth();
        if (!transitioning) {
            transitioning = true;
            Gdx.app.postRunnable(() -> {
                game.setScreen(new GameScreen(game));
            });
        }
    }
}
