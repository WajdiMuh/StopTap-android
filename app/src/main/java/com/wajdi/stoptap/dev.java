package com.wajdi.stoptap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class dev extends Activity {
    private Integer check = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
        final LButton back = findViewById(R.id.backdev);
        final ImageView devimage = findViewById(R.id.devimage);
        handler.post(colordev);
        Animation anim = new ScaleAnimation(1f,1.1f,1f,1.1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setInterpolator(this, android.R.anim.accelerate_decelerate_interpolator);
        anim.setDuration(1000);
        devimage.startAnimation(anim);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
            }
        });
    }
    private Runnable colordev = new Runnable() {
        @Override
        public void run() {
            final LTextView devname = findViewById(R.id.developer);
            if(check == 0){
                if(devname.getCurrentTextColor() == Color.BLACK){
                    devname.setTextColor(Color.rgb(0,74,230));
                }else{
                    devname.setTextColor(Color.BLACK);
                }
            }else{
                if(devname.getCurrentTextColor() == Color.WHITE){
                    devname.setTextColor(Color.rgb(0,74,230));
                }else{
                    devname.setTextColor(Color.WHITE);
                }
            }
            handler.postDelayed(colordev, 500);
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
    }
}
