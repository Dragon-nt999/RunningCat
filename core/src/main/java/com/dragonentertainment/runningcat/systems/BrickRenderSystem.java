package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonentertainment.runningcat.components.BrickComponent;
import com.dragonentertainment.runningcat.components.PositionComponent;
import com.dragonentertainment.runningcat.components.RenderComponent;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class BrickRenderSystem extends EntitySystem {
    private final SpriteBatch batch;
    private final ImmutableArray<Entity> bricks;
    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<BrickComponent> bm = ComponentMapper.getFor(BrickComponent.class);
    private final ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);

    public BrickRenderSystem(SpriteBatch batch, Engine engine) {
        this.batch = batch;
        this.bricks = engine.getEntitiesFor(Family.all(PositionComponent.class,
                                                BrickComponent.class, RenderComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        this.batch.begin();
        for(Entity brick : this.bricks) {
            BrickComponent bc = this.bm.get(brick);
            if(!bc.active) continue;

            PositionComponent pc = this.pm.get(brick);
            RenderComponent rc = this.rm.get(brick);

            this.batch.draw(rc.texture, pc.x, pc.y, GameGrid.CELL_SIZE, GameGrid.CELL_SIZE);
        }
        this.batch.end();
    }
}
