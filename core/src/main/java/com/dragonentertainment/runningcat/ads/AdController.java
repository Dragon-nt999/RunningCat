package com.dragonentertainment.runningcat.ads;

public interface AdController {
    void showBannerAds();
    void hideBannerAds();
    void showInterstitialAds();
    boolean isInterstitialLoaded();
}
