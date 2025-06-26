package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class MovementSystem extends IteratingSystem {
    private final PooledEngine engine;

    public MovementSystem(PooledEngine engine) {
        super(Family.all(
                            VelocityComponent.class,
                            RenderTypeComponent.class,
                            TransformComponent.class
                        ).get());
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = MappersComponent.transform.get(entity);
        VelocityComponent velocity = MappersComponent.velocity.get(entity);


        // Moving
        if(GameStateManager.getInstance().is(GameState.PLAYING)){
            trans.position.x += velocity.velocity.x * deltaTime;
            trans.position.y += velocity.velocity.y * deltaTime;
        }

        // Get width Brick
        float width = trans.width;

        // Remove entity when out of Screen
        if((trans.position.x + width) < 0) {
            this.engine.removeEntity(entity);
        }

    }
}
