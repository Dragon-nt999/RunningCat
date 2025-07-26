package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.FlyingComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class CoinFactory {
    public static void createCoin(AppGame game, PooledEngine engine, float x, float y) {
        // Create Entity
        Entity coin = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        ZIndexComponent zIndex = engine.createComponent(ZIndexComponent.class);
        RenderTypeComponent type = engine.createComponent(RenderTypeComponent.class);
        FlyingComponent flying = engine.createComponent(FlyingComponent.class);

        // Set texture
        texture.texture = game.assetManager.get(AssetsName.Game.Ui.COIN);

        // Set Transform
        transform.position.set(x, y);
        transform.width = GameGrid.toGridWidth(texture.texture.getWidth()) * Config.SCALE_RATIO;
        transform.height = GameGrid.toGridHeight(texture.texture.getHeight()) * Config.SCALE_RATIO;
        transform.canMove = false;

        // Set zIndex
        zIndex.zIndex = 11;

        // Set Type entity
        type.type = RenderType.COIN;

        flying.target = new Vector2(0, GameGrid.WORLD_HEIGHT);

        coin.add(transform);
        coin.add(texture);
        coin.add(zIndex);
        coin.add(type);
        coin.add(flying);

        engine.addEntity(coin);

    }
}
