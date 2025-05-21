package com.dragonentertainment.runningcat.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.utils.GameGrid;

import java.util.HashSet;
import java.util.Set;

public class BrickFactory {

    private static final float VOLOCITY = -2f;

    public static void createBrick(PooledEngine engine, Texture texture, float x, float y) {
        Entity entity = engine.createEntity();

        TransformComponent tc = engine.createComponent(TransformComponent.class);
        TextureComponent rc = engine.createComponent(TextureComponent.class);
        //VelocityComponent vc = engine.createComponent(VelocityComponent.class);
        BrickComponent bc = engine.createComponent(BrickComponent.class);

        // Set Transform
        tc.position.set(x, y);
        tc.w = tc.h = GameGrid.CELL_SIZE;

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

    public static void createRandomBricks(PooledEngine engine, Texture texture, int count) {
        Set<String> used = new HashSet<>();
        for(int i = 0; i < count; i++) {
            int x, y;
            String key;
            do{
                x = MathUtils.random(0, GameGrid.WORLD_WIDTH - GameGrid.CELL_SIZE);
                y = MathUtils.random(0, GameGrid.WORLD_HEIGHT - GameGrid.CELL_SIZE);
                key = x + "," + y;
            } while(used.contains(key));
            used.add(key);
            createBrick(engine, texture, x, y);
        }
    }
}
