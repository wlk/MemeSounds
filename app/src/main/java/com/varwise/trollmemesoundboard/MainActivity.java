package com.varwise.trollmemesoundboard;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import hotchemi.android.rate.AppRate;


public class MainActivity extends Activity {
    private SoundPool soundPool;
    private ArrayList<Integer> soundIds;
    private float volume;
    private InterstitialAd interstitial;
    public static GoogleAnalytics analytics;
    private AdView adView;
    private boolean adsEnabled = true;

    public static final Integer[] rawSoundReferences = {
            R.raw.badum, R.raw.bazinga,
            R.raw.bye, R.raw.challengeaccepted,
            R.raw.dramatic, R.raw.fart,
            R.raw.fuckoff, R.raw.fusrodah,
            R.raw.gay, R.raw.headshot,
            R.raw.heya, R.raw.hodor,
            R.raw.idontwant, R.raw.killme,
            R.raw.nooo, R.raw.nope,
            R.raw.nyancat, R.raw.petergriffin,
            R.raw.police, R.raw.saxguy,
            R.raw.shit, R.raw.silenceikillyou, R.raw.syrena, R.raw.takemymoney, R.raw.thatswhatshesaid,
            R.raw.thisissparta, R.raw.trololo, R.raw.whatthefuck, R.raw.whatwhatwhat,
            R.raw.youshallnotpass
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (adsEnabled) {
            showInterstitial();
            adView = (AdView) findViewById(R.id.adViewMainScreen);
            AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("38A89288B11D977328AD10DEE314DB12").addTestDevice("0457F45F2F3B38D51216287AD98A2C3D").addTestDevice("3AC2DCEE575018317C028D0C93F19AD0").addTestDevice("2D7D6AE8606296EB97A2A9B3681B90F6").build();
            adView.loadAd(adRequest);
        }

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        setupGoogleAnalytics();
        maybeShowAppRate();

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                soundPool.play(soundIds.get(position), volume, volume, 1, 0, 1f);
            }
        });

        soundIds = new ArrayList<>();

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        loadSoundPool();
    }

    private void setupGoogleAnalytics() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);
        Tracker t = analytics.newTracker(getResources().getString(R.string.googleAnalytics));
        t.enableExceptionReporting(true);
        t.enableAdvertisingIdCollection(true);
        t.enableAutoActivityTracking(true);
    }

    private void maybeShowAppRate() {
        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(2)
                .setRemindInterval(1)
                .setShowNeutralButton(true)
                .setDebug(false)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
    }

    private void loadSoundPool() {
        LoadSoundPoolTask lspt = new LoadSoundPoolTask(soundPool, soundIds, this);
        lspt.execute();
    }

    private void unloadSoundPool() {
        for (Integer soundId : soundIds) {
            soundPool.unload(soundId);
        }
    }

    private void showInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice("38A89288B11D977328AD10DEE314DB12").addTestDevice("0457F45F2F3B38D51216287AD98A2C3D").addTestDevice("3AC2DCEE575018317C028D0C93F19AD0").addTestDevice("2D7D6AE8606296EB97A2A9B3681B90F6").build();

        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-5829945009169600/5956253562");
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                interstitial.show();
            }
        });

        interstitial.loadAd(adRequest);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (adsEnabled) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adsEnabled) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (adsEnabled) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
