package com.dragonentertainment.runningcat.strategy.cat;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class FallingBehavior implements BehaviorStrategy {
    @Override
    public void apply(AppGame game, Entity entity) {
        TextureComponent texture = MappersComponent.texture.get(entity);
        texture.texture = game.assetManager.
                                    get(AssetsName.Game.Sequence.Cat_jumping.CAT_JUMPING_2);
    }
}
