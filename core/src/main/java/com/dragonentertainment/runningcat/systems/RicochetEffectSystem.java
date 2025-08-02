package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class RicochetEffectSystem extends IteratingSystem {
    private boolean soundPlayed = false;
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

            if (ricochet.time > ricochet.duration) {
                GameStateManager.getInstance().setState(GameState.OVER);
            } else { // Play sound effect
                if(!soundPlayed) {
                    SoundManager.getInstance().playSound(AssetsName.Sounds.Game.JUMP_OUT);
                    soundPlayed = true;
                }
            }
        }
    }
}
