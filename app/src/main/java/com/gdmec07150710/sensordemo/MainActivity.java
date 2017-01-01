package com.gdmec07150710.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensomanager;
    private Sensor mAcceterometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAcceterometer;
    private TextView Orientation;
    private TextView tLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAcceterometer=(TextView) this.findViewById(R.id.acceterometer);
        Orientation=(TextView) this.findViewById(R.id.orientation);
        tLight=(TextView) this.findViewById(R.id.light);
        mSensomanager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mAcceterometer=mSensomanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation=mSensomanager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight=mSensomanager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensomanager.registerListener(this,mAcceterometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensomanager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensomanager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensomanager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x=event.values[SensorManager.DATA_X];
        float y=event.values[SensorManager.DATA_Y];
        float z=event.values[SensorManager.DATA_Z];
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            tAcceterometer.setText("加速度:"+x+","+y+","+z);
        }else if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            tLight.setText("光线："+event.values[0]);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
