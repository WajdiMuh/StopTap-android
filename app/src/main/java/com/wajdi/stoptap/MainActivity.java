package com.wajdi.stoptap;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;
import com.jawnnypoo.physicslayout.Physics;
import com.jawnnypoo.physicslayout.PhysicsConfig;
import com.jawnnypoo.physicslayout.PhysicsRelativeLayout;
import org.jbox2d.common.Vec2;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;;
    private Sensor mAccelerometer;
    private boolean sensorava = false;
    private boolean checkgravity = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LTextView gname = findViewById(R.id.gname);
        final LButton play = findViewById(R.id.play);
        final ImageView settbutton = findViewById(R.id.setting);
        final LButton htp = findViewById(R.id.htp);
        final PhysicsRelativeLayout physicslayout = findViewById(R.id.physics_layout);
        final ImageView bganim1 = findViewById(R.id.bganim1);
        final ImageView bganim2 = findViewById(R.id.bganim2);
        final SecurePreferences preferences = new SecurePreferences(this, "my-preferences", "Ww654321", true);
        final float d = MainActivity.this.getResources().getDisplayMetrics().density;
        if (!preferences.containsKey("nm")){
            preferences.put("nm","0");
        }
        if (!preferences.containsKey("lang")){
            preferences.put("lang","1");
        }
        if (!preferences.containsKey("musicval")){
            preferences.put("musicval","10");
        }
        if (!preferences.containsKey("sfxval")){
            preferences.put("sfxval","10");
        }
        if (!preferences.containsKey("vib")){
            String vs = Context.VIBRATOR_SERVICE;
            Vibrator mVibrator = (Vibrator)getSystemService(vs);
            boolean isVibrator = mVibrator.hasVibrator();
            if(isVibrator){
                preferences.put("vib","1");
            }else{
                preferences.put("vib","0");
            }
        }
        if (!preferences.containsKey("shopselect")){
            preferences.put("shopselect","1");
        }
        if (!preferences.containsKey("shop2")){
            preferences.put("shop2","0");
        }
        if (!preferences.containsKey("shop3")){
            preferences.put("shop3","0");
        }
        if (!preferences.containsKey("shop4")){
            preferences.put("shop4","0");
        }
        if (!preferences.containsKey("shop5")){
            preferences.put("shop5","0");
        }
        if (!preferences.containsKey("shop6")){
            preferences.put("shop6","0");
        }
        if (!preferences.containsKey("shop7")){
            preferences.put("shop7","0");
        }
        if (!preferences.containsKey("shop8")){
            preferences.put("shop8","0");
        }
        if (!preferences.containsKey("shop9")){
            preferences.put("shop9","0");
        }
        if (!preferences.containsKey("shop10")){
            preferences.put("shop10","0");
        }
        if (!preferences.containsKey("shop11")){
            preferences.put("shop11","0");
        }
        if (!preferences.containsKey("shop12")){
            preferences.put("shop12","0");
        }
        if (!preferences.containsKey("shop13")){
            preferences.put("shop13","0");
        }
        if (!preferences.containsKey("shop14")){
            preferences.put("shop14","1");
        }
        if (!preferences.containsKey("shop15")){
            preferences.put("shop15","0");
        }
        if (!preferences.containsKey("shop16")){
            preferences.put("shop16","0");
        }
        if (!preferences.containsKey("cpsc")){
            preferences.put("cpsc",String.valueOf(Color.argb(255,0,76,255)));
        }
        if (!preferences.containsKey("cpbc")){
            preferences.put("cpbc","0");
            preferences.put("cpsc",String.valueOf(Color.argb(255,255,0,0)));
        }
        if (!preferences.containsKey("cv")){
            preferences.put("cv","0");
        }
        System.out.println("on create");
        gname.post(new Runnable() {
            public void run() {
                gname.setY((play.getY() / 2));
                gname.setRotation(15);
                Keyframe firststop = Keyframe.ofFloat(0f, 1f);
                Keyframe secondstop = Keyframe.ofFloat(0.5f, 1.5f);
                Keyframe thirdstop = Keyframe.ofFloat(1f, 1f);
                PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", firststop, secondstop,thirdstop);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", firststop, secondstop,thirdstop);
                PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation",-15f);
                ObjectAnimator anim = ObjectAnimator.ofPropertyValuesHolder(gname, scaleX,
                        scaleY,rotation);
                anim.setDuration(500); // duration 5 seconds
                anim.setRepeatCount(ValueAnimator.INFINITE);
                anim.setRepeatMode(ValueAnimator.REVERSE);
                anim.start();
            }
        });
        physicslayout.getPhysics().setPositionIterations(0);
        physicslayout.getPhysics().setVelocityIterations(20);
        physicslayout.getPhysics().setOnCollisionListener(new Physics.OnCollisionListener() {
            @Override
            public void onCollisionEntered(int viewIdA, int viewIdB) {
                if(checkgravity == false){
                    checkgravity = true;
                    physicslayout.post(new Runnable() {
                        public void run() {
                            physicslayout.getPhysics().getWorld().setContinuousPhysics(false);
                            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                            if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
                                mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                                mSensorManager.registerListener(MainActivity.this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                                sensorava = true;
                            }
                        }
                    });
                }
            }

            @Override
            public void onCollisionExited(int viewIdA, int viewIdB) {

            }
        });
        settbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkgravity = false;
                physicslayout.getPhysics().disablePhysics();
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinintro,R.anim.fadeoutintro);

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                physicslayout.getPhysics().setGravity(5f, 0f);
            }
        });
        htp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final View circleView = new View(MainActivity.this);
             /*   circleView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            circleView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
                PhysicsConfig config = PhysicsConfig.create();
                config.shapeType = PhysicsConfig.SHAPE_TYPE_RECTANGLE;
                Physics.setPhysicsConfig(circleView, config);
                physicslayout.addView(circleView);*/
                physicslayout.removeView(bganim1);
                bganim1.getLayoutParams().width = 100;
                bganim1.getLayoutParams().height = 100;
                physicslayout.addView(bganim1);
                physicslayout.removeView(bganim2);
                bganim2.getLayoutParams().width = 100;
                bganim2.getLayoutParams().height = 100;
                physicslayout.addView(bganim2);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onstart");
        checkgravity = false;
        final PhysicsRelativeLayout physicslayout = findViewById(R.id.physics_layout);
        physicslayout.post(new Runnable() {
            public void run() {
                //on resume is better called
                physicslayout.getPhysics().findBodyById(R.id.bganim1).setTransform(new Vec2(1f,1f),0f);
                physicslayout.getPhysics().findBodyById(R.id.bganim1).setLinearVelocity(new Vec2(0f, 0f));
                physicslayout.getPhysics().findBodyById(R.id.bganim1).setLinearDamping(0.1f);
                physicslayout.getPhysics().findBodyById(R.id.bganim2).setTransform(new Vec2(physicslayout.getPhysics().pixelsToMeters(physicslayout.getWidth()) - 1,1f),0f);
                physicslayout.getPhysics().findBodyById(R.id.bganim2).setLinearVelocity(new Vec2(0f, 0f));
                physicslayout.getPhysics().findBodyById(R.id.bganim2).setLinearDamping(0.1f);
                physicslayout.getPhysics().setGravity(0f, Physics.EARTH_GRAVITY);
                physicslayout.getPhysics().enablePhysics();
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        System.out.println("test accuracy changed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume called");
        if(sensorava == true) {
            if(checkgravity){
              mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("on pause called");
        if(sensorava == true){
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            final PhysicsRelativeLayout physicslayout = findViewById(R.id.physics_layout);
            float x = event.values[0];
            float y = event.values[1];
            int rotation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
            if (rotation == Surface.ROTATION_90) {
                Float t = x;
                x = y;
                y = t;
                physicslayout.getPhysics().setGravity(x, y);
                System.out.println("rotate 90");
            } else if (rotation == Surface.ROTATION_270) {
                Float t = x;
                x = 0 - y;
                y = 0 - t;
                System.out.println("rotate 270");
                physicslayout.getPhysics().setGravity(x, y);
            }
    }


}
