package com.dragonentertainment.runningcat.systems.parallax;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.factory.ParallaxFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class ParallaxRenderSystems extends EntitySystem {
    private final AppGame game;
    private final PooledEngine engine;
    private final SpriteBatch batch;
    private ImmutableArray<Entity> entities;

    public ParallaxRenderSystems(AppGame game, PooledEngine engine, SpriteBatch batch) {
        this.game = game;
        this.engine = engine;
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.one(ParallaxComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();

        for(Entity e : this.entities) {
            TransformComponent transform = MappersComponent.transform.get(e);
            TextureComponent texture = MappersComponent.texture.get(e);

            this.batch.draw(texture.texture, transform.position.x,
                                    transform.position.y, transform.width, transform.height);
        }

        batch.end();
    }

    public void generateParallax_layer01() {
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_1), MathUtils.random(-2, 20), 10f, 1);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_1), MathUtils.random(-2, 20), -0.5f, 1);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_2), MathUtils.random(-2, 20), 5f, 2);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_2), MathUtils.random(-2, 20), -0.5f,2);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_3), MathUtils.random(-2, 20), 7f, 3);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_3), MathUtils.random(-2, 20), -0.5f,3);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_4), MathUtils.random(-2, 20), 4f, 4);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_4), MathUtils.random(-2, 20), -0.5f,4);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_5), MathUtils.random(-2, 20), 10f, 5);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_5), MathUtils.random(-2, 20), -0.5f,5);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.CLOUD_6), MathUtils.random(-2, 20), 12f, 6);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_6), MathUtils.random(-2, 20), -0.5f,6);
    }

    public void generateParallax_layer02() {
        float addSpeed = 6f;
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_1), MathUtils.random(-2, 20), -2f,1 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_2), MathUtils.random(-2, 20), -2f,2 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_3), MathUtils.random(-2, 20), -2f,3 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_4), MathUtils.random(-2, 20), -2f,4 + addSpeed);
    }

    public void generateParallax_layer03() {
        float addSpeed = 10f;
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_5), MathUtils.random(-2, 20), -4.5f,1 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_6), MathUtils.random(-2, 20), -4.5f,2 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_7), MathUtils.random(-2, 20), -4.5f,3 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_8), MathUtils.random(-2, 20), -4.5f,4 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_9), MathUtils.random(-2, 20), -4.5f,5 + addSpeed);
    }
}
