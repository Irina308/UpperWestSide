package com.irina.upperwestside;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ImageDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Image.db";


    public static final String IMAGE_TABLE = "images";

    public static final String IMAGE_COL_ID = "imageID";

    public static final String IMAGE_COL_DESCRITPION = "description";


    // SQL: CREATE TABLE image (_id INTEGER PRIMARY KEY, title TEXT, imdbId TEXT)
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + IMAGE_TABLE + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + IMAGE_COL_ID + " TEXT,"
            + IMAGE_COL_DESCRITPION + " TEXT)";

    //SQL: DROP TABLE IF EXISTS image
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + IMAGE_TABLE;

    public ImageDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
