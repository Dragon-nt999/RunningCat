package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class ParallaxFactory {

    public static void createParallax(PooledEngine engine,
                                      Texture texture,
                                      float x,
                                      float y,
                                      int zIndex,
                                      RenderTypeComponent.Type pType
    ) {
        Entity entity = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent text = engine.createComponent(TextureComponent.class);
        VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
        ZIndexComponent zI = engine.createComponent(ZIndexComponent.class);
        RenderTypeComponent type = engine.createComponent(RenderTypeComponent.class);
        ParallaxComponent parallax = engine.createComponent(ParallaxComponent.class);

        // Set Transform
        transform.position.set(x, y);
        transform.width = GameGrid.toGridWidth(texture.getWidth());
        transform.height = GameGrid.toGridHeight(texture.getHeight());

        // Set Texture
        text.texture = texture;

        // Set velocity
        velocity.velocity.set(Config.X_VELOCITY * zIndex, 0);

        // Set zIndex
        zI.zIndex = zIndex;

        // Set Type
        type.type = pType;

        // Add component
        entity.add(transform);
        entity.add(text);
        entity.add(velocity);
        entity.add(zI);
        entity.add(type);
        entity.add(parallax);

        // Add Entity
        engine.addEntity(entity);

    }
}
