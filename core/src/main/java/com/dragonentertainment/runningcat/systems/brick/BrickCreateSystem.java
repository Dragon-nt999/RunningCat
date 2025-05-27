package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.factory.BrickFactory;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

import java.util.List;

public class BrickCreateSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Texture texture;

    public BrickCreateSystem(PooledEngine engine, Texture texture) {
        this.engine = engine;
        this.texture = texture;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.generateBricks(false);
    }

    @Override
    public void update(float deltaTime) {
        int brickRemain = this.engine.getEntitiesFor(Family.one(BrickComponent.class).get()).size();
        if( brickRemain < 30 ) {
            this.generateBricks(true);
        }

        Gdx.app.log("BRICK", brickRemain + "");
    }

    private void generateBricks(boolean isReSpawn) {
       List<List<Vector2>> randPositions = RandomMatrixPositions.getBlockPositions(5);
        for(int i = 0; i < randPositions.size(); i++) {
            List<Vector2> positions = randPositions.get(i);
            for(int j = 0; j < positions.size(); j++) {
                int w = isReSpawn ? GameGrid.WORLD_WIDTH : 0;
                BrickFactory.createBrick(
                    this.engine,
                    this.texture,
                    positions.get(j).x + w,
                    positions.get(j).y,
                    9);
            }
        }
    }
}
