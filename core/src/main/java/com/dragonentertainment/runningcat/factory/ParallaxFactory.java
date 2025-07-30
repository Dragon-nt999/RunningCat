package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class ParallaxFactory {

    public static void createParallax(PooledEngine engine,
                                      Texture texture,
                                      float x,
                                      float y,
                                      int zIndex,
                                      RenderType type
    ) {
        Entity entity = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent text = engine.createComponent(TextureComponent.class);
        VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
        ZIndexComponent zI = engine.createComponent(ZIndexComponent.class);
        RenderTypeComponent renderType = engine.createComponent(RenderTypeComponent.class);
        ParallaxComponent parallax = engine.createComponent(ParallaxComponent.class);
        RicochetEffectComponent ricochet = engine.createComponent(RicochetEffectComponent.class);

        // Set Transform
        transform.position.set(x, y);
        transform.width = GameGrid.toGridWidth(texture.getWidth()) * 0.8f;
        transform.height = GameGrid.toGridHeight(texture.getHeight()) * 0.8f;

        // Set Texture
        text.texture = texture;

        // Set zIndex
        zI.zIndex = zIndex;

        // Set Type
        renderType.type = type;

        // Add component
        entity.add(transform);
        entity.add(text);
        entity.add(velocity);
        entity.add(zI);
        entity.add(renderType);
        entity.add(parallax);
        entity.add(ricochet);

        // Add Entity
        engine.addEntity(entity);

    }
}
