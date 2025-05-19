package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.dragonentertainment.runningcat.components.BrickComponent;
import com.dragonentertainment.runningcat.components.PositionComponent;
import com.dragonentertainment.runningcat.components.RenderComponent;
import com.dragonentertainment.runningcat.entities.BrickFactory;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BrickPoolSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Array<Entity> brickPool = new Array<Entity>();
    private int numOfBrick = 144;
    private float timer = 0;
    private final Texture texture;

    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<BrickComponent> bm = ComponentMapper.getFor(BrickComponent.class);
    private final ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);

    private List<int[]> excludedList;

    public BrickPoolSystem(PooledEngine engine, Texture texture){
        this.engine = engine;
        this.texture = texture;

        this.excludedList = RandomMatrixPositions.getRandomPositions(GameGrid.WORLD_HEIGHT, GameGrid.WORLD_WIDTH, 100);
    }

    @Override
    public void addedToEngine(Engine engine) {
        for(int i = 0; i < numOfBrick; i++) {
            Entity brick = BrickFactory.createBrick(this.engine, this.texture, 0, 0);
            //brick.getComponent(BrickComponent.class).active = false;
            this.brickPool.add(brick);
        }
    }

    @Override
    public void update(float deltaTime) {
        this.generateBrick();
    }
    private void generateBrick() {
        int i = 0;

        // Dùng Set để so sánh nhanh (key = "x,y")
        Set<String> excludedKeys = new HashSet<>();
        for (int[] pos : excludedList) {
            excludedKeys.add(pos[0] + "," + pos[1]);
        }

        // Draw brick
        for(int y = 0; y < GameGrid.WORLD_HEIGHT; y++) {
            for(int x = 0; x < GameGrid.WORLD_WIDTH; x++) {
                String key = x + "," + y;
                if (!excludedKeys.contains(key)) {
                    if (i < this.brickPool.size) {
                        this.spawBrickAt(this.brickPool.get(i++), x, y);
                    }
                }
            }
        }

    }

    private void spawBrickAt(Entity brick, float x, float y) {
        PositionComponent pos = this.pm.get(brick);
        BrickComponent bc = this.bm.get(brick);

        pos.x = x;
        pos.y = y;

        bc.active = true;
    }

    public int getNumOfBrick() {
        return numOfBrick;
    }

    public void setNumOfBrick(int numOfBrick) {
        this.numOfBrick = numOfBrick;
    }

}
