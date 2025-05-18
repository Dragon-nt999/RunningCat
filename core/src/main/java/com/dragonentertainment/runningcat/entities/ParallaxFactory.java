package com.dragonentertainment.runningcat.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.components.ParallaxComponent;

public class ParallaxFactory {
    public static Entity createEntity(Texture texture, float ratioX, float ratioY) {
        Entity entity = new Entity();
        ParallaxComponent pc = new ParallaxComponent();
        pc.texture = texture;
        pc.parallaxRatioX = ratioX;
        pc.parallaxRatioY = ratioY;
        entity.add(pc);
        return entity;
    }
}
