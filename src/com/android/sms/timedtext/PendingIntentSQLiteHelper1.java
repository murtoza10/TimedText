package com.android.sms.timedtext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PendingIntentSQLiteHelper1 extends SQLiteOpenHelper {

  public static String TABLE_PENDINGINTENT = "pendingintents1";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_HOUR = "hour";
  public static final String COLUMN_MINUTES = "minutes";
  public static final String COLUMN_SECONDS = "seconds";
  public static final String COLUMN_YEAR = "year";
  public static final String COLUMN_MONTH = "month";
  public static final String COLUMN_DAY = "day";
  public static final String COLUMN_FREQUENCY = "frequency";
  public static final String COLUMN_NUMBERTOSEND = "numbertosend";
  public static final String COLUMN_RECEIVERNAME = "receivername";
  public static final String COLUMN_MESSAGE = "message";
  
  
  private static final String DATABASE_NAME = "pendingintents.db";
  private static final int DATABASE_VERSION = 1;
  

 private static final String DATABASE_CREATE = "create table " + TABLE_PENDINGINTENT + "(" + COLUMN_ID + " integer primary key , " + COLUMN_HOUR +" integer, " + COLUMN_MINUTES + " integer, " + COLUMN_SECONDS + " integer, " + COLUMN_YEAR + " integer, " + COLUMN_MONTH + " integer, " + COLUMN_DAY + " integer, " + COLUMN_FREQUENCY + " String, " + COLUMN_NUMBERTOSEND + " String, " + COLUMN_RECEIVERNAME + " String, " + COLUMN_MESSAGE + " String " + ");";
	
	public PendingIntentSQLiteHelper1(Context context, String name,
			CursorFactory factory, int version) {
		super(context, "pendingintents.db", factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENDINGINTENT);
	    onCreate(db);
	}
	
	

}
