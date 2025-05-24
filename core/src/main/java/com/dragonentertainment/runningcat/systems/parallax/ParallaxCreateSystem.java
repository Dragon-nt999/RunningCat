package com.dragonentertainment.runningcat.systems.parallax;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.factory.ParallaxFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.GameGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParallaxCreateSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Map<Integer, Texture> mountains;

    public ParallaxCreateSystem(AppGame game, PooledEngine engine) {
        this.engine = engine;
        AssetManager assetManager = game.assetManager;

        // Mountains
        this.mountains = new HashMap<>();
        mountains.put(1, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_1));
        mountains.put(2, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_2));
        mountains.put(3, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_3));
        mountains.put(4, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_4));
        mountains.put(5, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_5));
        mountains.put(6, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_6));
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.createMountain(false);
    }

    @Override
    public void update(float deltaTime) {

        // Get total Entity
        int totalRemain = this.engine.getEntitiesFor(Family.all(
                                                        ParallaxComponent.class).get()
                                                    ).size();

        if(totalRemain == 1) {
            this.createMountain(true);
        }
        Gdx.app.log(
            "ERROR",
             totalRemain + "");
    }

    /*
    * Create Mountains
    * */
    private void createMountain(boolean reSpawn) {

        // Shuffle
        List<Map.Entry<Integer, Texture>> mountainList = new ArrayList<>(this.mountains.entrySet());
        Collections.shuffle(mountainList);
        for(Map.Entry<Integer, Texture> mountain : mountainList) {

            // Random X
            int xPos = reSpawn ? MathUtils.random(0, GameGrid.WORLD_WIDTH * 5)
                : MathUtils.random(GameGrid.WORLD_WIDTH, GameGrid.WORLD_WIDTH * 5);

            // Create Entity
            ParallaxFactory.createParallax(
                                this.engine,
                                mountain.getValue(),
                                xPos,
                                -0.5f,
                                mountain.getKey()
                );
        }
    }

    /*public void generateParallax_layer01() {
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
    }*/
}
