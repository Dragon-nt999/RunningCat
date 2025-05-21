package com.dragonentertainment.runningcat.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.dragonentertainment.runningcat.components.ParallaxComponent;
import com.dragonentertainment.runningcat.components.TextureComponent;
import com.dragonentertainment.runningcat.components.TransformComponent;
import com.dragonentertainment.runningcat.components.VelocityComponent;
import com.dragonentertainment.runningcat.components.brick.BrickComponent;

public class MappersComponent {
    public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<BrickComponent> brick = ComponentMapper.getFor(BrickComponent.class);
    public static final ComponentMapper<ParallaxComponent> parallax = ComponentMapper.getFor(ParallaxComponent.class);
}
