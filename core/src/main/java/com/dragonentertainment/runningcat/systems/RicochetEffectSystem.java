package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class RicochetEffectSystem extends IteratingSystem {
    public RicochetEffectSystem() {
        super(Family.all(RicochetEffectComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = MappersComponent.transform.get(entity);
        RicochetEffectComponent ricochet = MappersComponent.ricochet.get(entity);

        if(GameStateManager.getInstance().is(GameState.STOP)) {
            ricochet.time += deltaTime;
            ricochet.triggered = true;
        }

        if(ricochet.triggered) {
            float shakeAmount = 0.05f;
            transform.position.x += MathUtils.random(-shakeAmount, shakeAmount);
            transform.position.y += MathUtils.random(-shakeAmount, shakeAmount);
        }

        if (ricochet.time > ricochet.duration) {
            ricochet.triggered = false;
            entity.remove(RicochetEffectComponent.class);
            GameStateManager.getInstance().setState(GameState.OVER);
        }

        Gdx.app.log("RICOCHET", ricochet.time + "====" + ricochet.duration);

    }
}
