package com.dragonentertainment.runningcat.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.ads.AdController;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication implements AdController {
    private AdView bannerAd;
    private InterstitialAd interstitialAd;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new AppGame(this), configuration);

        // Initialization Ads
        MobileAds.initialize(this, initializationStatus -> {});

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(new AppGame(this), config);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int adWidth = (int)(metrics.widthPixels / metrics.density);
        AdSize adSize = AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(this, adWidth);

        // Banner Ads
        this.bannerAd = new AdView(this);
        this.bannerAd.setAdSize(adSize);
        this.bannerAd.loadAd(new AdRequest.Builder().build());

        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(gameView);

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
          RelativeLayout.LayoutParams.MATCH_PARENT,
          RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.addView(bannerAd, adParams);
        setContentView(layout);
    }

    @Override
    public void showBannerAds() {
        this.handler.post(() -> this.bannerAd.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideBannerAds() {
        this.handler.post(() -> this.bannerAd.setVisibility(View.GONE));
    }

    @Override
    public void showInterstitialAds() {

    }

    @Override
    public boolean isInterstitialLoaded() {
        return false;
    }
}
