package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.graphics.Texture;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.struct.AssetsName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FrameTexture {

    public static List<Texture> catInGame(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Game.Sequence.Cat_running.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    public static List<Texture> catInHomeIdle(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Home.Sequences.Cat.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    public static List<Texture> catInHomeInjured(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Home.Sequences.Cat_injured.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    public static List<Texture> mouse(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Game.Sequence.Mouse.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    public static List<Texture> otherCat(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Game.Sequence.Neighbor_cat.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    public static List<Texture> otherCatHit(AppGame game){
        List<Texture> frames = new ArrayList<>();

        for(String name : getFramesFromClass(AssetsName.Game.Sequence.Neighbor_cat_hit.class)) {
            frames.add(game.assetManager.get(name));
        }

        return frames;
    }

    private static List<String> getFramesFromClass(Class<?> clazz) {
        List<String> frames = new ArrayList<>();

        for(Field field : clazz.getDeclaredFields()) {
            if(Modifier.isStatic(field.getModifiers()) && field.getType() == String.class){
                try{
                    frames.add((String) field.get(null));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        frames.sort(Comparator.naturalOrder());
        return frames;
    }

}
