package com.devhome.eduardobastos.roouber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

//Classes do Intro
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

//import com.devhome.eduardobastos.roouber.MainActivity;

public class SplashScreenActivity extends IntroActivity {

    private Button buttonEntrar;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);

        buttonEntrar = findViewById(R.id.buttonEntrar);


        //Configurando Interstitial_1

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8069899346555474/6325015173");
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestInter);



        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setButtonBackVisible(false);
        setButtonNextVisible(false);
        setButtonCtaVisible(true);

        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_TEXT);


        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_entrar)
                .canGoForward(false)
                .build());



        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        }, 2000);*/


    }



    public void btEntrar(View view) {

            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();



        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");

        }
    }
    /*public void openMain() {

        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }*/
}