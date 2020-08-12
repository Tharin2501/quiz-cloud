package no.kristiania.quizcloud_exam.game;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import no.kristiania.quizcloud_exam.MainActivity;
import no.kristiania.quizcloud_exam.R;

public class Result extends AppCompatActivity {

    private SensorManager sm;
    private float acelVal; // Current acceleration value & gravity
    private float acelLast; //Last acceleration value and gravity
    private float shake;    // acceleration value differ from gravity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(sensorEventListener, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;

    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = acelVal - acelLast;
            shake = shake * 0.9f + delta;

            if (shake > 3) {

                Intent it = new Intent("intent.my.action");
                it.setComponent(new ComponentName(getApplicationContext().getPackageName(), MainActivity.class.getName()));
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().getApplicationContext().startActivity(it);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
