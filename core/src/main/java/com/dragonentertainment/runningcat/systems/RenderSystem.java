package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.struct.AssetsName;
import com.dragonentertainment.runningcat.utils.MappersComponent;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem
{
    private final AppGame game;
    private final SpriteBatch batch;

    public RenderSystem(AppGame game, SpriteBatch batch)
    {
        super(Family.all(RenderTypeComponent.class,
                         ZIndexComponent.class
                        ).get(),
                Comparator.comparing(e -> e.getComponent(ZIndexComponent.class).zIndex)
        );
        this.game = game;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        this.batch.begin();

        TextureComponent text = MappersComponent.texture.get(entity);
        TransformComponent trans = MappersComponent.transform.get(entity);
        AnimationComponent anim = MappersComponent.animation.get(entity);
        RenderTypeComponent type = MappersComponent.type.get(entity);

        if(type.type == RenderTypeComponent.Type.CAT) {
            PlayerComponent cat = MappersComponent.player.get(entity);
            try {
                switch (cat.state) {
                    case RUNNING:
                        text.texture = anim.currentFrame;
                        //text.texture = this.game.assetManager.
                        //get(AssetsName.Game.Sequence.Cat_jumping.CAT_JUMPING_1);
                        break;
                    case JUMPING:
                        text.texture = this.game.assetManager.
                            get(AssetsName.Game.Sequence.Cat_jumping.CAT_JUMPING_1);
                        break;
                    case FALLING:
                        text.texture = this.game.assetManager.
                            get(AssetsName.Game.Sequence.Cat_jumping.CAT_JUMPING_2);
                        break;
                    case HIT:
                        text.texture = this.game.assetManager.
                            get(AssetsName.Game.Sequence.Cat_failed.CAT_FAILED_1);
                        break;
                    default:
                        break;
                }
            } catch (RuntimeException e) {
                Gdx.app.log("ERROR", e.getLocalizedMessage());
            }
        }
        // Draw Entity
        this.batch.draw(
            text.texture,
            trans.position.x,
            trans.position.y,
            trans.width,
            trans.height
        );

        this.batch.end();

    }
}
