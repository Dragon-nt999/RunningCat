package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.ScaleComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.factory.EffectFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class ScaleSystem extends IteratingSystem {

    private final Entity effect;
    public ScaleSystem(AppGame game, PooledEngine engine) {
        super(Family.one(ScaleComponent.class).get());
        this.effect = EffectFactory.createEffect(game, engine, AssetsName.Game.Sequence.Cat_failed.SCREEN_BREAKING, 1, 5, 12);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(entity == null) return;

        ScaleComponent scale = MappersComponent.scale.get(entity);
        TransformComponent transform = MappersComponent.transform.get(entity);
        PlayerComponent cat = MappersComponent.player.get(entity);
        RenderTypeComponent effect = MappersComponent.type.get(this.effect);

        if(scale.isEffect && scale.start > -1f) {
            scale.start += deltaTime;
        }

        if(scale.start >= scale.delay) {

            cat.state = CatState.HIT_ENEMY;

            scale.elapsed += deltaTime;

            float progress = Math.min(scale.elapsed / scale.duration, 1f);

            transform.width = MathUtils.lerp(transform.width, scale.wTarget, progress);
            transform.height = MathUtils.lerp(transform.height, scale.hTarget, progress);

            transform.position.set(
                MathUtils.lerp(transform.position.x, 0.5f, progress),
                MathUtils.lerp(transform.position.y, 5, progress)
            );

            if(progress >= scale.duration && scale.timeRecochet < scale.duration) {
                scale.timeRecochet += deltaTime;
                float shake = MathUtils.cos(scale.elapsed * 50f) * 0.2f;
                transform.width += shake;
                transform.height += shake;
                effect.visibility = true;
                GameStateManager.getInstance().setState(GameState.OVER);
            }
        }
    }
}
