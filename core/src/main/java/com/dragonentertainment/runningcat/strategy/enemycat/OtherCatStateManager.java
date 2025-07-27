package com.dragonentertainment.runningcat.strategy.enemycat;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.strategy.BehaviorStrategy;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.HashMap;
import java.util.Map;

public class OtherCatStateManager {
    private static final Map<CatState, BehaviorStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(CatState.IDLE, new IdleBehavior());
        STRATEGY_MAP.put(CatState.HIT_ENEMY, new HitBehavior());
    }

    public static void changeState(AppGame game, Entity entity) {
        PlayerComponent state = MappersComponent.player.get(entity);

        STRATEGY_MAP.get(state.state).apply(game, entity);

    }
}
