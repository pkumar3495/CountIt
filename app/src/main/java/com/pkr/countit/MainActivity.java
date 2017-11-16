package com.pkr.countit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSIVITY = 1;
    public TextView proximityCountText;
    public TextView touchCountText;
    public int pCount = 0;
    public int tCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        proximityCountText = findViewById(R.id.proximityCountText);
        touchCountText = findViewById(R.id.touchCountText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            if (event.values[0] >= -SENSOR_SENSIVITY && event.values[0] <= SENSOR_SENSIVITY){
                pCount++;
                proximityCountText.setText(""+pCount);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void touchFunc (View v){
        tCount++;
        touchCountText.setText(""+tCount);
    }
    public void reset (View v){
        tCount = 0;
        pCount = 0;
        proximityCountText.setText(""+pCount);
        touchCountText.setText(""+tCount);
    }
}
