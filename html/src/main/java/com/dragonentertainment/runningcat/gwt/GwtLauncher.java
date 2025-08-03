package com.dragonentertainment.runningcat.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.ads.AdController;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication implements AdController {
        @Override
        public GwtApplicationConfiguration getConfig () {
            // Resizable application, uses available space in browser with no padding:
            GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(true);
            cfg.padVertical = 0;
            cfg.padHorizontal = 0;
            return cfg;
            // If you want a fixed size application, comment out the above resizable section,
            // and uncomment below:
            //return new GwtApplicationConfiguration(640, 480);
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new AppGame(this);
        }

    @Override
    public void showBannerAds() {

    }

    @Override
    public void hideBannerAds() {

    }

    @Override
    public void showInterstitialAds() {

    }

    @Override
    public boolean isInterstitialLoaded() {
        return false;
    }
}
