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
        // Create Bricks
        this.generateBricks();
    }

    @Override
    public void update(float deltaTime) {
        this.batch.begin();

        for(Entity e : this.entities) {
            TextureComponent tex = MappersComponent.texture.get(e);
            TransformComponent trans = MappersComponent.transform.get(e);

            this.batch.draw(tex.texture, trans.position.x, trans.position.y, trans.width, trans.height);
        }

        this.batch.end();

        int brickRemain = this.engine.getEntitiesFor(Family.one(BrickComponent.class).get()).size();

        if( brickRemain == 0) {
            this.generateBricks();
        }
        //Gdx.app.log("ERROR", "" + brickRemain);
    }

    private void generateBricks() {
        List<List<Vector2>> randoomPositions = RandomMatrixPositions.getBlockPositions(10, nextPos);
        for(List<Vector2> positions : randoomPositions) {
            for(int i = 0; i < positions.size(); i++) {
                BrickFactory.createBrick(this.engine, this.texture, positions.get(i).x, positions.get(i).y);

                if(i == positions.size() - 1) {
                    nextPos = (int)positions.get(i).x;
                }
            }
        }
    }
}
