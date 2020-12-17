package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity implements SensorEventListener {
    // experimental values for hi and lo magnitude limits
//    private final double NORTH_MOVE_min = -1.5;
//    private final double South_MOVE_min = 9;
//    private final double East_MOVE_min = 2;
//    private final double West_MOVE_min = -2;
//
//    private final double NORTH_MOVE_BACKWARD = 6.0;

    int[] gameSequence = new int[120];
    int arrayIndex = 0;

    TextView tvx, tvy, tvz, tvSteps;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    Button btred,btblue,btyellow,btgreen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        btblue = findViewById(R.id.btBlue2);
        btred = findViewById(R.id.btRed2);
        btgreen = findViewById(R.id.btGreen2);
        btyellow = findViewById(R.id.btYellow2);



        // passing in the array to game screen
        Bundle extras = getIntent().getExtras();
        int[] arrayB = extras.getIntArray("numbers");




        tvx = findViewById(R.id.tvX);
        tvy = findViewById(R.id.tvY);
        tvz = findViewById(R.id.tvZ);


        // we are going to use the sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }






    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * App running but not on screen - in the background
     */
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);    // turn off listener to save power
    }


    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        tvx.setText(String.valueOf(x));
        tvy.setText(String.valueOf(y));
        tvz.setText(String.valueOf(z));



    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}