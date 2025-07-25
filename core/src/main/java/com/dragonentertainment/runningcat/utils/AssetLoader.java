package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetLoader {

    private static final String GAME = "GAME";
    private static final String HOME = "HOME";

    private static Map<String, List<String>> getAssetNames(String name) {
        Map<String, List<String>> assetNames = new HashMap<>();
        FileHandle file = Gdx.files.internal("assets.txt");

        if(file.exists()) {
            String fileContent = file.readString();
            String[] lines = fileContent.split("\\r?\\n");
            List<String> src = new ArrayList<>();
            for(String l : lines) {
                if(!l.trim().isEmpty()) {
                    String[] f = l.split("/");
                    if(f[0].equalsIgnoreCase(name)) {
                        src.add(l);
                    }
                }
            }
            assetNames.put(name, src);
        }

        return assetNames;
    }

    public static void loadGameScreenAssets(AssetManager assetManager){
        Map<String, List<String>> gameSrcs = getAssetNames(GAME);
        if(!gameSrcs.isEmpty()) {
            for(String src : gameSrcs.get(GAME)) {
                loadAssetByType(assetManager, src);
            }
        }

    }

    public static void unloadGameScreenAssets(AssetManager assetManager){
        Map<String, List<String>> gameSrcs = getAssetNames(GAME);
        if(!gameSrcs.isEmpty()) {
            for(String src : gameSrcs.get(GAME)) {
                assetManager.unload(src);
            }
        }
    }

    private static void loadAssetByType(AssetManager assetManager, String src) {
        if(src.endsWith(".png") || src.endsWith(".jpg")) {
            assetManager.load(src, Texture.class);
        } else if(src.endsWith(".mp3") || src.endsWith(".ogg") || src.endsWith(".wav")) {
            assetManager.load(src, Sound.class);
        }
    }
}
