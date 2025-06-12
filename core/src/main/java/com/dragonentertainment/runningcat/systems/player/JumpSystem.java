package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class JumpSystem extends IteratingSystem {

    public JumpSystem() {
        super(Family.all(PlayerComponent.class).get());

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocity = MappersComponent.velocity.get(entity);
        TouchComponent touch       = MappersComponent.touch.get(entity);
        PlayerComponent cat        = MappersComponent.player.get(entity);
        JumpComponent catJump      = MappersComponent.jump.get(entity);
        TransformComponent catTransform = MappersComponent.transform.get(entity);

        /*----------------------------------------
         *   JUMP
         * ---------------------------------------- */
        if(touch.isPressed) {
            if(touch.pressDuration < Config.MAX_PRESS_DURATION) {
                velocity.velocity.y = touch.pressDuration * 10;
                cat.state = CatState.JUMPING;
            }
        }

        /*----------------------------------------
         *  Tracking cat 's position y after jump
         * ---------------------------------------- */
        catJump.endY = catTransform.position.y;

        /*----------------------------------------
         *  Release touch when cat on the brick
         * ---------------------------------------- */
        if(catJump.endY - catJump.startY <= 0) {
            touch.pressDuration = 0;
        }

    }
}
