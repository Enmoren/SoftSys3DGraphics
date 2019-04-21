package com.dragon.compass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class Main extends Activity implements SensorEventListener {

    ImageView znzImage;
    float currentDegree = 0f;
    SensorManager mSensorManager;

    private Sensor accelerometer;//加速度传感器
    private Sensor magnetic;//地磁传感器

    private float[] accelerometerValues = new float[3];
    private float[] magneticFieldValues = new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        znzImage = (ImageView)findViewById(R.id.znzImage);
//        实例化管理者
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
//        Log.e("dragon",deviceSensors+"");
//        实例化加速度传感器
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        实例化地磁传感器
        magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        calculateOrientation();
    }
    @Override
    protected void onResume(){
//        注册监听事件
        mSensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,magnetic,SensorManager.SENSOR_DELAY_GAME);
        super.onResume();
    }
    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
    @Override
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

private float calculateOrientation(){
    float[] values = new float[3];
    float[] R = new float[9];
    SensorManager.getRotationMatrix(R, null, accelerometerValues,
            magneticFieldValues);
    SensorManager.getOrientation(R, values);
    values[0] = (float) Math.toDegrees(values[0]);
    return values[0];
}




    @Override
    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticFieldValues = event.values;
        }
        float degree = calculateOrientation();
        RotateAnimation ra = new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);

        ra.setDuration(200);
        znzImage.startAnimation(ra);
        currentDegree=-degree;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }

}
