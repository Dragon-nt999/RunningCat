package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class RenderTypeComponent implements Component {
    public enum Type {PARALLAX_MOUNTAIN, PARALLAX_LOTUS, PARALLAX_CLOUD, BRICK, ENTITY}
    public Type type;
}
