package com.dragonentertainment.runningcat.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class ParallaxFactory {
    private static final float POS_Y = -0.5f;
    private static final float VELOCITY = -0.05f;

    public static void createParallax(PooledEngine engine, Texture texture, float x, float y, float zIndex) {
        Entity entity = engine.createEntity();

        TransformComponent tc = engine.createComponent(TransformComponent.class);
        TextureComponent rc = engine.createComponent(TextureComponent.class);
        ParallaxComponent pc = engine.createComponent(ParallaxComponent.class);
        VelocityComponent vc = engine.createComponent(VelocityComponent.class);

        // Set Transform
        tc.position.set(x, y);
        tc.width = GameGrid.toGridWidth(texture.getWidth());
        tc.height = GameGrid.toGridHeight(texture.getHeight());

        // Set Texture
        rc.texture = texture;

        // Set parallax
        pc.zIndex = zIndex;

        // Set velocity
        vc.velocity.set(VELOCITY * zIndex, 0);

        // Add component
        entity.add(tc);
        entity.add(rc);
        entity.add(vc);
        entity.add(pc);

        engine.addEntity(entity);

    }
}
