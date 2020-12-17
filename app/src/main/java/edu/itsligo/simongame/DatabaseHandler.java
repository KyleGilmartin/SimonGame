package edu.itsligo.simongame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "project3game";
    private static final String TABLE_HI_SCORES = "hi_scores";
    private static final String KEY_SCORE_ID = "score_id";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_GAME_DATE = "game_date";
    private static final String KEY_SCORE = "score";

    /*
     * Constructor
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISCORES_TABLE = "CREATE TABLE " + TABLE_HI_SCORES + "(" +
                KEY_SCORE_ID + " INTEGER PRIMARY KEY," +
                KEY_GAME_DATE + " TEXT NOT NULL," +
                KEY_PLAYER_NAME + " TEXT NOT NULL," +
                KEY_SCORE + " INTEGER NOT NULL" +
                ")";
        db.execSQL(CREATE_HISCORES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HI_SCORES);

        // Create tables again
        onCreate(db);
    }

    /*
     * CRUD Helper methods
     */
    public void emptyHiScores() {
        // Drop older table if existed
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HI_SCORES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new hiScore
    void addHiScore(HiScore hiScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GAME_DATE, hiScore.getGame_date());
        values.put(KEY_PLAYER_NAME, hiScore.getPlayer_name());
        values.put(KEY_SCORE, hiScore.getScore());

        // Inserting Row
        db.insert(TABLE_HI_SCORES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single hiScore
    HiScore getHiScore(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HI_SCORES, new String[]{
                        KEY_SCORE_ID,
                        KEY_GAME_DATE,
                        KEY_PLAYER_NAME,
                        KEY_SCORE},
                KEY_SCORE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HiScore hiScore = new HiScore(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        // return hi score
        return hiScore;
    }

    // code to get all hiScores in a list view
    public List<HiScore> getAllHiScores() {
        List<HiScore> hiScoreList = new ArrayList<HiScore>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HI_SCORES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HiScore hiScore = new HiScore();
                hiScore.setScore_id(Integer.parseInt(cursor.getString(0)));
                hiScore.setGame_date(cursor.getString(1));
                hiScore.setPlayer_name(cursor.getString(2));
                hiScore.setScore(cursor.getInt(3));
                // Adding hi score to list
                hiScoreList.add(hiScore);
            } while (cursor.moveToNext());
        }

        // return hiScore list
        return hiScoreList;
    }

    // code to update the single hiScore
    public int updateHiScore(HiScore hiScore) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_NAME, hiScore.getPlayer_name());
        values.put(KEY_GAME_DATE, hiScore.getGame_date());
        values.put(KEY_SCORE, hiScore.getScore());

        // updating row
        return db.update(TABLE_HI_SCORES, values, KEY_SCORE_ID + " = ?",
                new String[]{String.valueOf(hiScore.getScore_id())});
    }

    // Deleting single hiScore
    public void deleteHiScore(HiScore hiScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HI_SCORES, KEY_SCORE_ID + " = ?",
                new String[]{String.valueOf(hiScore.getScore_id())});
        db.close();
    }

    // Getting top 5 scores
    public List<HiScore> getTopFiveScores() {
        List<HiScore> hiScoreList = new ArrayList<HiScore>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HI_SCORES +
                " ORDER BY SCORE DESC " +
                " LIMIT 5";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HiScore hiScore = new HiScore();
                hiScore.setScore_id(Integer.parseInt(cursor.getString(0)));
                hiScore.setGame_date(cursor.getString(1));
                hiScore.setPlayer_name(cursor.getString(2));
                hiScore.setScore(cursor.getInt(3));
                // Adding hi score to list
                hiScoreList.add(hiScore);
            } while (cursor.moveToNext());
        }

        // return hi score list
        return hiScoreList;
    }

}
