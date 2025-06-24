package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.utils.CalculateCollision;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;

public class CollisionSystem extends EntitySystem
{
    private Entity cat;
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine)
    {
        // Get Cat
        this.entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
        for(Entity entity : entities) {
            RenderTypeComponent type = MappersComponent.type.get(entity);
            // Get cat
            if(type.type == RenderTypeComponent.Type.CAT) {
                this.cat = entity;
                break;
            }
        }
    }

    @Override
    public void update(float deltaTime)
    {
        if(this.cat != null) {
            // Set Cat bound
            TransformComponent catTransform = MappersComponent.transform.get(this.cat);
            CollisionComponent catCollider = MappersComponent.collider.get(this.cat);
            VelocityComponent catVelocity = MappersComponent.velocity.get(this.cat);
            PlayerComponent cat = MappersComponent.player.get(this.cat);
            JumpComponent catJump = MappersComponent.jump.get(this.cat);

            catCollider.bounds.set(catTransform.position.x,
                                    catTransform.position.y,
                                    catTransform.width,
                                    catTransform.height);

            // Check cat with bricks
            for(Entity brick : this.entities)
            {
                RenderTypeComponent type = MappersComponent.type.get(brick);
                if(type.type != RenderTypeComponent.Type.BRICK) continue;

                TransformComponent brickTransform = MappersComponent.transform.get(brick);
                CollisionComponent brickCollider = MappersComponent.collider.get(brick);

                brickCollider.bounds.set(brickTransform.position.x,
                                            brickTransform.position.y,
                                            brickTransform.width,
                                            brickTransform.height);

                switch (cat.state) {
                    case RUNNING:
                    case FALLING:
                        if(CalculateCollision.aabbOverlapTop(catCollider, brickCollider)){
                            catVelocity.velocity.y = 0;
                            catTransform.position.y = brickTransform.position.y
                                                                            + brickTransform.height;
                            catJump.startY = catTransform.position.y;
                            catJump.endY = catTransform.position.y;
                            cat.isOnBrick = true;
                            cat.state = CatState.RUNNING;
                        }
                        break;
                    case JUMPING:
                        if(CalculateCollision.aabbOverlapBottom(catCollider, brickCollider)) {
                            catTransform.position.y = brickTransform.position.y - catTransform.height;
                            cat.state = CatState.HIT;
                        }
                        break;
                    default:
                        break;
                }

                Gdx.app.log("JUMP", cat.state + "");
            }

        }
    }

}
