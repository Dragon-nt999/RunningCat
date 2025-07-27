package com.dragonentertainment.runningcat.strategy.cat;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.ScaleComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameGrid;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class HitEnemyCatBehavior implements BehaviorStrategy {
    @Override
    public void apply(AppGame game, Entity entity) {
        TextureComponent texture = MappersComponent.texture.get(entity);
        ScaleComponent catScale = MappersComponent.scale.get(entity);

        texture.texture = game.assetManager.
                                get(AssetsName.Game.Sequence.Cat_failed.CAT_FAILED_2);

        catScale.isEffect = true;

        catScale.wTarget = GameGrid.toGridWidth(texture.texture.getWidth());
        catScale.hTarget = GameGrid.toGridHeight(texture.texture.getHeight());

    }
}
