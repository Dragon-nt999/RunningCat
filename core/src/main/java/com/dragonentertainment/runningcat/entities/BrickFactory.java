package com.dragonentertainment.runningcat.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.BrickComponent;
import com.dragonentertainment.runningcat.components.PositionComponent;
import com.dragonentertainment.runningcat.components.RenderComponent;

public class BrickFactory {
    public static Entity createBrick(PooledEngine engine, Texture texture, float x, float y){
        Entity entity = engine.createEntity();

        PositionComponent pos = engine.createComponent(PositionComponent.class);
        pos.x = x;
        pos.y = y;

        BrickComponent brick = engine.createComponent(BrickComponent.class);
        brick.active = true;

        RenderComponent render = engine.createComponent(RenderComponent.class);
        render.texture = texture;

        entity.add(pos);
        entity.add(brick);
        entity.add(render);

        engine.addEntity(entity);

        return entity;
    }
}
