package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Comment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView topfive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();     // empty table if required

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HiScore("20 OCT 2020", "Frodo", 12));
        db.addHiScore(new HiScore("28 OCT 2020", "Dobby", 16));
        db.addHiScore(new HiScore("20 NOV 2020", "DarthV", 20));
        db.addHiScore(new HiScore("20 NOV 2020", "Bob", 18));
        db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 22));
        db.addHiScore(new HiScore("30 NOV 2020", "Joe", 30));
        db.addHiScore(new HiScore("01 DEC 2020", "DarthV", 22));
        db.addHiScore(new HiScore("02 DEC 2020", "Gandalf", 132));


        HiScore singleScore = db.getHiScore(5);

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();


        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);

        }



    }

    public void doStart(View view) {
        Intent intent = new Intent(view.getContext(), SecondScreen.class);
        startActivity(intent);
    }


    // add name
    public void onClick(View view) {
    }
}