package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.components.GravityComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class GravitySystem extends IteratingSystem {
    public GravitySystem() {
        super(Family.all(GravityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = MappersComponent.velocity.get(entity);
        GravityComponent gravity = MappersComponent.gravity.get(entity);

        if(GameStateManager.getInstance().is(GameState.PAUSE)) return;

        velocity.velocity.y += gravity.gravity * deltaTime;
    }
}
