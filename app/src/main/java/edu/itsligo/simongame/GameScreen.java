package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity implements SensorEventListener {

    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    TextView tvx, tvy, tvz, tvSteps;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    Button btred,btblue,btyellow,btgreen;
    int clicked=0;
    ArrayList<Integer> clickedSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        btblue = findViewById(R.id.btBlue2);
        btred = findViewById(R.id.btRed2);
        btgreen = findViewById(R.id.btGreen2);
        preferences=getSharedPreferences("score_ref", MODE_PRIVATE);
        editor=preferences.edit();
        btyellow = findViewById(R.id.btYellow2);
        clickedSequence=new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        ArrayList<Integer> gameSequence = extras.getIntegerArrayList("numbers");
        for (int value : gameSequence) {
            Log.e("array", String.valueOf(value));
        }
        tvx = findViewById(R.id.tvX);
        tvy = findViewById(R.id.tvY);
        tvz = findViewById(R.id.tvZ);
        btblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSequence.add(BLUE);
                clicked++;
                if (clicked>=gameSequence.size()){
                    if (clickedSequence.equals(gameSequence)){
                        Toast.makeText(getApplicationContext(),"Matched",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.putExtra("Result","S");
                        intent.putExtra("seq",gameSequence.size()+2);
                        int l=preferences.getInt("Level",1);
                        l++;
                        int s=preferences.getInt("score",0);
                        s+=gameSequence.size();
                        intent.putExtra("score",s);
                        intent.putExtra("level",l);
                        setResult(Activity.RESULT_OK,intent);
                    }
                    else {
                        Toast.makeText(GameScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GameScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        int s=preferences.getInt("score",0);
                        intent.putExtra("score",s);
                        intent.putExtra("Result","L");
                        startActivity(intent);
                        // setResult(Activity.RESULT_OK,intent);
                    }
                    finish();
                    clicked=0;
                    clickedSequence.clear();
                }
            }
        });
        btgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSequence.add(GREEN);
                clicked++;
                if (clicked>=gameSequence.size()){
                    if (clickedSequence.equals(gameSequence)){
                        Toast.makeText(getApplicationContext(),"Matched",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.putExtra("Result","S");
                        intent.putExtra("seq",gameSequence.size()+2);
                        int l=preferences.getInt("Level",1);
                        l++;
                        int s=preferences.getInt("score",0);
                        s+=gameSequence.size();
                        intent.putExtra("score",s);
                        intent.putExtra("level",l);
                        setResult(Activity.RESULT_OK,intent);
                    }
                    else {
                        Toast.makeText(GameScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GameScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        int s=preferences.getInt("score",0);
                        intent.putExtra("score",s);
                        intent.putExtra("Result","L");
                        // startActivity(intent);
                        setResult(Activity.RESULT_OK,intent);
                    }
                    finish();
                    clicked=0;
                    clickedSequence.clear();
                }
            }
        });
        btred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSequence.add(RED);
                clicked++;
                if (clicked>=gameSequence.size()){
                    if (clickedSequence.equals(gameSequence)){
                        Toast.makeText(getApplicationContext(),"Matched",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.putExtra("Result","S");
                        intent.putExtra("seq",gameSequence.size()+2);
                        int l=preferences.getInt("Level",1);
                        l++;
                        int s=preferences.getInt("score",0);
                        s+=gameSequence.size();
                        intent.putExtra("score",s);
                        intent.putExtra("level",l);
                        setResult(Activity.RESULT_OK,intent);
                    }
                    else {
                        Toast.makeText(GameScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GameScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        int s=preferences.getInt("score",0);
                        intent.putExtra("score",s);
                        intent.putExtra("Result","L");
                        setResult(Activity.RESULT_OK,intent);
                    }
                    finish();

                    clicked=0;
                    clickedSequence.clear();
                }
            }
        });
        btyellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSequence.add(YELLOW);
                clicked++;
                if (clicked>=gameSequence.size()){

                    if (clickedSequence.equals(gameSequence)){
                        Toast.makeText(getApplicationContext(),"Matched",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent();
                        intent.putExtra("Result","S");
                        intent.putExtra("seq",gameSequence.size()+2);
                        int l=preferences.getInt("Level",1);
                        l++;
                        int s=preferences.getInt("score",0);
                        s+=gameSequence.size();
                        intent.putExtra("score",s);
                        intent.putExtra("level",l);
                        setResult(Activity.RESULT_OK,intent);
                    }
                    else {
                        Toast.makeText(GameScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(GameScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        int s=preferences.getInt("score",0);
                        intent.putExtra("score",s);
                        intent.putExtra("Result","L");
                        intent.putExtra("player_name",preferences.getString("player_name",""));
                        setResult(Activity.RESULT_OK,intent);
                    }
                    finish();

                    clicked=0;
                    clickedSequence.clear();
                }
            }
        });
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }





    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);    // turn off listener to save power
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        tvx.setText(String.valueOf(x));
        tvy.setText(String.valueOf(y));
        tvz.setText(String.valueOf(z));



    }

    @Override
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