package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class JumpSystem extends IteratingSystem {

    public JumpSystem() {
        super(Family.all(PlayerComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = MappersComponent.velocity.get(entity);
        TouchComponent touch = MappersComponent.touch.get(entity);
        PlayerComponent cat = MappersComponent.player.get(entity);

        if(touch.isPressed) {
            if(cat.state != CatState.JUMPING) {
                cat.jumpForce = touch.pressDuration * 20;
                velocity.velocity.y = cat.jumpForce;
                cat.state = CatState.JUMPING;
            }
        } else {
            cat.state = CatState.FALLING;
        }

        Gdx.app.log("CAT-STATE", cat.jumpForce + "===" + cat.state);

    }
}
