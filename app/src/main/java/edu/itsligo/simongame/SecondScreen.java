package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class SecondScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);




    }

    public void doReady(View view) {
        Intent intent = new Intent(view.getContext(), GameScreen.class);
        startActivity(intent);
    }


}