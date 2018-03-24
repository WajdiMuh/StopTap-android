package com.wajdi.stoptap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final ImageView langb = findViewById(R.id.lang);
        final LButton back = findViewById(R.id.backsett);
        final SeekBar musbar = findViewById(R.id.musbar);
        final LTextView musval = findViewById(R.id.musval);
        final SeekBar sfxbar = findViewById(R.id.sfxbar);
        final LTextView sfxval = findViewById(R.id.sfxval);
        final ImageView vib = findViewById(R.id.vib);
        final SecurePreferences preferences = new SecurePreferences(this, "my-preferences", "Ww654321", true);
        musbar.setProgress(10 * Integer.valueOf(preferences.getString("musicval")));
        sfxbar.setProgress(10 * Integer.valueOf(preferences.getString("sfxval")));
        musval.setText(preferences.getString("musicval"));
        sfxval.setText(preferences.getString("sfxval"));
        String vs = Context.VIBRATOR_SERVICE;
        Vibrator mVibrator = (Vibrator)getSystemService(vs);
        boolean isVibrator = mVibrator.hasVibrator();
        if(isVibrator == true) {
            if (preferences.getString("vib").equals("0")) {
                vib.setColorFilter(null);
            } else {
                vib.setColorFilter(Color.rgb(255, 0, 0));
            }
            vib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (preferences.getString("vib").equals("0")) {
                        vib.setColorFilter(Color.rgb(255, 0, 0));
                        preferences.put("vib", "1");
                    } else {
                        vib.setColorFilter(null);
                        preferences.put("vib", "0");
                    }
                }
            });
        }else{
            vib.setVisibility(View.INVISIBLE);
        }
        langb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, Language.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);
            }
        });
        musbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                musval.setText(String.valueOf(progress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sfxbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sfxval.setText(String.valueOf(progress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        final SecurePreferences preferences = new SecurePreferences(this, "my-preferences", "Ww654321", true);
        final LTextView musval = findViewById(R.id.musval);
        final LTextView sfxval = findViewById(R.id.sfxval);
        preferences.put("musicval",musval.getText().toString());
        preferences.put("sfxval",sfxval.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
