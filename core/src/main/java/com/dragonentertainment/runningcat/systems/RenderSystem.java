package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem
{
    //private final AppGame game;
    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch)
    {
        super(Family.all(RenderTypeComponent.class,
                         ZIndexComponent.class
                        ).get(),
                Comparator.comparing(e -> e.getComponent(ZIndexComponent.class).zIndex)
        );
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        RenderTypeComponent render = MappersComponent.type.get(entity);
        TextureComponent texture = MappersComponent.texture.get(entity);
        TransformComponent trans = MappersComponent.transform.get(entity);

        if(!render.visibility) return;

        this.batch.begin();
        // Draw Entity
        this.batch.draw(
            texture.texture,
            trans.position.x,
            trans.position.y,
            trans.width,
            trans.height
        );

        this.batch.end();

    }
}
