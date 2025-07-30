package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.FlyingComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.utils.LevelManager;
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
        float progress = Math.min(fly.elapsed / fly.duration, 1f);
        float eased = Interpolation.smooth.apply(progress);

        transform.position.set(
            MathUtils.lerp(fly.start.x, fly.target.x, eased),
            MathUtils.lerp(fly.start.y, fly.target.y, eased)
        );

        /**
         * -------------------------------------------------
         * Add score when coin disappear
         * And increase level
         * -------------------------------------------------
         */
        if (progress >= 1f) {
            ScoreManager.getInstance().addScore();
            LevelManager.getInstance().increase(); //Set Level ++ When Score reach 50
            getEngine().removeEntity(entity);
            fly.elapsed = 0f;
        }
    }
}
