package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class RenderTypeComponent implements Component {
    public enum Type {
        PARALLAX_MOUNTAIN,
        PARALLAX_LOTUS_BACK,
        PARALLAX_LOTUS_FRONT,
        PARALLAX_CLOUD,
        BRICK,
        CAT,
        MOUSE,
        OTHER_CAT}
    public Type type;
}
