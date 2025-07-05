package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import java.util.Iterator;
import java.util.List;

public class BrickCreateSystem extends EntitySystem {
    private final PooledEngine engine;
    private final Texture texture;

    private final List<Vector2> positionsPlayer = new ArrayList<>();
    private final List<Vector2> positionsUsed = new ArrayList<>();

    private final AppGame game;

    public BrickCreateSystem(PooledEngine engine, Texture texture, AppGame game) {
        this.engine = engine;
        this.texture = texture;
        this.game = game;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.generateBricks(false, Level.MEDIUM);
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> allBricks = this.engine.getEntitiesFor(Family.one(BrickComponent.class).get());
        Entity lastBrick = allBricks.get(allBricks.size() - 1);
        int lastX = (int)lastBrick.getComponent(TransformComponent.class).position.x;

        if( lastX < GameGrid.WORLD_WIDTH / 5) {
            this.generateBricks(true, Level.MEDIUM);
            if(this.generateMouses()){
                this.generateOtherCat();
            }
        }
    }

    private void generateBricks(boolean isReSpawn, Level level) {
       // Clear position for render Player
       this.positionsPlayer.clear();

       List<List<Vector2>> randPositions = RandomMatrixPositions.getBlockPositions(isReSpawn, level);
        for(int i = 0; i < randPositions.size(); i++) {
            List<Vector2> positions = randPositions.get(i);
            for(int j = 0; j < positions.size(); j++) {
                BrickFactory.createBrick(
                    this.engine,
                    this.texture,
                    positions.get(j).x,
                    positions.get(j).y,
                    9);

                // Add position for render Mouse and Other Cat
                this.positionsPlayer.add(positions.get(j));
            }
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
