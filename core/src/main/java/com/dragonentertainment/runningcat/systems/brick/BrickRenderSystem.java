package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.factory.BrickFactory;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class BrickRenderSystem extends EntitySystem {
    private final PooledEngine engine;
    private final SpriteBatch batch;
    private ImmutableArray<Entity> entities;
    private final Texture texture;

    private boolean brickInitialed = false;
    public BrickRenderSystem(PooledEngine engine, SpriteBatch batch, Texture texture) {
        this.engine = engine;
        this.batch = batch;
        this.texture = texture;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class).get());

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
    }

    private void generateBricks() {
        if(!this.brickInitialed) {
            for(int i = 0; i < GameGrid.WORLD_HEIGHT; i++) {

                // Draw Floor
                if(i < 2) {
                    for(int j = 0; j < GameGrid.WORLD_WIDTH; j++) {
                        if(j >= 5 && j <= 6) continue;
                        BrickFactory.createBrick(this.engine, this.texture, j, i);
                    }
                }

                // Draw roof
                if(i >= 15) {
                    for(int j = 0; j < GameGrid.WORLD_WIDTH; j++) {
                        if(j % 3 == 0) continue;
                        BrickFactory.createBrick(this.engine, this.texture, j, i);
                    }
                }

                // Draw middle

            }

            this.brickInitialed = true;
        }
    }
}
