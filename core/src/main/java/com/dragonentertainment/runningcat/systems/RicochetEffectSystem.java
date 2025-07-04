package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
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
            float shake = MathUtils.cos(ricochet.time * ricochet.frequency) * ricochet.shakeAmount;
            transform.position.y += shake;
        }

        if (ricochet.time > ricochet.duration) {
            entity.remove(RicochetEffectComponent.class);
            getEngine().getSystem(MovementSystem.class).setProcessing(false);
            GameStateManager.getInstance().setState(GameState.OVER);
        }

    }
}
