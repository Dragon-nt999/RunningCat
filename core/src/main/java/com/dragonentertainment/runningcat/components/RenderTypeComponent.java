package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import com.dragonentertainment.runningcat.enums.RenderType;

public class RenderTypeComponent implements Component, Pool.Poolable {
    public RenderType type = null;
    public boolean visibility = true;

    @Override
    public void reset() {
        type = null;
        visibility = true;
    }
}
