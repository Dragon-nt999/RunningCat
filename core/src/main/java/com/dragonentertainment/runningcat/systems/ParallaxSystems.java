package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.utils.GameGrid;

public class ParallaxSystems extends EntitySystem {
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private ImmutableArray<Entity> entities;
    private final ComponentMapper<ParallaxComponent> pm = ComponentMapper.getFor(ParallaxComponent.class);

    public ParallaxSystems(Viewport viewport, SpriteBatch batch, OrthographicCamera camera) {
        this.viewport = viewport;
        this.batch = batch;
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Family.all(ParallaxComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Entity entity : entities) {
            ParallaxComponent pc = pm.get(entity);

            float textureWidth = pc.texture.getWidth();
            float textureHeight = pc.texture.getHeight();

            int width = GameGrid.toGridWidth(textureWidth);
            int height = GameGrid.toGridHeight(textureHeight);

            if(pc.texture != null) {
                batch.draw(pc.texture, pc.parallaxRatioX, pc.parallaxRatioY, width, height);
            }

            /*float camX = camera.position.x;
            float camY = camera.position.y;

            float x = -camX * pc.parallaxRatioX + pc.offsetX;
            float y = -camY * pc.parallaxRatioY + pc.offsetY;

            // Repeat texture in 3x3 grid to cover camera area
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    float drawX = x % textureWidth + dx * textureWidth;
                    float drawY = y % textureHeight + dy * textureHeight;

                    batch.draw(pc.texture, drawX, drawY);
                }
            }*/
        }

        batch.end();
    }
}
