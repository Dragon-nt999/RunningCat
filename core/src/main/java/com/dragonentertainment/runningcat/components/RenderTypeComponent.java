package com.dragonentertainment.runningcat.components;

import com.badlogic.ashley.core.Component;
import com.dragonentertainment.runningcat.enums.RenderType;

public class RenderTypeComponent implements Component {
    public RenderType type;
    public boolean visibility = true;
}
