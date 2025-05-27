package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem {

    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TextureComponent.class,
                         TransformComponent.class,
                         ZIndexComponent.class
                        ).get(),
                Comparator.comparing(e -> e.getComponent(ZIndexComponent.class).zIndex)
        );
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.batch.begin();

        TextureComponent text = MappersComponent.texture.get(entity);
        TransformComponent trans = MappersComponent.transform.get(entity);

        // Draw Entity
        this.batch.draw(
            text.texture,
            trans.position.x,
            trans.position.y,
            trans.width,
            trans.height
        );

        this.batch.end();

        Gdx.app.log("FRAME PER SECOND",Gdx.graphics.getFramesPerSecond() + "");
    }
}
