package com.dragonentertainment.runningcat.systems.parallax;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;

public class ParallaxMovementSystem extends IteratingSystem {

    private final Viewport viewport;

    public ParallaxMovementSystem(Viewport viewport) {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
        this.viewport = viewport;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent trans = MappersComponent.transform.get(entity);
        VelocityComponent velocity = MappersComponent.velocity.get(entity);

        trans.position.x += velocity.velocity.x * deltaTime;

        float width = trans.width;

        if((trans.position.x + width) < 0) {
            trans.position.x = MathUtils.random(this.viewport.getWorldWidth(),
                                                            this.viewport.getWorldWidth() * 2);
        }
    }
}
