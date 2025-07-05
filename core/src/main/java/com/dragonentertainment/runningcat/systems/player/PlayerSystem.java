package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.strategy.cat.CatStateManager;
import com.dragonentertainment.runningcat.strategy.mouse.MouseStateManager;
import com.dragonentertainment.runningcat.strategy.othercat.OtherCatStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class PlayerSystem extends IteratingSystem {

    private final AppGame game;

    public PlayerSystem(AppGame game) {
        super(Family.one(PlayerComponent.class).get());
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderTypeComponent type = MappersComponent.type.get(entity);

        if(type.type == RenderTypeComponent.Type.CAT){
            CatStateManager.changeState(this.game, entity);
        } else if(type.type == RenderTypeComponent.Type.MOUSE){
            MouseStateManager.changeState(this.game, entity);
        } else if(type.type == RenderTypeComponent.Type.OTHER_CAT){
            OtherCatStateManager.changeState(this.game, entity);
        }
    }
}
