package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.LevelManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class MovementSystem extends IteratingSystem {
    private final PooledEngine engine;
    private float time = 0.0f;

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
        RenderTypeComponent type = MappersComponent.type.get(entity);
        ZIndexComponent zIndex = MappersComponent.zIndex.get(entity);
        AnimationComponent animation = MappersComponent.animation.get(entity);

        if(!trans.canMove) return;

        // Moving
        if(GameStateManager.getInstance().is(GameState.PLAYING)
                        && !GameStateManager.getInstance().is(GameState.PAUSE)){

            if(type.type != RenderType.CAT) {
                /*------------------------------------------------------------------------
                * Set Speed movement X by Level for other Entity
                * ------------------------------------------------------------------------*/
                velocity.velocity.set((Config.X_VELOCITY - LevelManager.getInstance().getLevel()) * zIndex.zIndex, 0);
            } else {
                /*------------------------------------------------------------------------
                 * Set Speed of animation frame for Cat
                 * ------------------------------------------------------------------------*/
                animation.frameDuration = (float) (Config.CAT_MAX_SPEED_RUN / Math.abs(Config.X_VELOCITY - LevelManager.getInstance().getLevel()));
                velocity.velocity.x = 0;
            }

            trans.position.x += velocity.velocity.x * deltaTime;
            trans.position.y += velocity.velocity.y * deltaTime;
        }

        // Get width Brick
        float width = trans.width;

        // Remove entity when out of Screen except Cat
        if(((trans.position.x + width) < 0) && type.type != RenderType.CAT) {
            this.engine.removeEntity(entity);
        }

    }
}
