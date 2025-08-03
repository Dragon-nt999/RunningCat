package com.dragonentertainment.runningcat.screens;

import com.dragonentertainment.runningcat.AppGame;

public class AdsScreen extends BaseScreen {
    public AdsScreen(AppGame game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
        if(this.game.adController.isInterstitialLoaded()) {
            this.game.adController.showInterstitialAds();
        }

    }

    @Override
    protected void renderContent(float delta) {

    }
}
