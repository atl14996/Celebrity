package com.example.week6day3homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week6day3homework.ProviderContract.COLUMN_FAVORITE;
import static com.example.week6day3homework.ProviderContract.COLUMN_IMAGE;
import static com.example.week6day3homework.ProviderContract.COLUMN_NAME;
import static com.example.week6day3homework.ProviderContract.DATABASE_NAME;
import static com.example.week6day3homework.ProviderContract.DATABASE_VERSION;
import static com.example.week6day3homework.ProviderContract.TABLE_NAME;

public class CelebrityDBHelper extends SQLiteOpenHelper {
    public CelebrityDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private void createCelebrityTable(SQLiteDatabase db) {

        String createCelebTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_NAME + " TEXT, " +
                COLUMN_FAVORITE + " TEXT, " +
                COLUMN_IMAGE + " TEXT)";

        db.execSQL(createCelebTableQuery);

    }

    public long insertCeleb(Celebrity celebrity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, celebrity.getName());
        contentValues.put(COLUMN_IMAGE, celebrity.getPicture());
        contentValues.put(COLUMN_FAVORITE, "false");

        return db.insert(TABLE_NAME, null, contentValues);


    }

    public long updateFavorite(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String whereClause = "WHERE " + COLUMN_NAME + " = \"" + celebrity.getName() + "\"";

        contentValues.put(COLUMN_IMAGE, celebrity.getPicture());
        contentValues.put(COLUMN_NAME, celebrity.getName());
        contentValues.put(COLUMN_FAVORITE, "true");

        return database.update(TABLE_NAME, contentValues, whereClause, null);
    }

    public long updateCelebrity(Celebrity celebrity) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String whereClause = "WHERE " + COLUMN_NAME + " = \"" + celebrity.getName() + "\"";

        contentValues.put(COLUMN_IMAGE, celebrity.getPicture());
        contentValues.put(COLUMN_NAME, celebrity.getName());
        contentValues.put(COLUMN_FAVORITE, celebrity.getIsFavorite());

        return database.update(TABLE_NAME, contentValues, whereClause, null);
    }

    public long deleteCeleb(String celebrityName) {


        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, " WHERE " + COLUMN_NAME + " = \"" + celebrityName, null);
    }

    public ArrayList<Celebrity> retrieveAllCelebrities() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectionQuery = "SELECT * FROM " + TABLE_NAME;
        ArrayList<Celebrity> celebrityArrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String picture = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
                Boolean isFavorite = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return celebrityArrayList;
    }

    public Celebrity getSingleCelebrity(String celebrityName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectionQuery = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME + " \"" + celebrityName + "\"";
        Celebrity returnCeleb = null;

        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String picture = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE));
                Boolean isFavorite = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)));

                returnCeleb = new Celebrity(picture, name, isFavorite);
        }
        cursor.close();
        return returnCeleb;

    }
}
