package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.GravityComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.ScaleComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.FrameTexture;

public class PlayerFactory {

    public static void createCat(
        AppGame game,
        PooledEngine engine
    ) {
        // Create Entity
        Entity entity = engine.createEntity();

        // Create Components
        TransformComponent transformComponent   = engine.createComponent(TransformComponent.class);
        ZIndexComponent zIndexComponent         = engine.createComponent(ZIndexComponent.class);
        AnimationComponent animationComponent   = engine.createComponent(AnimationComponent.class);
        TextureComponent textureComponent       = engine.createComponent(TextureComponent.class);
        RenderTypeComponent renderTypeComponent = engine.createComponent(RenderTypeComponent.class);
        PlayerComponent playerComponent         = engine.createComponent(PlayerComponent.class);
        VelocityComponent velocityComponent     = engine.createComponent(VelocityComponent.class);
        CollisionComponent collisionComponent   = engine.createComponent(CollisionComponent.class);
        GravityComponent gravityComponent       = engine.createComponent(GravityComponent.class);
        JumpComponent jumpComponent             = engine.createComponent(JumpComponent.class);
        TouchComponent touchComponent           = engine.createComponent(TouchComponent.class);
        ScaleComponent scale                    = engine.createComponent(ScaleComponent.class);

        // Set values of components
        animationComponent.frames = FrameTexture.cat(game);
        //animationComponent.frameDuration = Config.CAT_MAX_SPEED_RUN / Math.abs(Config.X_VELOCITY);

        playerComponent.state     = CatState.RUNNING;
        textureComponent.texture  = animationComponent.frames.get(0);

        playerComponent.position = new Vector3(3, 4, 10);

        transformComponent.position.set(playerComponent.position.x, playerComponent.position.y);

        //transformComponent.width = GameGrid.toGridWidth(textureComponent.texture.getWidth()) * Config.SCALE_RATIO;
        //transformComponent.height = GameGrid.toGridHeight(textureComponent.texture.getHeight()) * Config.SCALE_RATIO;

        transformComponent.width = GameGrid.toGridWidth(textureComponent.texture.getWidth()) * Config.SCALE_RATIO;
        transformComponent.height = GameGrid.toGridHeight(textureComponent.texture.getHeight()) * Config.SCALE_RATIO;

        zIndexComponent.zIndex    = (int)playerComponent.position.z;
        renderTypeComponent.type  = RenderType.CAT;

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
        entity.add(textureComponent);
        entity.add(scale);

        // Add entity to component
        engine.addEntity(entity);
    }

    public static void createMouse(
        AppGame game,
        PooledEngine engine,
        Vector2 position
    ) {
        // Create Entity
        Entity entity = engine.createEntity();

        // Create Components
        TransformComponent transformComponent   = engine.createComponent(TransformComponent.class);
        ZIndexComponent zIndexComponent         = engine.createComponent(ZIndexComponent.class);
        AnimationComponent animationComponent   = engine.createComponent(AnimationComponent.class);
        TextureComponent textureComponent       = engine.createComponent(TextureComponent.class);
        RenderTypeComponent renderTypeComponent = engine.createComponent(RenderTypeComponent.class);
        PlayerComponent playerComponent         = engine.createComponent(PlayerComponent.class);
        CollisionComponent collisionComponent   = engine.createComponent(CollisionComponent.class);
        VelocityComponent velocity              = engine.createComponent(VelocityComponent.class);

        // Set values of components
        animationComponent.frames = FrameTexture.mouse(game);
        animationComponent.frameDuration = (Config.CAT_MAX_SPEED_RUN / Math.abs(Config.X_VELOCITY))
                                                                    + MathUtils.random(0.01f, 0.05f);

        playerComponent.state     = CatState.IDLE;
        textureComponent.texture  = animationComponent.frames.get(0);

        playerComponent.position = new Vector3(position.x, position.y + 1, 9);

        transformComponent.position.set(playerComponent.position.x, playerComponent.position.y);

        transformComponent.width = GameGrid.toGridWidth(textureComponent.texture.getWidth()) * Config.SCALE_RATIO;
        transformComponent.height = GameGrid.toGridHeight(textureComponent.texture.getHeight()) * Config.SCALE_RATIO;

        zIndexComponent.zIndex    = (int)playerComponent.position.z;
        renderTypeComponent.type  = RenderType.MOUSE;

        // Add component to entity
        entity.add(transformComponent);
        entity.add(zIndexComponent);
        entity.add(animationComponent);
        entity.add(renderTypeComponent);
        entity.add(playerComponent);
        entity.add(collisionComponent);
        entity.add(textureComponent);
        entity.add(velocity);

        // Add entity to component
        engine.addEntity(entity);

    }

    public static void createOtherCat(
        AppGame game,
        PooledEngine engine,
        Vector2 position
    ) {
        // Create Entity
        Entity entity = engine.createEntity();

        // Create Components
        TransformComponent transformComponent   = engine.createComponent(TransformComponent.class);
        ZIndexComponent zIndexComponent         = engine.createComponent(ZIndexComponent.class);
        AnimationComponent animationComponent   = engine.createComponent(AnimationComponent.class);
        TextureComponent textureComponent       = engine.createComponent(TextureComponent.class);
        RenderTypeComponent renderTypeComponent = engine.createComponent(RenderTypeComponent.class);
        PlayerComponent playerComponent         = engine.createComponent(PlayerComponent.class);
        CollisionComponent collisionComponent   = engine.createComponent(CollisionComponent.class);
        VelocityComponent velocity              = engine.createComponent(VelocityComponent.class);

        // Set values of components
        animationComponent.frames = FrameTexture.otherCat(game);
        animationComponent.frameDuration = (Config.CAT_MAX_SPEED_RUN / Math.abs(Config.X_VELOCITY))
            + MathUtils.random(0.01f, 0.05f);

        playerComponent.state     = CatState.IDLE;
        textureComponent.texture  = animationComponent.frames.get(0);

        playerComponent.position = new Vector3(position.x, position.y + 1, 9);

        transformComponent.position.set(playerComponent.position.x, playerComponent.position.y);

        transformComponent.width = GameGrid.toGridWidth(textureComponent.texture.getWidth()) * Config.SCALE_RATIO;
        transformComponent.height = GameGrid.toGridHeight(textureComponent.texture.getHeight()) * Config.SCALE_RATIO;

        zIndexComponent.zIndex    = (int)playerComponent.position.z;
        renderTypeComponent.type  = RenderType.ENEMY_CAT;

        // Add component to entity
        entity.add(transformComponent);
        entity.add(zIndexComponent);
        entity.add(animationComponent);
        entity.add(renderTypeComponent);
        entity.add(playerComponent);
        entity.add(collisionComponent);
        entity.add(textureComponent);
        entity.add(velocity);

        // Add entity to component
        engine.addEntity(entity);

    }
}
