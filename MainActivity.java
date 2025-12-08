package com.hazem.multitaskapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.CookieManager;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);
        ws.setMediaPlaybackRequiresUserGesture(false);

        // Load remote URL (your GitHub Pages)
        webView.loadUrl("https://ibrahimmostafahazem-dotcom.github.io/9-IN-1-MULTI-TASKS-app/");

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // Banner
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Interstitial
        InterstitialAd.load(this, "ca-app-pub-7741711065800080/8024556837",
            new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
                @Override public void onAdLoaded(InterstitialAd ad) { mInterstitialAd = ad; }
                @Override public void onAdFailedToLoad(LoadAdError error) { mInterstitialAd = null; }
            });

        // Rewarded
        RewardedAd.load(this, "ca-app-pub-7741711065800080/1994852029",
            new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
                @Override public void onAdLoaded(RewardedAd ad) { mRewardedAd = ad; }
                @Override public void onAdFailedToLoad(LoadAdError error) { mRewardedAd = null; }
            });
    }
}
