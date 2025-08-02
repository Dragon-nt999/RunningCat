package com.dragonentertainment.runningcat.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.dragonentertainment.runningcat.struct.AssetsName;

public class SoundManager {
    public static SoundManager instance;
    private AssetManager assetManager;

    private boolean soundEnabled = true;
    private boolean musicEnabled = true;

    private SoundManager(){}

    public static SoundManager getInstance() {
        if(instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /*
     * Initial
     * */
    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    /*
    * Setting Sound | Music
    * */
    public void setSoundEnabled(boolean enabled) {
        this.soundEnabled = enabled;
    }

    public void setMusicEnabled(boolean enabled) {
        this.musicEnabled = enabled;
    }

    /*
     * Play Sound
     * */
    public void playSound(String assetName) {
        if(soundEnabled) {
            Sound sound = assetManager.get(assetName, Sound.class);
            sound.play();
        }
    }
    /*
     * Play Music
     * */
    public void playMusic(String assetName) {
        if(musicEnabled) {
            Music music = assetManager.get(assetName, Music.class);
            if(!music.isPlaying()) {
                music.setLooping(true);
                music.setVolume(0.5f);
                music.play();
            }
        }
    }
    public void stopMusic(String assetName) {
        Music music = assetManager.get(assetName, Music.class);
        if(music.isPlaying()) {
            music.stop();
        }
    }

}
