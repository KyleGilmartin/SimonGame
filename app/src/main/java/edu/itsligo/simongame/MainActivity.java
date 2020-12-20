package edu.itsligo.simongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView topfive;
    private EditText userName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userNameEd);
        topfive = findViewById(R.id.lvLeaderBoard);
        DatabaseHandler db = new DatabaseHandler(this);


        sharedPreferences = getSharedPreferences("score_ref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int s = sharedPreferences.getInt("score", 0);
        //  Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();

        if (s != 0) {

            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            db.addHiScore(new HiScore(date,
                    sharedPreferences.getString("player_name", ""),
                    s));

            //Toast.makeText(this, "Now we can store in db " + s, Toast.LENGTH_SHORT).show();

        }

        editor.putString("player_name", "");
        editor.putInt("Level", 1);
        editor.putInt("seqcount", 4);
        editor.putInt("score", 0);
        editor.commit();


//         db.emptyHiScores();     // empty table if required
//
//         Inserting hi scores
//        Log.i("Insert: ", "Inserting ..");
//        db.addHiScore(new HiScore("20 OCT 2020", "Frodo", 0));
//        db.addHiScore(new HiScore("28 OCT 2020", "Dobby", 1));
//        db.addHiScore(new HiScore("20 NOV 2020", "DarthV", 3));
//        db.addHiScore(new HiScore("20 NOV 2020", "Bob", 2));
//        db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 1));
//        db.addHiScore(new HiScore("30 NOV 2020", "Joe", 0));
//        db.addHiScore(new HiScore("01 DEC 2020", "DarthV", 0));
//        db.addHiScore(new HiScore("02 DEC 2020", "Gandalf", 0));
//

        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HiScore> hiScores = db.getAllHiScores();


        for (HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");

//        HiScore singleScore = db.getHiScore(5);
//        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
//                singleScore.getScore());

        Log.i("divider", "====================");

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }
        Log.i("divider", "====================");

//        HiScore hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        ///    Log.i("fifth Highest score: ", String.valueOf(hiScore.getScore()));

        // simple test to add a hi score
        int myCurrentScore = 40;
        // if 5th highest score < myCurrentScore, then insert new score
//        if (hiScore.getScore() < myCurrentScore) {
//            db.addHiScore(new HiScore("08 DEC 2020", "Elrond", 11));
//        }

        Log.i("divider", "====================");

        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();
        List<String> scoresStr;
        scoresStr = new ArrayList<>();

        int j = 1;
        for (HiScore hs : top5HiScores) {

            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // store score in string array
            scoresStr.add(j++ + " : " +
                    hs.getPlayer_name() + "\t" +
                    hs.getScore());
            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        Log.i("divider", "Scores in list <>>");
        for (String ss : scoresStr) {
            Log.i("Score: ", ss);
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresStr);
        topfive.setAdapter(itemsAdapter);

    }

    public void doStart(View view) {


        if (!userName.getText().toString().trim().isEmpty()) {

            editor.putString("player_name", userName.getText().toString().trim());
            editor.commit();
            //  sharedPreferences.edit().apply();
            // sharedPreferences.edit().commit();
            Intent intent = new Intent(view.getContext(), SecondScreen.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "User Name is required", Toast.LENGTH_SHORT).show();
        }
    }


    // add name
    public void onClick(View view) {
    }


}