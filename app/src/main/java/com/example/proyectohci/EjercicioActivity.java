package com.example.proyectohci;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class EjercicioActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    //private Sensor mGyroscope;
    private boolean workoutSetMode = false;
    private boolean workoutSetStart = false;
    private boolean workoutSetEnd = false;
    private int workoutEndCounter = 0;

    private float previousX = 0;
    private float previousY = 0;
    private float previousZ = 0;

    private int previousShiftX = 0;
    private int previousShiftY = 0;
    private int previousShiftZ = 0;

    private float sensibility = 3.0f;

    private ArrayList<Integer> workoutForcesX = new ArrayList<>();
    private ArrayList<Integer> workoutForcesY = new ArrayList<>();
    private ArrayList<Integer> workoutForcesZ = new ArrayList<>();

    private ArrayList<Integer> currentWorkoutForcesX = new ArrayList<>();
    private ArrayList<Integer> currentWorkoutForcesY = new ArrayList<>();
    private ArrayList<Integer> currentWorkoutForcesZ = new ArrayList<>();

    private long timeCooldown = 0;

    private TextView txtStatus;
    private int repetitions = 0;

    private boolean endWorkout1 = false;
    private boolean endWorkout2 = false;


    private MediaPlayer mpPling;
    private MediaPlayer mpWump;
    private MediaPlayer mpRobots;
    private MediaPlayer mpClapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        txtStatus = findViewById(R.id.textView4);

        mpPling = MediaPlayer.create(getApplicationContext(), R.raw.pling_1);
        mpWump = MediaPlayer.create(getApplicationContext(), R.raw.wump_1);
        mpRobots = MediaPlayer.create(getApplicationContext(), R.raw.robots_1);
        mpClapping = MediaPlayer.create(getApplicationContext(), R.raw.clapping_1);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String sensorName = sensorEvent.sensor.getName();
        //Log.i("Sensor",sensorName + ": X: " + sensorEvent.values[0] + "; Y: " + sensorEvent.values[1] + "; Z: " + sensorEvent.values[2] + ";");


        if(workoutSetMode) {

            if(previousX == 0){
                previousX = sensorEvent.values[0];
            }
            if(previousY == 0){
                previousY = sensorEvent.values[1];
            }
            if(previousZ == 0){
                previousZ = sensorEvent.values[2];
            }

            if (sensorEvent.values[0] < previousX - sensibility
                    || sensorEvent.values[0] > previousX + sensibility
                    || sensorEvent.values[1] < previousY - sensibility
                    || sensorEvent.values[1] > previousY + sensibility
                    || sensorEvent.values[2] < previousZ - sensibility
                    || sensorEvent.values[2] > previousZ + sensibility)
            {
                workoutSetStart = true;
                Log.i("WORKOUT", "STARTING WORKOUT");
                txtStatus.setText("Calibrando");
                timeCooldown = System.currentTimeMillis();
            }
        }


        boolean changesDetectedX = false;
        boolean changesDetectedY = false;
        boolean changesDetectedZ = false;
        if(workoutSetStart && !workoutSetEnd){

            if(previousShiftX == 1 && sensorEvent.values[0] > previousX){
                previousX = sensorEvent.values[0];
            }else if(previousShiftX == -1 && sensorEvent.values[0] < previousX) {
                previousX = sensorEvent.values[0];
            }

            if(sensorEvent.values[0] < previousX - sensibility){
                previousX = sensorEvent.values[0];
                workoutForcesX.add(-1);
                previousShiftX = -1;
                changesDetectedX = true;
            }else if (sensorEvent.values[0] > previousX + sensibility) {
                previousX = sensorEvent.values[0];
                workoutForcesX.add(1);
                previousShiftX = 1;
                changesDetectedX = true;
            }

            if(previousShiftY == 1 && sensorEvent.values[1] > previousY){
                previousY = sensorEvent.values[1];
            }else if(previousShiftY == -1 && sensorEvent.values[1] < previousY) {
                previousY = sensorEvent.values[1];
            }

            if(sensorEvent.values[1] < previousY - sensibility){
                previousY = sensorEvent.values[1];
                workoutForcesY.add(-1);
                previousShiftY = -1;
                changesDetectedY = true;
            }else if (sensorEvent.values[1] > previousY + sensibility) {
                previousY = sensorEvent.values[1];
                workoutForcesY.add(1);
                previousShiftY = 1;
                changesDetectedY = true;
            }

            if(previousShiftZ == 1 && sensorEvent.values[2] > previousZ){
                previousZ = sensorEvent.values[2];
            }else if(previousShiftZ == -1 && sensorEvent.values[2] < previousZ) {
                previousZ = sensorEvent.values[2];
            }

            if(sensorEvent.values[2] < previousZ - sensibility){
                previousZ = sensorEvent.values[2];
                workoutForcesZ.add(-1);
                previousShiftZ = -1;
                changesDetectedZ = true;
            }else if (sensorEvent.values[2] > previousZ + sensibility) {
                previousZ = sensorEvent.values[2];
                workoutForcesZ.add(1);
                previousShiftZ = 1;
                changesDetectedZ = true;
            }


            if(changesDetectedX || changesDetectedY || changesDetectedZ){
                timeCooldown = System.currentTimeMillis();
            }
            if(System.currentTimeMillis() - timeCooldown > 1000){
                workoutSetMode = false;
                workoutSetEnd = true;
                txtStatus.setText("Calibración completada\nPuede empezar su ejercicio");
                mpRobots.start();
                Log.i("RESULTS X", workoutForcesX.toString());
                Log.i("RESULTS Y", workoutForcesY.toString());
                Log.i("RESULTS Z", workoutForcesZ.toString());

                if(currentWorkoutForcesX.size()%2 != 0){
                    currentWorkoutForcesX.remove(currentWorkoutForcesX.size()-1);
                }
                if(currentWorkoutForcesY.size()%2 != 0){
                    currentWorkoutForcesY.remove(currentWorkoutForcesY.size()-1);
                }
                if(currentWorkoutForcesZ.size()%2 != 0){
                    currentWorkoutForcesZ.remove(currentWorkoutForcesZ.size()-1);
                }

                currentWorkoutForcesX = new ArrayList<>(workoutForcesX);
                currentWorkoutForcesY = new ArrayList<>(workoutForcesY);
                currentWorkoutForcesZ = new ArrayList<>(workoutForcesZ);

                workoutEndCounter = 0;
            }
        }

        if(workoutSetEnd && !endWorkout2){
            if(previousShiftX == 1 && sensorEvent.values[0] > previousX){
                previousX = sensorEvent.values[0];
            }else if(previousShiftX == -1 && sensorEvent.values[0] < previousX) {
                previousX = sensorEvent.values[0];
            }

            if(sensorEvent.values[0] < previousX - sensibility){

                if(!currentWorkoutForcesX.isEmpty() && currentWorkoutForcesX.get(0) == -1){
                    currentWorkoutForcesX.remove(0);
                }
                previousX = sensorEvent.values[0];
                previousShiftX = -1;
            }else if (sensorEvent.values[0] > previousX + sensibility) {
                if(!currentWorkoutForcesX.isEmpty() && currentWorkoutForcesX.get(0) == 1){
                    currentWorkoutForcesX.remove(0);
                }
                previousX = sensorEvent.values[0];
                previousShiftX = 1;
            }

            if(previousShiftY == 1 && sensorEvent.values[1] > previousY){
                previousY = sensorEvent.values[1];
            }else if(previousShiftY == -1 && sensorEvent.values[1] < previousY) {
                previousY = sensorEvent.values[1];
            }

            if(sensorEvent.values[1] < previousY - sensibility){
                if(!currentWorkoutForcesY.isEmpty() && currentWorkoutForcesY.get(0) == -1){
                    currentWorkoutForcesY.remove(0);
                }
                previousY = sensorEvent.values[1];
                previousShiftY = -1;
            }else if (sensorEvent.values[1] > previousY + sensibility) {
                if(!currentWorkoutForcesY.isEmpty() && currentWorkoutForcesY.get(0) == 1){
                    currentWorkoutForcesY.remove(0);
                }
                previousY = sensorEvent.values[1];
                previousShiftY = 1;
            }

            if(previousShiftZ == 1 && sensorEvent.values[2] > previousZ){
                previousZ = sensorEvent.values[2];
            }else if(previousShiftZ == -1 && sensorEvent.values[2] < previousZ) {
                previousZ = sensorEvent.values[2];
            }

            if(sensorEvent.values[2] < previousZ - sensibility){
                if(!currentWorkoutForcesZ.isEmpty() && currentWorkoutForcesZ.get(0) == -1){
                    currentWorkoutForcesZ.remove(0);
                }
                previousZ = sensorEvent.values[2];
                previousShiftZ = -1;
            }else if (sensorEvent.values[2] > previousZ + sensibility) {
                if(!currentWorkoutForcesZ.isEmpty() && currentWorkoutForcesZ.get(0) == 1){
                    currentWorkoutForcesZ.remove(0);
                }
                previousZ = sensorEvent.values[2];
                previousShiftZ = 1;
            }

            if((currentWorkoutForcesX.isEmpty() && !workoutForcesX.isEmpty()) || (currentWorkoutForcesY.isEmpty() && !workoutForcesY.isEmpty()) || (currentWorkoutForcesZ.isEmpty()) && !workoutForcesZ.isEmpty()){
                Log.i("REPETICIONES", "REPETICION COMPLETADA");

                mpPling.release();
                mpPling = MediaPlayer.create(getApplicationContext(), R.raw.pling_1);
                mpPling.start();
                repetitions++;
                endWorkout1 = false;
                txtStatus.setText("Repeticiones\n"+repetitions+"\n\n(Presione para finalizar)");
                currentWorkoutForcesX = new ArrayList<>(workoutForcesX);
                currentWorkoutForcesY = new ArrayList<>(workoutForcesY);
                currentWorkoutForcesZ = new ArrayList<>(workoutForcesZ);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if (!workoutSetStart) {
                workoutSetMode = true;
                txtStatus.setText("Realize una repeticion y luego mantenga quieto el celular");
                mpWump.start();
            } else if (workoutSetEnd) {
                if (!endWorkout1) {
                    txtStatus.setText("Toque otra vez la pantalla para finalizar");
                    endWorkout1 = true;
                    mpWump.release();
                    mpWump = MediaPlayer.create(getApplicationContext(), R.raw.beep_1);
                    mpWump.start();
                } else if(!endWorkout2){
                    txtStatus.setText("FELICIDADES!\nCompletaste " + repetitions + " repeticiones\n\n(Presione para volver al menu)");
                    mpClapping.start();
                    endWorkout2 = true;
                }else{
                    onBackPressed();
                }
            }
        }
        return false;
    }
}
