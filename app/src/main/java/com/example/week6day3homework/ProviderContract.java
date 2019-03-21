package com.example.week6day3homework;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ProviderContract {

    public static final String CONTENT_AUTHORITY = "com.example.week6day3homework";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String DATABASE_NAME = "celeb_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "celebrity_table";

    public static final String COLUMN_NAME = "celebrity_name";
    public static final String COLUMN_IMAGE = "celebrity_image";
    public static final String COLUMN_FAVORITE = "celebrity_favorite";

    public static final class CelebrityEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + TABLE_NAME;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + TABLE_NAME;

        public static Uri buildCelebUri(long id) {

            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }
}
