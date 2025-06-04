package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class BrickFactory {

    public static void createBrick(PooledEngine engine,
                                   Texture texture,
                                   float x,
                                   float y,
                                   int zIndex
    ) {
        Entity entity = engine.createEntity();

        TransformComponent trans = engine.createComponent(TransformComponent.class);
        TextureComponent text   = engine.createComponent(TextureComponent.class);
        VelocityComponent velocity  = engine.createComponent(VelocityComponent.class);
        ZIndexComponent zI    = engine.createComponent(ZIndexComponent.class);
        RenderTypeComponent type = engine.createComponent(RenderTypeComponent.class);
        BrickComponent bc = engine.createComponent(BrickComponent.class);
        CollisionComponent cc = engine.createComponent(CollisionComponent.class);

        // Set Transform
        trans.position.set(x, y);
        trans.width = trans.height = GameGrid.CELL_SIZE;

        // Set Texture
        text.texture = texture;

        // Set velocity
        velocity.velocity.set(Config.X_VELOCITY * zIndex, 0);

        // Set zIndex
        zI.zIndex = zIndex;

        // Set Type
        type.type = RenderTypeComponent.Type.BRICK;

        // Set Brick index
        bc.index.set(x, y);

        // Add component
        entity.add(trans);
        entity.add(text);
        entity.add(velocity);
        entity.add(zI);
        entity.add(type);
        entity.add(bc);
        entity.add(cc);

        engine.addEntity(entity);

    }

}
