package com.dragonentertainment.runningcat.strategy.cat;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.PlayerState;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.HashMap;
import java.util.Map;

public class CatStateManager {
    private static final Map<PlayerState, BehaviorStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(PlayerState.RUNNING, new RunningBehavior());
        STRATEGY_MAP.put(PlayerState.FALLING, new FallingBehavior());
        STRATEGY_MAP.put(PlayerState.JUMPING, new JumpingBehavior());
        STRATEGY_MAP.put(PlayerState.HIT, new HitBehavior());
    }

    public static void changeState(AppGame game, Entity entity) {
        PlayerComponent state = MappersComponent.player.get(entity);

        STRATEGY_MAP.get(state.state).apply(game, entity);

    }
}
