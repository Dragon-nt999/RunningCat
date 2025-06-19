package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.GravityComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

import java.util.List;

public class PlayerFactory {
    public static void createCat(
        PooledEngine engine,
        List<Texture> frames,
        float x,
        float y,
        int zIndex,
        RenderTypeComponent.Type type
    ) {
        // Create Entity
        Entity entity = engine.createEntity();

        // Create Components
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class);
        ZIndexComponent zIndexComponent       = engine.createComponent(ZIndexComponent.class);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        RenderTypeComponent renderTypeComponent = engine.createComponent(RenderTypeComponent.class);
        PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        VelocityComponent velocityComponent = engine.createComponent(VelocityComponent.class);
        CollisionComponent collisionComponent = engine.createComponent(CollisionComponent.class);
        GravityComponent gravityComponent = engine.createComponent(GravityComponent.class);
        JumpComponent jumpComponent = engine.createComponent(JumpComponent.class);
        TouchComponent touchComponent = engine.createComponent(TouchComponent.class);

        // Set values of components
        animationComponent.frames = frames;
        animationComponent.frameDuration = Math.abs(Config.X_VELOCITY + 0.1f);

        transformComponent.position.set(x, y);
        transformComponent.previous_y = y;
        transformComponent.width  = GameGrid.toGridWidth(frames.get(0).getWidth()) * 1.3f;
        transformComponent.height = GameGrid.toGridHeight(frames.get(0).getHeight()) * 1.3f;
        zIndexComponent.zIndex    = zIndex;
        renderTypeComponent.type  = type;
        playerComponent.state = CatState.RUNNING;

        // Add component to entity
        entity.add(transformComponent);
        entity.add(zIndexComponent);
        entity.add(animationComponent);
        entity.add(renderTypeComponent);
        entity.add(playerComponent);
        entity.add(velocityComponent);
        entity.add(collisionComponent);
        entity.add(gravityComponent);
        entity.add(jumpComponent);
        entity.add(touchComponent);

        // Add entity to component
        engine.addEntity(entity);
    }
}
