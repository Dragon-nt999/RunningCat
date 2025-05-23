package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.factory.BrickFactory;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.RandomMatrixPositions;

import java.util.List;

public class BrickRenderSystem extends EntitySystem {
    private final PooledEngine engine;
    private final SpriteBatch batch;
    private ImmutableArray<Entity> entities;
    private final Texture texture;
    private boolean brickInitialed = false;
    private static int nextPos = 0;

    public BrickRenderSystem(PooledEngine engine, SpriteBatch batch, Texture texture) {
        this.engine = engine;
        this.batch = batch;
        this.texture = texture;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.one(BrickComponent.class).get());
        // Create Bricks for first time
        this.generateBricks(false);
    }

    @Override
    public void update(float deltaTime) {

        // Draw Bricks
        this.batch.begin();

        for(Entity e : this.entities) {
            TextureComponent tex = MappersComponent.texture.get(e);
            TransformComponent trans = MappersComponent.transform.get(e);

            this.batch.draw(tex.texture, trans.position.x, trans.position.y, trans.width, trans.height);
        }

        this.batch.end();

        // Re-spawn bricks
        int brickRemain = this.engine.getEntitiesFor(Family.one(BrickComponent.class).get()).size();
        if( brickRemain < 40 ) {
            this.generateBricks(true);
        }

        Gdx.app.log("INFO-GAME", brickRemain + "");
    }

    private void generateBricks(boolean isReSpawn) {
        List<List<Vector2>> randoomPositions = RandomMatrixPositions.getBlockPositions(20);
        for(int i = 0; i < randoomPositions.size(); i++) {
            List<Vector2> positions = randoomPositions.get(i);
            for(int j = 0; j < positions.size(); j++) {
                int w = isReSpawn ? GameGrid.WORLD_WIDTH : 0;
                BrickFactory.createBrick(this.engine, this.texture, positions.get(j).x + w, positions.get(j).y);
            }
        }
    }
}
