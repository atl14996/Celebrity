package com.example.week6day3homework;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.week6day3homework.ProviderContract.COLUMN_NAME;
import static com.example.week6day3homework.ProviderContract.TABLE_NAME;

public class ContentProviders extends ContentProvider {

    CelebrityDBHelper celebrityDBHelper;

public static final int CELEBRITY = 300;
public static final int CELEB_ID = 380;

public static final UriMatcher tUriMatcher = buildUriMatcher();

public static UriMatcher buildUriMatcher(){
    String content = ProviderContract.CONTENT_AUTHORITY;

    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    matcher.addURI(content, TABLE_NAME, CELEBRITY);
    matcher.addURI(content, TABLE_NAME + "/#", CELEB_ID);

    return matcher;

}

    @Override
    public boolean onCreate() {
        celebrityDBHelper = new CelebrityDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = celebrityDBHelper.getWritableDatabase() ;
        Cursor returnCursor;
        switch(tUriMatcher.match(uri)) {
            case CELEBRITY:
                returnCursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CELEB_ID:
                long id = ContentUris.parseId(uri);
                returnCursor = db.query(TABLE_NAME, projection, COLUMN_NAME + " =?",
                        new String[]{String.valueOf(id)}, null, null, sortOrder);
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (tUriMatcher.match(uri)) {
            case CELEBRITY:
                return ProviderContract.CelebrityEntry.CONTENT_TYPE;
            case CELEB_ID:
                return ProviderContract.CelebrityEntry.CONTENT_ITEM_TYPE;
                default:
                    throw new UnsupportedOperationException("Unknown uri" + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = celebrityDBHelper.getWritableDatabase();
        long id;
        Uri returnUri;

        switch (tUriMatcher.match(uri)) {
            case CELEBRITY:
            id = db.insert(TABLE_NAME, null, values);
            if (id > 0) {

                returnUri = ProviderContract.CelebrityEntry.buildCelebUri(id);

            } else {
                throw new UnsupportedOperationException("Unable to insert Row Into" + uri);


            }
            break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        } getContext().getContentResolver().notifyChange(uri, null);
return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = celebrityDBHelper.getWritableDatabase();
        int rowsDeleted;

        switch (tUriMatcher.match(uri)) {

            case CELEBRITY:
                rowsDeleted = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default: throw new UnsupportedOperationException("Unknown uri:" + uri);
        }

        if(selection == null || rowsDeleted != 0) {

            getContext().getContentResolver().notifyChange(uri, null);

        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = celebrityDBHelper.getWritableDatabase();
        int rows;

        switch (tUriMatcher.match(uri)) {
            case CELEBRITY:
                rows = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
                default:
                    throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        if(rows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);

        } return rows;
    }
}
