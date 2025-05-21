package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.factory.ParallaxFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class ParallaxSystems extends EntitySystem {
    private final AppGame game;
    private final PooledEngine engine;
    private final SpriteBatch batch;
    private ImmutableArray<Entity> entities;

    public ParallaxSystems(AppGame game, PooledEngine engine, SpriteBatch batch) {
        this.game = game;
        this.engine = engine;
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.all(ParallaxComponent.class).get());
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
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_1), 2, -0.5f, 1);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_2), -2, -0.5f,2);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_3), 15, -0.5f,3);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_4), 3, -0.5f,4);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_5), 7, -0.5f,5);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_6), 0, -0.5f,6);
    }

    public void generateParallax_layer02() {
        float addSpeed = 0.5f;
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_1), 0, -2f,1 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_2), 7, -2f,2 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_3), 4, -2f,3 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_4), 3, -2f,4 + addSpeed);
    }

    public void generateParallax_layer03() {
        float addSpeed = 10f;
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_5), 0, -4.5f,1 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_6), 7, -4.5f,2 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_7), 12, -4.5f,3 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_8), 10, -4.5f,4 + addSpeed);
        ParallaxFactory.createParallax(this.engine, this.game.assetManager.get(AssetsName.Game.Backgrounds.LOTUS_9), 5, -4.5f,5 + addSpeed);
    }
}
