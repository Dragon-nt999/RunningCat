package com.dragonentertainment.runningcat.systems.player;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.factory.PlayerFactory;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.ArrayList;
import java.util.List;

public class AnimationSystem extends IteratingSystem {

    private final AppGame game;
    private final PooledEngine engine;
    private final List<String> assetName = new ArrayList<>();

    public AnimationSystem(AppGame game, PooledEngine engine)
    {
        super(Family.all(AnimationComponent.class, RenderTypeComponent.class).get());
        this.game = game;
        this.engine = engine;

        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0001);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0002);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0003);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0004);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0005);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0006);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0007);
        this.assetName.add(AssetsName.Game.Sequence.Cat_running.CAT_RUNNING0008);
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        super.addedToEngine(engine);
        this.generatePlayer();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        AnimationComponent anim = MappersComponent.animation.get(entity);

        anim.stateTime += deltaTime;
        int frameIndex = (int)(anim.stateTime / anim.frameDuration);

        if(anim.loop && !anim.stop)
        {
            frameIndex %= anim.frames.size();
        } else
        {
            frameIndex = Math.min(frameIndex, anim.frames.size() - 1);
        }

        anim.currentFrame = anim.frames.get(frameIndex);

    }

    private void generatePlayer()
    {
        List<Texture> animations = new ArrayList<>();
        for(int i = 0; i < assetName.size(); i++)
        {
            animations.add(this.game.assetManager.get(assetName.get(i), Texture.class));
        }

        PlayerFactory.createCat(
            this.engine,
            animations,
            3,
            2,
            10,
            RenderTypeComponent.Type.CAT);
    }
}
