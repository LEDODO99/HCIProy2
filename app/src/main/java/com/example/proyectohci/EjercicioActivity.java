package com.example.proyectohci;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.hardware.Sensor.TYPE_GYROSCOPE;

public class EjercicioActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroscope;
    private boolean workoutSet = false;

    private float previousX = 0;
    private float previousY = 0;
    private float previousZ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String sensorName = sensorEvent.sensor.getName();
        Log.wtf("Sensor",sensorName + ": X: " + sensorEvent.values[0] + "; Y: " + sensorEvent.values[1] + "; Z: " + sensorEvent.values[2] + ";");
        if(!workoutSet){
            if(sensorEvent.values[0] > previousX + 0.5f || sensorEvent.values[0] < previousX - 0.5f){
                Log.wtf("SENSOR", "CAMBIO EN X");
                previousX = sensorEvent.values[0];
            }
            if(sensorEvent.values[1] > previousY + 0.5f || sensorEvent.values[1] < previousY - 0.5f){
                Log.wtf("SENSOR", "CAMBIO EN Y");
                previousY = sensorEvent.values[1];
            }
            if(sensorEvent.values[2] > previousZ + 0.5f || sensorEvent.values[2] < previousZ - 0.5f){
                Log.wtf("SENSOR", "CAMBIO EN Z");
                previousZ = sensorEvent.values[2];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
