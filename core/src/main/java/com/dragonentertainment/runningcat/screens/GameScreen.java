package com.dragonentertainment.runningcat.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.entities.ParallaxFactory;
import com.dragonentertainment.runningcat.systems.ParallaxSystems;

public class GameScreen extends BaseScreen{
    private Engine engine;
    private Texture background;
    public GameScreen(AppGame game) {
        super(game);
        this.engine = new Engine();
    }

    @Override
    public void show() {
        super.show();
        this.background = new Texture("backgrounds/bgGame_day.jpg");

        this.engine.addSystem(new ParallaxSystems(this.viewport, this.batch, this.camera, this.background));

        this.engine.addEntity(ParallaxFactory.createEntity(new Texture("backgrounds/mountain_6.png"), 0.0f, 0f));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Simulate camera movement to test parallax
        camera.position.x += delta * 1f; // Move to the right
        camera.update();

        this.engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
        this.background.dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
        this.background.dispose();
    }

}
