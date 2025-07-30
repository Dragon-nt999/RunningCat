package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
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
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BrickCreateSystem extends IteratingSystem {
    private final PooledEngine engine;
    private final Texture texture;
    private final List<Vector2> positionsPlayer = new ArrayList<>();
    private final List<Vector2> positionsUsed = new ArrayList<>();
    private final AppGame game;
    private Entity maxXBrick = null;
    private float maxX = 0f;
    public BrickCreateSystem(PooledEngine engine, Texture texture, AppGame game) {
        super(Family.one(BrickComponent.class).get());
        this.engine = engine;
        this.texture = texture;
        this.game = game;
        this.generateBricks(false);
    }

    @Override
    public void update(float deltaTime) {
        this.maxX = Float.NEGATIVE_INFINITY;
        this.maxXBrick = null;

        super.update(deltaTime);

        /*-----------------------------------------------
         * Generate Brick by Brick has maximum X
         *-----------------------------------------------*/
        if(this.maxXBrick != null) {
            TransformComponent transform = MappersComponent.transform.get(this.maxXBrick);
            if((transform.position.x + transform.width) < (GameGrid.WORLD_WIDTH)) {
                this.maxXBrick = null;
                this.generateBricks(true);
                if(this.generateMouses()){
                    this.generateOtherCat();
                }
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        /*-----------------------------------------------
         * Get Brick has maximum X
         *-----------------------------------------------*/
        TransformComponent transform = MappersComponent.transform.get(entity);

        if(this.maxX < transform.position.x) {
            this.maxX = transform.position.x;
            this.maxXBrick = entity;
        }

       // Gdx.app.log("ALLBRICKS", this.maxX + "");

    }

    private void generateBricks(boolean isReSpawn) {

       // Clear position for render Player
        this.positionsPlayer.clear();

        /*-----------------------------------------------
         * Get random position on Game Grid
         *-----------------------------------------------*/
        List<Vector2> randPositions = RandomMatrixPositions.getBlockPositions(isReSpawn);

       // Gdx.app.log("ALLBRICKS", randPositions.toString());

        for(int i = 0; i < randPositions.size(); i++) {
            Vector2 position = randPositions.get(i);

            /*-----------------------------------------------
             * Create brick to render
             *-----------------------------------------------*/
             BrickFactory.createBrick(
                this.engine,
                this.texture,
                position.x,
                position.y,
                9
            );

            /*-----------------------------------------------
             * Tracking position for spawn enemy cat and mouse
             *-----------------------------------------------*/
            this.positionsPlayer.add(position);
        }

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
