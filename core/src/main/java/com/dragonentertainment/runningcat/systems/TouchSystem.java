package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class TouchSystem extends EntitySystem implements InputProcessor {
    private final Entity cat;
    public TouchSystem(PooledEngine engine) {
        Gdx.input.setInputProcessor(this);
        this.cat = engine.getEntitiesFor(Family.one(PlayerComponent.class).get()).get(0);
    }

    @Override
    public void update(float deltaTime) {
        TouchComponent touch = MappersComponent.touch.get(this.cat);
        JumpComponent jump = MappersComponent.jump.get(this.cat);
        if(touch.isPressed) {
            touch.pressDuration += deltaTime;
            jump.jumpForce = touch.pressDuration * 10;
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
        touch.isPressed = true;
        touch.pressDuration = 0;
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
