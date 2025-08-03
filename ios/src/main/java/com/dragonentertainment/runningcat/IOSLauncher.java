package com.dragonentertainment.runningcat;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.dragonentertainment.runningcat.ads.AdController;

/** Launches the iOS (RoboVM) application. */
public class IOSLauncher extends IOSApplication.Delegate implements AdController {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration configuration = new IOSApplicationConfiguration();
        return new IOSApplication(new AppGame(this), configuration);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
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
