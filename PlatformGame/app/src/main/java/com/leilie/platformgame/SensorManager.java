package com.leilie.platformgame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorManager implements SensorEventListener {
    private android.hardware.SensorManager sensorManager;
    private Sensor gyroscope;
    private float tiltValue = 0;
    private SensorListener listener;

    public interface SensorListener {
        void onTiltChanged(float tilt);
    }

    public SensorManager(Context context, SensorListener listener) {
        this.listener = listener;
        sensorManager = (android.hardware.SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public void start() {
        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, android.hardware.SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            tiltValue = -event.values[0] * 5;
            if (listener != null) {
                listener.onTiltChanged(tiltValue);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public float getTiltValue() {
        return tiltValue;
    }
}
