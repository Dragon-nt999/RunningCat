package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class BoundsRenderSystem extends IteratingSystem
{
    private final ShapeRenderer shape = new ShapeRenderer();

    private final OrthographicCamera camera;


    public BoundsRenderSystem(OrthographicCamera camera)
    {
        super(Family.all(CollisionComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        CollisionComponent collider = MappersComponent.collider.get(entity);

        // Draw Rectangle Bounds for Texting
        if(collider != null)
        {
            Rectangle bounds = collider.bounds;
            this.shape.setProjectionMatrix(this.camera.combined);
            this.shape.begin(ShapeRenderer.ShapeType.Line);
            this.shape.setColor(Color.RED);
            this.shape.rect(bounds.x, bounds.y, bounds.width, bounds.height);
            this.shape.end();
        }
    }
}
