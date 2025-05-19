package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.Struct.AssetName;

import java.awt.TextArea;

public class AssetLoader {
    public static void loadGameScreenAssets(AssetManager assetManager){
        assetManager.load(AssetName.BACKGROUND_GAME, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_1, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_2, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_3, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_4, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_5, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_6, Texture.class);
        assetManager.load(AssetName.MOUNTAIN_6, Texture.class);
        assetManager.load(AssetName.BRICK, Texture.class);
    }

    public static void unlodGameScreenAssets(AssetManager assetManager){
        assetManager.unload(AssetName.BACKGROUND_GAME);
        assetManager.unload(AssetName.MOUNTAIN_1);
        assetManager.unload(AssetName.MOUNTAIN_2);
        assetManager.unload(AssetName.MOUNTAIN_3);
        assetManager.unload(AssetName.MOUNTAIN_4);
        assetManager.unload(AssetName.MOUNTAIN_5);
        assetManager.unload(AssetName.MOUNTAIN_6);
        assetManager.unload(AssetName.BRICK);
    }
}
