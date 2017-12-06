package com.gg.matei.avnutrition;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

public class gg1 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    ImageView imageView;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gg1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(gg1.this, gg2.class);
                startActivity(login);
                finish();
            }
        },SPLASH_TIME_OUT);


imageView = (ImageView)findViewById(R.id.animation);
if (imageView == null) throw new AssertionError();
imageView.setBackgroundResource(R.drawable.background);

anim = (AnimationDrawable)imageView.getBackground();
anim.start();


    }
}
