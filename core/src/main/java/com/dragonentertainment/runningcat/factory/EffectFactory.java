package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class EffectFactory {
    public static Entity createEffect(AppGame game, PooledEngine engine, String textureEffect, float x, float y, int zIndex) {
        Entity entity = engine.createEntity();

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        RenderTypeComponent render = engine.createComponent(RenderTypeComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);

        ZIndexComponent zIndexC = engine.createComponent(ZIndexComponent.class);

        texture.texture = game.assetManager.get(textureEffect);
        render.visibility = false;

        transform.position.set(x, y);

        transform.width = GameGrid.toGridWidth(texture.texture.getWidth());
        transform.height = GameGrid.toGridHeight(texture.texture.getHeight());

        zIndexC.zIndex = zIndex;

        entity.add(texture);
        entity.add(render);
        entity.add(transform);
        entity.add(zIndexC);

        engine.addEntity(entity);

        return entity;
    }
}
