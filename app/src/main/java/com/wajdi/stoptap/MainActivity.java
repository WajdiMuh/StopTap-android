package com.wajdi.stoptap;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jawnnypoo.physicslayout.Physics;
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
        final PhysicsRelativeLayout physicslayout = findViewById(R.id.physics_layout);
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
        physicslayout.getPhysics().setOnCollisionListener(new Physics.OnCollisionListener() {
            @Override
            public void onCollisionEntered(int viewIdA, int viewIdB) {
                if(checkgravity == false){
                    checkgravity = true;
                    physicslayout.post(new Runnable() {
                        public void run() {
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
            public void onClick(View v) {
                checkgravity = false;
                physicslayout.getPhysics().disablePhysics();
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                physicslayout.getPhysics().setGravity(5f, 0f);
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
