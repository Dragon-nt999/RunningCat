package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.FlyingComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.ScoreManager;

public class FlyingSystem extends IteratingSystem {
    public FlyingSystem() {
        super(Family.one(FlyingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(entity == null) return;

        FlyingComponent fly = MappersComponent.flying.get(entity);
        TransformComponent transform = MappersComponent.transform.get(entity);

        fly.elapsed += deltaTime;
        float progress = Math.min(fly.elapsed / fly.duration, fly.duration);

        transform.position.set(
            MathUtils.lerp(transform.position.x, fly.target.x, progress),
            MathUtils.lerp(transform.position.y, fly.target.y, progress)
        );

        if(progress >= fly.duration) {
            ScoreManager.getInstance().addScore();
            fly.elapsed = 0f;
            getEngine().removeEntity(entity);
        }
    }
}
