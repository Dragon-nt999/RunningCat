package com.dragonentertainment.runningcat.systems.brick;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class BrickMovementSystem extends IteratingSystem {

    private final PooledEngine engine;

    public BrickMovementSystem(PooledEngine engine) {
        super(Family.one(BrickComponent.class).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Get Component
        TransformComponent trans = MappersComponent.transform.get(entity);
        VelocityComponent velocity = MappersComponent.velocity.get(entity);
        BrickComponent bc = MappersComponent.brick.get(entity);

        // Moving
        trans.position.x += velocity.velocity.x * deltaTime;

        // Get witdh Brick
        float width = trans.width;

        // Remove when Brick out of Screen
        if((trans.position.x + width) < 0) {
            this.engine.removeEntity(entity);
        }
    }
}
