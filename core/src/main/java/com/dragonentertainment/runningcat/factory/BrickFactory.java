package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class BrickFactory {

    private static final float VOLOCITY = -2f;

    public static void createBrick(PooledEngine engine, Texture texture, float x, float y) {
        Entity entity = engine.createEntity();

        TransformComponent tc = engine.createComponent(TransformComponent.class);
        TextureComponent rc = engine.createComponent(TextureComponent.class);
        BrickComponent bc = engine.createComponent(BrickComponent.class);

        // Set Transform
        tc.position.set(x, y);
        tc.width = tc.height = GameGrid.CELL_SIZE;

        // Set Texture
        rc.texture = texture;

        // Set velocity
        //vc.velocity.set(VOLOCITY, 0);

        // Add component
        entity.add(tc);
        entity.add(rc);
        //entity.add(vc);
        entity.add(bc);

        engine.addEntity(entity);

    }

}
