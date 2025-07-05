package com.dragonentertainment.runningcat.strategy;

import com.badlogic.ashley.core.Entity;
import com.dragonentertainment.runningcat.AppGame;

public interface BehaviorStrategy {
    void apply(AppGame game, Entity entity);
}
