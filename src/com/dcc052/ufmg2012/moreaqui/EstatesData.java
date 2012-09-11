package com.dcc052.ufmg2012.moreaqui;

import static android.provider.BaseColumns._ID;
import static com.dcc052.ufmg2012.moreaqui.Constants.TABLE_NAME; 
import static com.dcc052.ufmg2012.moreaqui.Constants.TYPE; 
import static com.dcc052.ufmg2012.moreaqui.Constants.SIZE;
import static com.dcc052.ufmg2012.moreaqui.Constants.PHONE;
import static com.dcc052.ufmg2012.moreaqui.Constants.STATUS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EstatesData extends SQLiteOpenHelper {
  private static final String DATABASE_NAME = "estates.db";
  private static final int DATABASE_VERSION = 1;

  /** Create a helper object for the Events database */
  public EstatesData(Context ctx) {
    super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
	String query = "CREATE TABLE " + TABLE_NAME + " (" + _ID
		    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TYPE
		    + " INTEGER,"+ SIZE + " INTEGER,"+ PHONE + " INTEGER," + STATUS + " INTEGER);";
    db.execSQL(query);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }
}
