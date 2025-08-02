package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class AnimationSystem extends IteratingSystem {

    public AnimationSystem()
    {
        super(Family.all(AnimationComponent.class, RenderTypeComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        super.addedToEngine(engine);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        AnimationComponent anim = MappersComponent.animation.get(entity);

        anim.stateTime += deltaTime;
        int frameIndex = (int)(anim.stateTime / anim.frameDuration);

        if(anim.loop && !anim.stop)
        {
            frameIndex %= anim.frames.size();
        } else
        {
            frameIndex = Math.min(frameIndex, anim.frames.size() - 1);
        }

        anim.currentFrame = anim.frames.get(frameIndex);

    }
}
