package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class ParallaxComponent implements Component {
    public Texture texture;
    public float parallaxRatioX;
    public float parallaxRatioY;
    public float offsetX = 0;
    public float offsetY = 0;
}
