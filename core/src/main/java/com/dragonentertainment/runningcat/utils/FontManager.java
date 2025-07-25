package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.dragonentertainment.runningcat.struct.AssetsName;

public class FontManager {
    private static FontManager instance;
    public static FontManager getInstance() {
        if(instance == null) {
            instance = new FontManager();
        }

        return instance;
    }

    public BitmapFont getFont(int size, Color color) {
        FileHandle fontFile = Gdx.files.internal(AssetsName.Fonts.SHOWG);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
    }

}
