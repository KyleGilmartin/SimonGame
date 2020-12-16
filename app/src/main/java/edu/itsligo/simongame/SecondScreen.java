package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class SecondScreen extends AppCompatActivity {
    Button btplay,fb;
    Button bRed,bBlue,bYellow,bGreen;
    int sequenceCount = 4, n = 0;
    private Object mutex = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        btplay = findViewById(R.id.btReady);
        bRed = findViewById(R.id.btRight);
        bBlue = findViewById(R.id.btTop);
        bGreen = findViewById(R.id.btBottom);
        bYellow = findViewById(R.id.btLeft);

    }


    public void doPlay(View view) {


        for (int i = 0; i < sequenceCount; i++) {
            // Need to generate a random sequence
            // start at 4 values, increase by 2 every time
            n=0;



            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    n = getRandom(sequenceCount);

                    switch (n) {
                        case 1:
                            flashButton(bBlue);
                            break;
                        case 2:
                            flashButton(bRed);
                            break;
                        case 3:
                            flashButton(bYellow);
                            break;
                        case 4:
                            flashButton(bGreen);
                            break;
                        default:
                            break;
                    }   // end switch
                }
            }, 1000*i);
        }  // end loop




    }



    //
    // return a number between 1 and maxValue
    private int getRandom(int maxValue) {
        int rn = (int) ((Math.random() * maxValue) + 1);
        return (rn);
    }

    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
            }
        },600);
        fb.setPressed(false);
        fb.invalidate();
    }

    public void doTest(View view) {
        for (int i = 0; i < sequenceCount; i++) {
            int x = getRandom(sequenceCount);



            if (x == 1)
                flashButton(bBlue);
            else if (x == 2)
                flashButton(bRed);
            else if (x == 3)
                flashButton(bYellow);
            else if (x == 4)
                flashButton(bGreen);
        }

    }
}