package com.dragonentertainment.runningcat.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.ScaleComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;
import com.dragonentertainment.runningcat.enums.CatState;
import com.dragonentertainment.runningcat.enums.GameState;
import com.dragonentertainment.runningcat.enums.RenderType;
import com.dragonentertainment.runningcat.factory.CoinFactory;
import com.dragonentertainment.runningcat.systems.player.JumpSystem;
import com.dragonentertainment.runningcat.utils.CalculateCollision;
import com.dragonentertainment.runningcat.utils.GameStateManager;
import com.dragonentertainment.runningcat.utils.MappersComponent;
import com.dragonentertainment.runningcat.utils.ScoreManager;

public class CollisionSystem extends EntitySystem
{
    private Entity cat;
    private ImmutableArray<Entity> entities;
    private TransformComponent catTransform = null;
    private CollisionComponent catCollider = null;
    private VelocityComponent catVelocity = null;
    private PlayerComponent catState = null;
    private JumpComponent catJump = null;
    private RicochetEffectComponent ricochet = null;

    private final PooledEngine engine;
    private final AppGame game;

    public CollisionSystem(AppGame game, PooledEngine engine) {
        this.game = game;
        this.engine = engine;
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        // Get Cat
        this.entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
        for(Entity entity : entities) {
            RenderTypeComponent type = MappersComponent.type.get(entity);
            // Get cat
            if(type.type == RenderType.CAT) {
                catTransform = MappersComponent.transform.get(entity);
                catCollider = MappersComponent.collider.get(entity);
                catVelocity = MappersComponent.velocity.get(entity);
                catState = MappersComponent.player.get(entity);
                catJump = MappersComponent.jump.get(entity);
                this.cat = entity;
                break;
            }
        }
    }

    @Override
    public void update(float deltaTime)
    {
        catCollider.bounds.set(catTransform.position.x,
                                catTransform.position.y,
                                catTransform.width,
                                catTransform.height);

        for(Entity entity : this.entities)
        {
            RenderTypeComponent type = MappersComponent.type.get(entity);

            // Cat collides with the brick
            this.catCollidesBrick(entity, type);
            // Cat collides with the mouse
            this.catCollidesMouse(entity, type);
            // Cat collides with the enemy cat
            this.catCollidesEnemyCat(entity, type);
        }
    }

    /**
     * Checking cat collides with the brick
     *
     */
    private void catCollidesBrick(Entity brick, RenderTypeComponent type) {
        if(type.type == RenderType.BRICK) {
            TransformComponent brickTransform = MappersComponent.transform.get(brick);
            CollisionComponent brickCollider = MappersComponent.collider.get(brick);

            brickCollider.bounds.set(brickTransform.position.x,
                                    brickTransform.position.y,
                                    brickTransform.width,
                                    brickTransform.height);

            switch (catState.state) {
                case RUNNING:
                case FALLING:
                    // Tracking Collision between Cat's Bottom and Bricks's Top
                    // And Tracking Collision between Cat's Right and Bricks's Left
                    if(CalculateCollision.aabbOverlapTop(catCollider, brickCollider)){
                        catVelocity.velocity.y = 0;
                        catTransform.position.y = brickTransform.position.y
                            + brickTransform.height;
                        catJump.startY = catTransform.position.y;
                        catJump.endY = catTransform.position.y;
                        catState.isOnBrick = true;
                        catState.state = CatState.RUNNING;
                    } else if(CalculateCollision.aabOverlapRightWhenFalling(catCollider, brickCollider)){
                        catTransform.position.x = brickTransform.position.x - catTransform.width
                            + CalculateCollision.MIN_MARGIN_X;
                    }

                    break;
                case JUMPING:

                    // Tracking Collision between Cat's Top and Bricks's Bottom
                    // And Tracking Collision between Cat's Right and Bricks's Left
                    if(CalculateCollision.aabbOverlapBottom(catCollider, brickCollider)) {
                        catState.state = CatState.HIT_BRICK;
                        catTransform.position.y = brickTransform.position.y - catTransform.height;
                        GameStateManager.getInstance().setState(GameState.STOP);

                        getEngine().getSystem(CollisionSystem.class).setProcessing(false);
                        getEngine().getSystem(JumpSystem.class).setProcessing(false);

                        // Add Ricochet When cat hit brick
                        if(ricochet == null) {
                            this.ricochet = getEngine().createComponent(RicochetEffectComponent.class);
                            this.cat.add(ricochet);
                            if (brick != null) {
                                brick.add(ricochet);
                            }
                        }

                    } else if(CalculateCollision.aabOverlapRightWhenJumping(catCollider, brickCollider)){
                        catTransform.position.x = brickTransform.position.x -
                            catTransform.width + CalculateCollision.MIN_MARGIN_X;
                    }
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * Checking cat collides with the mouse
     */
    private void catCollidesMouse(Entity mouse, RenderTypeComponent type) {
        if(type.type == RenderType.MOUSE) {
            TransformComponent mouseTransform = MappersComponent.transform.get(mouse);
            CollisionComponent mouseColider = MappersComponent.collider.get(mouse);

            mouseColider.bounds.set(mouseTransform.position.x,
                                    mouseTransform.position.y,
                                    mouseTransform.width,
                                    mouseTransform.height);

            if(Intersector.overlaps(this.catCollider.bounds, mouseColider.bounds)
                                                                        && this.catState.state != CatState.HIT_ENEMY) {
                this.engine.removeEntity(mouse);
                CoinFactory.createCoin(this.game, this.engine, mouseTransform.position.x, mouseTransform.position.y);
                ScoreManager.getInstance().addScore();
            }
        }
    }

    /**
     * Checking cat collides with the enemy cat
     */
    private void catCollidesEnemyCat(Entity enemyCat, RenderTypeComponent type) {
        if(type.type == RenderType.ENEMY_CAT) {
            TransformComponent enemyCatTransform = MappersComponent.transform.get(enemyCat);
            CollisionComponent enemyCatCollider = MappersComponent.collider.get(enemyCat);
            PlayerComponent enemyCatState = MappersComponent.player.get(enemyCat);
            ScaleComponent scaleCat = MappersComponent.scale.get(this.cat);


            enemyCatCollider.bounds.set(enemyCatTransform.position.x,
                                        enemyCatTransform.position.y,
                                        enemyCatTransform.width,
                                        enemyCatTransform.height);

            if(CalculateCollision.aabOverlapRightWhenOverOtherEntity(this.catCollider, enemyCatCollider)){
                enemyCatState.state = CatState.HIT_ENEMY;
                scaleCat.isEffect = true; // Trigger to turn on ScaleSystem
                GameStateManager.getInstance().setState(GameState.PAUSE);
            }

        }
    }
}
