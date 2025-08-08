package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.TimeUtils;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.Config;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.SoundManager;

public class TouchSystem extends EntitySystem implements InputProcessor
{
    private Entity cat;
    public TouchSystem(PooledEngine engine)
    {
        ImmutableArray<Entity> entities
            = engine.getEntitiesFor(Family.one(PlayerComponent.class).get());

        for(Entity entity : entities)
        {
            RenderTypeComponent type = MappersComponent.type.get(entity);
            if(type.type == RenderType.CAT) {
                this.cat = entity;
                break;
            }
        }
    }

    @Override
    public void update(float deltaTime)
    {
        TouchComponent touch = MappersComponent.touch.get(this.cat);
        if(touch.isPressed) {
            long now = TimeUtils.millis();
            touch.pressDuration = (now - touch.pressStartTime) / 1000f;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        TouchComponent touch = MappersComponent.touch.get(this.cat);

        if(touch.pressDuration == 0 && !GameStateManager.getInstance().is(GameState.PAUSE)) {
            touch.isPressed = true;
            touch.pressStartTime = TimeUtils.millis();
            SoundManager.getInstance().playSound(AssetsName.Sounds.Game.JUMP);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        TouchComponent touch = MappersComponent.touch.get(this.cat);
        touch.isPressed = false;
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
