package com.dragonentertainment.runningcat.strategy.mouse;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.PlayerState;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.strategy.cat.FallingBehavior;
import com.dragonentertainment.runningcat.strategy.cat.HitBehavior;
import com.dragonentertainment.runningcat.strategy.cat.JumpingBehavior;
import com.dragonentertainment.runningcat.strategy.cat.RunningBehavior;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.HashMap;
import java.util.Map;

public class MouseStateManager {
    private static final Map<PlayerState, BehaviorStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(PlayerState.IDLE, new RunningBehavior());
    }

    public static void changeState(AppGame game, Entity entity) {
        PlayerComponent state = MappersComponent.player.get(entity);

        STRATEGY_MAP.get(state.state).apply(game, entity);

    }
}
