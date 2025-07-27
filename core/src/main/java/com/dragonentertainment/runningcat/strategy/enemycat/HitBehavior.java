package com.dragonentertainment.runningcat.strategy.enemycat;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.utils.FrameTexture;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class HitBehavior implements BehaviorStrategy {
    @Override
    public void apply(AppGame game, Entity entity) {
        TextureComponent texture = MappersComponent.texture.get(entity);
        AnimationComponent animation = MappersComponent.animation.get(entity);
        animation.frames = FrameTexture.otherCatHit(game);
        animation.loop = false;
        animation.frameDuration = 0.75f;

        texture.texture = animation.currentFrame == null ? animation.frames.get(0)
                                                        : animation.currentFrame;
    }
}
