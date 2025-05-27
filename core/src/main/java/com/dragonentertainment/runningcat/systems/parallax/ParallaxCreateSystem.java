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
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.factory.ParallaxFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.GameGrid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParallaxCreateSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Map<Integer, Texture> mountains;
    private final Map<Integer, Texture> clouds;
    private final Map<Integer, Texture> lotusLayerBack;
    private final Map<Integer, Texture> lotusLayerFront;

    private final List<Entity> parallaxMountain = new ArrayList<>();
    private final List<Entity> parallaxCloud = new ArrayList<>();
    private final List<Entity> parallaxLotusBack = new ArrayList<>();

    public ParallaxCreateSystem(AppGame game, PooledEngine engine) {
        this.engine = engine;
        AssetManager assetManager = game.assetManager;

        // Mountains
        this.mountains = new HashMap<>();
        this.mountains.put(1, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_1));
        this.mountains.put(2, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_2));
        this.mountains.put(3, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_3));
        this.mountains.put(4, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_4));
        this.mountains.put(5, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_5));
        this.mountains.put(6, assetManager.get(AssetsName.Game.Backgrounds.MOUNTAIN_6));

        // Clouds
        this.clouds = new HashMap<>();
        this.clouds.put(-1, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_1));
        this.clouds.put(-2, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_2));
        this.clouds.put(-3, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_3));
        this.clouds.put(7, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_4));
        this.clouds.put(8, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_5));
        this.clouds.put(9, assetManager.get(AssetsName.Game.Backgrounds.CLOUD_6));

        // Lotus Back
        String[] lotusB = new String[]{
            AssetsName.Game.Backgrounds.LOTUS_1,
            AssetsName.Game.Backgrounds.LOTUS_2,
            AssetsName.Game.Backgrounds.LOTUS_3,
            AssetsName.Game.Backgrounds.LOTUS_4
        };
        this.lotusLayerBack = new HashMap<>();
        for(int i = 0; i < 20; i++) {
            this.lotusLayerBack.put(i * 2, assetManager.get(lotusB[MathUtils.random(0, lotusB.length - 1)]));
        }
        // Lotus Front
        String[] lotusF = new String[]{
            AssetsName.Game.Backgrounds.LOTUS_5,
            AssetsName.Game.Backgrounds.LOTUS_6,
            AssetsName.Game.Backgrounds.LOTUS_7,
            AssetsName.Game.Backgrounds.LOTUS_8,
            AssetsName.Game.Backgrounds.LOTUS_9,
        };
        this.lotusLayerFront = new HashMap<>();
        for(int i = 0; i < 20; i++) {
            this.lotusLayerFront.put(i * 2, assetManager.get(lotusF[MathUtils.random(0, lotusF.length - 1)]));
        }

    }

    @Override
    public void addedToEngine(Engine engine) {
        // Mountains
        this.createMountains(false);

        // Clouds
        this.createClouds(false);

        // Lotus Back
        this.createLotusLayerBack(false);

        // Lotus Front
        this.createLotusLayerFront(false);
    }

    @Override
    public void update(float deltaTime) {

        // Get total Entity
        this.parallaxMountain.clear();
        this.parallaxCloud.clear();
        this.parallaxLotusBack.clear();

        // Get all Parallax Entity
        ImmutableArray<Entity> parallaxs = this.engine.getEntitiesFor(
            Family.all(RenderTypeComponent.class).get()
        );

        // Get Parallax Entity on Screen and count it
        for(Entity entity: parallaxs) {
            RenderTypeComponent rtc = entity.getComponent(RenderTypeComponent.class);
            switch (rtc.type){
                case PARALLAX_MOUNTAIN:
                    this.parallaxMountain.add(entity);
                    break;
                case PARALLAX_CLOUD:
                    this.parallaxCloud.add(entity);
                    break;
                case PARALLAX_LOTUS:
                    this.parallaxLotusBack.add(entity);
                    break;
                default:
                    break;
            }
        }

        // Respawn Parallax Mount
        if(this.parallaxMountain.size() < 3) {
            this.createMountains(true);
        }

        // Respawn Parallax Cloud
        if(this.parallaxCloud.size() < 2) {
            this.createClouds(true);
        }

        // Respawn Parallax Lotus back
        if(this.parallaxLotusBack.size() < 10) {
            this.createLotusLayerBack(true);
            this.createLotusLayerFront(true);
        }

    }

    /*
    * Create Mountains
    * */
    private void createMountains(boolean reSpawn) {

        // Shuffle
        List<Map.Entry<Integer, Texture>> mountainList = new ArrayList<>(this.mountains.entrySet());
        Collections.shuffle(mountainList);
        for(Map.Entry<Integer, Texture> mountain : mountainList) {

            // Random X
            int xPos = reSpawn ? MathUtils.random(GameGrid.WORLD_WIDTH, GameGrid.WORLD_WIDTH + 20)
                                : MathUtils.random(0, GameGrid.WORLD_WIDTH + 20);

            // Create Entity
            ParallaxFactory.createParallax(
                                this.engine,
                                mountain.getValue(),
                                xPos,
                                -0.5f,
                                mountain.getKey(),
                                RenderTypeComponent.Type.PARALLAX_MOUNTAIN
                );
        }
    }
    /**
     * Create clouds
     */
    private void createClouds(boolean reSpawn) {
        // Shuffle
        List<Map.Entry<Integer, Texture>> cloudList = new ArrayList<>(this.clouds.entrySet());
        Collections.shuffle(cloudList);
        for(Map.Entry<Integer, Texture> cloud : cloudList) {
            // Random X
            int xPos = reSpawn ? MathUtils.random(GameGrid.WORLD_WIDTH, GameGrid.WORLD_WIDTH + 20)
                                : MathUtils.random(0, GameGrid.WORLD_WIDTH + 20);

            ParallaxFactory.createParallax(
                this.engine,
                cloud.getValue(),
                xPos,
                MathUtils.random(1, 4) + cloud.getKey(),
                cloud.getKey(),
                RenderTypeComponent.Type.PARALLAX_CLOUD
            );
        }
    }

    /*
    * Create Lotus Layer Back
    *
    * */
    private void createLotusLayerBack(boolean reSpawn) {
        // Shuffle
        List<Map.Entry<Integer, Texture>> lotusList = new ArrayList<>(this.lotusLayerBack.entrySet());
        Collections.shuffle(lotusList);
        for(Map.Entry<Integer, Texture> lotus : lotusList) {

            // Random X
            int xPos = reSpawn ? MathUtils.random(GameGrid.WORLD_WIDTH, GameGrid.WORLD_WIDTH + 50)
                : lotus.getKey();

            // Create Entity
            ParallaxFactory.createParallax(
                this.engine,
                lotus.getValue(),
                xPos + 1,
                -1f,
                7,
                RenderTypeComponent.Type.PARALLAX_LOTUS
            );
        }
    }

    /*
     * Create Lotus Layer Front
     *
     * */
    private void createLotusLayerFront(boolean reSpawn) {
        // Shuffle
        List<Map.Entry<Integer, Texture>> lotusList = new ArrayList<>(this.lotusLayerFront.entrySet());
        Collections.shuffle(lotusList);
        for(Map.Entry<Integer, Texture> lotus : lotusList) {
            // Random X
            int xPos = reSpawn ? MathUtils.random(GameGrid.WORLD_WIDTH, GameGrid.WORLD_WIDTH + 50)
                : lotus.getKey();

            // Create Entity
            ParallaxFactory.createParallax(
                this.engine,
                lotus.getValue(),
                xPos + 6,
                -5f,
                10,
                RenderTypeComponent.Type.PARALLAX_LOTUS
            );
        }
    }
}
