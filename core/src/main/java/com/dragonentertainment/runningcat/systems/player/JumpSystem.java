package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.utils.CalculateCollision;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameStateManager;
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
         *   JUMP Cat When touching
         * ---------------------------------------- */
        if(touch.isPressed && touch.pressDuration < Config.MAX_PRESS_DURATION) {
            velocity.velocity.y = touch.pressDuration * 15f;
            cat.state = CatState.JUMPING;
            cat.isOnBrick = false;
        }

        /*----------------------------------------
         *  Tracking cat 's position y during jump and falling
         * ---------------------------------------- */
        if(!touch.isPressed || touch.pressDuration >= Config.MAX_PRESS_DURATION) {
            if(catJump.endY < catTransform.position.y) {
                catJump.endY = catTransform.position.y;
            }
        }

        /*----------------------------------------
        * If cat is Jump and has maxJump > endY => cat is falling
        * ----------------------------------------*/
        if(catJump.endY > 0 && catTransform.position.y < catJump.endY) {
            cat.state = CatState.FALLING;
        }

        /*----------------------------------------
         * If cat is startY > endY => cat is free falling
         * ----------------------------------------*/
        if(catJump.startY > catJump.endY && cat.state != CatState.JUMPING) {
            cat.isOnBrick = false;
            touch.pressDuration = -1;
            cat.state = CatState.FALLING;
        }

        /*----------------------------------------
         *  Release touch when cat on the brick
         * ---------------------------------------- */
        if(cat.isOnBrick) {
            touch.pressDuration = 0;
            catJump.endY = 0;
        }

        // Game over when cat fall out of screen
        if(cat.state == CatState.FALLING) {
            if((catTransform.position.y + catTransform.height * 2) < 0
                    || (catTransform.position.x + catTransform.width) < 0) {
                //cat.state = CatState.FALL_OUT;
                GameStateManager.getInstance().setState(GameState.STOP);
            }
        }

    }
}
