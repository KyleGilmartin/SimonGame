package edu.itsligo.simongame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondScreen extends AppCompatActivity {
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Button btplay, fb;
    Button bRed, bBlue, bYellow, bGreen;

    ArrayList<Integer> gameSequence;
    int arrayIndex = 0;
    TextView level;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int seq=0;
    int sequenceCount = 4, n = 0;
    private Object mutex = new Object();

    Handler handler;

    Runnable runnable;

    CountDownTimer ct = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        public void onFinish() {
            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence.get(i)));
            Intent i = new Intent(SecondScreen.this, GameScreen.class);
            i.putIntegerArrayListExtra("numbers", gameSequence);
            startActivityForResult(i,1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            seq=0;
            gameSequence.clear();
            editor.putInt("Level",data.getIntExtra("level",1));
            editor.putInt("seqcount",data.getIntExtra("seq",4));
            editor.putInt("score",data.getIntExtra("score",0));
            editor.commit();
            sequenceCount=data.getIntExtra("seq",4);
            level.setText(String.valueOf(data.getIntExtra("level",1)));
            if (data.getStringExtra("Result").equals("S")){
                Toast.makeText(this, "Congratulations!!", Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable,1500);
            }
            else {
                AlertDialog.Builder builder=new AlertDialog.Builder(SecondScreen.this);
                builder.setTitle("Game Over");
                builder.setMessage("Your Score was "+data.getIntExtra("score",0));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        handler=new Handler();
        runnable =new Runnable() {
            @Override
            public void run() {
                if (seq<sequenceCount) {
                    oneButton();
                    handler.postDelayed(this, 1500);
                    seq++;
                }
                else {
                    handler.removeCallbacks(this);
                    Intent i = new Intent(SecondScreen.this, GameScreen.class);
                    i.putIntegerArrayListExtra("numbers", gameSequence);
                    startActivityForResult(i,1);
                }
            }
        };
        preferences = getSharedPreferences("score_ref", MODE_PRIVATE);
        editor = preferences.edit();
        gameSequence=new ArrayList<>();
        level=findViewById(R.id.textView4);
        level.setText(String.valueOf(preferences.getInt("Level",1)));
        btplay = findViewById(R.id.btReady);
        bRed = findViewById(R.id.btRed2);
        bBlue = findViewById(R.id.btBlue2);
        bGreen = findViewById(R.id.btGreen2);
        bYellow = findViewById(R.id.btYellow2);
    }



    public void doPlay(View view) {
        handler.postDelayed(runnable,1500);
    }

    private void oneButton() {
        n = getRandom(4);
        switch (n) {
            case 1:
                flashButton(bBlue);
                gameSequence.add(BLUE);
                break;
            case 2:
                flashButton(bRed);
                gameSequence.add(RED);
                break;
            case 3:
                flashButton(bYellow);
                gameSequence.add(YELLOW);
                break;
            case 4:
                flashButton(bGreen);
                gameSequence.add(GREEN);
                break;
            default:
                break;
        }   // end switch
    }

    //
    // return a number between 1 and maxValue
    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }

    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }


    public void doTest(View view) {
        Intent intent = new Intent(view.getContext(), GameScreen.class);
        startActivity(intent);
    }
}