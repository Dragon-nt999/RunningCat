package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.enums.Level;
import com.dragonentertainment.runningcat.factory.BrickFactory;
import com.dragonentertainment.runningcat.factory.PlayerFactory;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrickCreateSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Texture texture;

    private final List<Vector2> positionsPlayer = new ArrayList<>();
    private final List<Vector2> positionsUsed = new ArrayList<>();

    private final AppGame game;

    private float maxXBrick = Float.NEGATIVE_INFINITY;
    private Map<Float, Entity> bricksHasMaxX = new HashMap<>();

    private ImmutableArray<Entity> allBricks;
    private int max = 0;

    public BrickCreateSystem(PooledEngine engine, Texture texture, AppGame game) {
        this.engine = engine;
        this.texture = texture;
        this.game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.bricksHasMaxX = this.generateBricks(false, Level.MEDIUM);
        this.allBricks = engine.getEntitiesFor(Family.one(BrickComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {

        //Entity lastBrick = this.bricksHasMaxX.get(this.maxXBrick);
        //int lastX = (int)lastBrick.getComponent(TransformComponent.class).position.x;

        if(this.allBricks.size() <= ((int) (max * 0.7)) ) {
            this.generateBricks(true, Level.MEDIUM);
            if(this.generateMouses()){
                this.generateOtherCat();
            }
        }

       Gdx.app.log("LASTX", this.allBricks.size() + "===" + ((int) (max * 0.7)));
    }

    private Map<Float, Entity>  generateBricks(boolean isReSpawn, Level level) {
        max = 0;
        Map<Float, Entity>  all = new HashMap<>();
       // Clear position for render Player
        this.positionsPlayer.clear();

        /*-----------------------------------------------
         * Get random position on Game Grid
         *-----------------------------------------------*/
        List<Vector2> randPositions = RandomMatrixPositions.getBlockPositions(isReSpawn, level);

        for(int i = 0; i < randPositions.size(); i++) {
            Vector2 position = randPositions.get(i);

            /*-----------------------------------------------
             * Create brick to render
             *-----------------------------------------------*/
            Entity brick = BrickFactory.createBrick(
                this.engine,
                this.texture,
                position.x,
                position.y,
                9
            );

            max++;

            /*-----------------------------------------------
             * Find Brick has greatest position x
             *-----------------------------------------------*/
            if(position.x > this.maxXBrick) {
                this.maxXBrick = position.x;
                all.put(this.maxXBrick, brick);
            }

            /*-----------------------------------------------
             * Tracking position for spawn enemy cat and mouse
             *-----------------------------------------------*/
            this.positionsPlayer.add(position);
        }
        return all;
    }

    private boolean generateMouses() {
        int maxMouse = 2;
        if(!this.positionsPlayer.isEmpty()) {
            Collections.shuffle(this.positionsPlayer);
            for (Vector2 pos : this.positionsPlayer) {
                if(maxMouse > 0) {
                    PlayerFactory.createMouse(this.game, this.engine, pos);
                    maxMouse--;
                    this.positionsUsed.add(pos);
                }
            }

            return true;
        }

        return false;
    }

    private void generateOtherCat() {
        int maxCat = 1;
        this.positionsPlayer.removeAll(this.positionsUsed);
        if(!this.positionsPlayer.isEmpty()) {
            Collections.shuffle(this.positionsPlayer);
            for (Vector2 pos : this.positionsPlayer) {
                if(maxCat > 0) {
                    PlayerFactory.createOtherCat(this.game, this.engine, pos);
                    maxCat--;
                    this.positionsUsed.add(pos);
                }
            }
        }
    }
}
