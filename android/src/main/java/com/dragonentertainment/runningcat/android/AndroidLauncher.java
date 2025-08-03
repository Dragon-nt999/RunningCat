package com.dragonentertainment.runningcat.android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dragonentertainment.runningcat.AppGame;
import com.dragonentertainment.runningcat.ads.AdController;
import com.dragonentertainment.runningcat.enums.ScreenType;
import com.dragonentertainment.runningcat.screens.LoadingScreen;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication implements AdController {

    private AdView bannerAd;
    private InterstitialAd interstitialAd;
    private Handler handler = new Handler(Looper.getMainLooper());
    private RelativeLayout layout;
    private AppGame game;
    public static final String INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712";
    public static final String BANNER_ID = "ca-app-pub-3940256099942544/9214589741";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        this.game = new AppGame(this);
        initialize(this.game, configuration);

        // Initialization Ads
        MobileAds.initialize(this, initializationStatus -> {});

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(this.game, config);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int adWidth = (int)(metrics.widthPixels / metrics.density);
        AdSize adSize = AdSize.getLandscapeAnchoredAdaptiveBannerAdSize(this, adWidth);

        // Banner Ads
        this.bannerAd = new AdView(this);
        this.bannerAd.setAdSize(adSize);
        bannerAd.setAdUnitId(BANNER_ID);
        this.bannerAd.loadAd(new AdRequest.Builder().build());

        this.layout = new RelativeLayout(this);
        this.layout.addView(gameView);

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        setContentView(layout);

        // Load Interstitial
        loadInterstitial();
    }

    @Override
    public void showBannerAds() {
        handler.post(() -> {
            if (bannerAd.getParent() == null) {
                layout.addView(bannerAd);
            }
            bannerAd.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideBannerAds() {
        handler.post(() -> {
            if (bannerAd.getParent() != null) {
                layout.removeView(bannerAd);
            }
        });
    }

    @Override
    public void showInterstitialAds() {
        handler.post(() -> {
            if (interstitialAd != null) {
                interstitialAd.show(AndroidLauncher.this);
                loadInterstitial(); // load lại sau khi hiện
            }
        });
    }

    @Override
    public boolean isInterstitialLoaded() {
        return interstitialAd != null;
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,
            INTERSTITIAL_ID, // test interstitial ID
            adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd ad) {
                    interstitialAd = ad;

                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            interstitialAd = null;

                            loadInterstitial(); // Load lại tại đây

                            Gdx.app.postRunnable(() -> {
                                if(game != null) {
                                    game.setScreen(new LoadingScreen(game, ScreenType.HOME));
                                }
                            });
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            interstitialAd = null;

                            Gdx.app.postRunnable(() -> {
                                if(game != null) {
                                    game.setScreen(new LoadingScreen(game, ScreenType.HOME));
                                }
                            });
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {

                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError error) {
                    interstitialAd = null;

                    Gdx.app.postRunnable(() -> {
                        if(game != null) {
                            game.setScreen(new LoadingScreen(game, ScreenType.HOME));
                        }
                    });

                }
            });
    }
}
