package com.wajdi.stoptap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Al-Muhtadi on 12/2/2018.
 */

public class LButton extends Button {

    public LButton(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/stoptapen.ttf");
        this.setTypeface(face);
    }

    public LButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/stoptapen.ttf");
        this.setTypeface(face);
    }

    public LButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/stoptapen.ttf");
        this.setTypeface(face);
    }
}