package com.dragonentertainment.runningcat.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.dragonentertainment.runningcat.components.AnimationComponent;
import com.dragonentertainment.runningcat.components.CollisionComponent;
import com.dragonentertainment.runningcat.components.GravityComponent;
import com.dragonentertainment.runningcat.components.RenderTypeComponent;
import com.dragonentertainment.runningcat.components.RicochetEffectComponent;
import com.dragonentertainment.runningcat.components.TouchComponent;
import com.dragonentertainment.runningcat.components.parallax.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.ZIndexComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;
import com.dragonentertainment.runningcat.components.player.JumpComponent;
import com.dragonentertainment.runningcat.components.player.PlayerComponent;

public class MappersComponent {
    public static final ComponentMapper<TransformComponent> transform =
                                            ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<TextureComponent> texture =
                                            ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity =
                                            ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<BrickComponent> brick =
                                            ComponentMapper.getFor(BrickComponent.class);
    public static final ComponentMapper<ParallaxComponent> parallax =
                                            ComponentMapper.getFor(ParallaxComponent.class);
    public static final ComponentMapper<ZIndexComponent> zIndex =
                                            ComponentMapper.getFor(ZIndexComponent.class);
    public static final ComponentMapper<RenderTypeComponent> type =
                                            ComponentMapper.getFor(RenderTypeComponent.class);
    public static final ComponentMapper<AnimationComponent> animation =
                                            ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<PlayerComponent> player =
                                            ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<CollisionComponent> collider =
                                            ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<GravityComponent> gravity =
                                            ComponentMapper.getFor(GravityComponent.class);
    public static final ComponentMapper<TouchComponent> touch =
                                            ComponentMapper.getFor(TouchComponent.class);
    public static final ComponentMapper<JumpComponent> jump =
                                            ComponentMapper.getFor(JumpComponent.class);
    public static final ComponentMapper<RicochetEffectComponent> ricochet =
                                            ComponentMapper.getFor(RicochetEffectComponent.class);
}
