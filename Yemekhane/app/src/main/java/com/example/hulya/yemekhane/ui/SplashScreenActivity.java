package com.example.hulya.yemekhane.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hulya.yemekhane.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIE_OUT = 1500;
    private GifImageView garfieldGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        navi();
        try {
            InputStream inputStream = getAssets().open("garfiled_fork_and_knife.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            garfieldGif.setBytes(bytes);
            garfieldGif.startAnimation();
        } catch (IOException ex) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, RecyclerViewActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIE_OUT);
    }

    public void navi() {
        garfieldGif = findViewById(R.id.garfield);
    }
}
