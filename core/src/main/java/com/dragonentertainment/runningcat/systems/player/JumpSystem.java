package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class JumpSystem extends IteratingSystem {

    public JumpSystem() {
        super(Family.all(JumpComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        JumpComponent jump = MappersComponent.jump.get(entity);
        VelocityComponent velocity = MappersComponent.velocity.get(entity);
        TouchComponent touch = MappersComponent.touch.get(entity);
        PlayerComponent cat = MappersComponent.player.get(entity);

        if(touch.isPressed && jump.jumpForce < Config.MAX_JUMP && cat.isOnBrick){
            velocity.velocity.y = jump.jumpForce;
        }

    }
}
