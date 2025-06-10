package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.components.TouchComponent;
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

        /*-----------------------------------------------------
         * 1. Nhấn xuống nhảy ngay
         *-----------------------------------------------------*/
        if(touch.isPressed
                    && cat.state == CatState.RUNNING
                                        && cat.isOnBrick) {
            catJump.jumpForce = Config.BASE_FORCE;
            velocity.velocity.y = catJump.jumpForce;
            cat.state = CatState.JUMPING;
            cat.isOnBrick = false;
        }

        /*-----------------------------------------------------
         * 2. Đang giữ => tăng lực
         * Chỉ tăng khi đang trên không và chưa max
         *-----------------------------------------------------*/
        if(touch.isPressed &&
                    cat.state == CatState.JUMPING) {
            float duration = Math.min(touch.pressDuration, Config.MAX_DURATION);

            float chargedForce = (duration / Config.MAX_DURATION) * Config.MAX_FORCE;

            if(chargedForce > catJump.jumpForce) {
                catJump.jumpForce = chargedForce;
                velocity.velocity.y = catJump.jumpForce;
            }

            if(duration >= Config.MAX_DURATION) {
                cat.state = CatState.FALLING;
            }
        }

        if(cat.state == CatState.FALLING) {
            touch.pressDuration = 0;
            catJump.jumpForce = 0;
        }
    }
}
