package com.harrie.learninglanguageapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class LessonDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lessons.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_LESSONS = "lessons";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";

    public LessonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_LESSONS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_CONTENT + " TEXT)";
        db.execSQL(CREATE_TABLE);

        insertCommonWordLessons(db); // Use common words
    }

    private void insertCommonWordLessons(SQLiteDatabase db) {
        insertLesson(db, "📘 Basic Greetings", "Hello = Bonjour\nGoodbye = Au revoir\nPlease = S'il vous plaît\nThank you = Merci\nYes = Oui\nNo = Non");
        insertLesson(db, "📘 Common Questions", "How are you? = Comment ça va ?\nWhat is your name? = Comment tu t'appelles ?\nWhere are you from? = D'où viens-tu ?");
        insertLesson(db, "📘 Numbers", "One = Un\nTwo = Deux\nThree = Trois\nFour = Quatre\nFive = Cinq");
        insertLesson(db, "📘 Days of the Week", "Monday = Lundi\nTuesday = Mardi\nWednesday = Mercredi\nThursday = Jeudi\nFriday = Vendredi\nSaturday = Samedi\nSunday = Dimanche");
        insertLesson(db, "📘 Colors", "Red = Rouge\nBlue = Bleu\nGreen = Vert\nYellow = Jaune\nBlack = Noir\nWhite = Blanc");
        insertLesson(db, "📘 Everyday Words", "Food = Nourriture\nWater = Eau\nHouse = Maison\nSchool = École\nBook = Livre\nPhone = Téléphone");
    }

    private void insertLesson(SQLiteDatabase db, String title, String content) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CONTENT, content);
        db.insert(TABLE_LESSONS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSONS);
        onCreate(db);
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_LESSONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Lesson lesson = new Lesson(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
                );
                lessons.add(lesson);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lessons;
    }
}
