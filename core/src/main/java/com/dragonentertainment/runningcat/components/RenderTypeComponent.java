package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;

public class RenderTypeComponent implements Component {
    public enum Type {PARALLAX, BRICK, ENTITY}
    public Type type;
}
