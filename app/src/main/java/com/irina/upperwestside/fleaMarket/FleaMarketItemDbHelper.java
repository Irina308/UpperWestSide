package com.irina.upperwestside.fleaMarket;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class FleaMarketItemDbHelper extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "Image.db";

    static final String IMAGE_TABLE = "images";

    static final String IMAGE_COL_IMAGE_ID = "imageID";

    static final String IMAGE_COL_DESCRITPION = "description";

    static final String IMAGE_COL_TITLE = "title";

    static final String IMAGE_COL_PRICE = "price";

    // SQL: CREATE TABLE image
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + IMAGE_TABLE + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + IMAGE_COL_TITLE + " TEXT,"
            + IMAGE_COL_IMAGE_ID + " TEXT,"
            + IMAGE_COL_PRICE + " DECIMAL(6,2),"
            + IMAGE_COL_DESCRITPION + " TEXT)";

    //SQL: DROP TABLE IF EXISTS image
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + IMAGE_TABLE;

    FleaMarketItemDbHelper(Context context) {
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
