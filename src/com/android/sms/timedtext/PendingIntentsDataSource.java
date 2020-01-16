package com.android.sms.timedtext;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PendingIntentsDataSource {

	  private SQLiteDatabase database;
	  private PendingIntentSQLiteHelper dbHelper;
	  private String[] allColumns = { PendingIntentSQLiteHelper.COLUMN_ID,PendingIntentSQLiteHelper.COLUMN_HOUR, PendingIntentSQLiteHelper.COLUMN_MINUTES, PendingIntentSQLiteHelper.COLUMN_SECONDS, PendingIntentSQLiteHelper.COLUMN_YEAR, PendingIntentSQLiteHelper.COLUMN_MONTH, PendingIntentSQLiteHelper.COLUMN_DAY, PendingIntentSQLiteHelper.COLUMN_FREQUENCY, PendingIntentSQLiteHelper.COLUMN_NUMBERTOSEND, PendingIntentSQLiteHelper.COLUMN_RECEIVERNAME, PendingIntentSQLiteHelper.COLUMN_MESSAGE };
	  private int mYear;
		private int mMonth;
		private int mDay;
		
	    private int mCurrentYear;
	    private int mCurrentMonth;
	    private int mCurrentDay;
	    private int mCurrentHour;
	    private int mCurrentMinute;
	    private int mId;
	    Calendar c,d;
	  public PendingIntentsDataSource(Context context){
		  dbHelper = new PendingIntentSQLiteHelper(context, "pendingintents.db",null, 2);
	  }
	  
	  public void open() throws SQLException {
		    database = dbHelper.getWritableDatabase();
		  }
	  
	  public void close() {
		    dbHelper.close();
		  }
	  
	  public SMSSchedulerPendingIntent createPendingIntents(String tb_name,int id, int hour, int mins, int secs, int year, int month, int day, String frequency, String number, String name, String message){
		  ContentValues values = new ContentValues();
		  values.put(PendingIntentSQLiteHelper.COLUMN_ID, id);
		  values.put(PendingIntentSQLiteHelper.COLUMN_HOUR, hour);
		  values.put(PendingIntentSQLiteHelper.COLUMN_MINUTES, mins);
		  values.put(PendingIntentSQLiteHelper.COLUMN_SECONDS, secs);
		  values.put(PendingIntentSQLiteHelper.COLUMN_YEAR, year);
		  values.put(PendingIntentSQLiteHelper.COLUMN_MONTH, month);
		  values.put(PendingIntentSQLiteHelper.COLUMN_DAY, day);
		  values.put(PendingIntentSQLiteHelper.COLUMN_FREQUENCY, frequency);
		  values.put(PendingIntentSQLiteHelper.COLUMN_NUMBERTOSEND, number);
		  values.put(PendingIntentSQLiteHelper.COLUMN_RECEIVERNAME, name);
		  values.put(PendingIntentSQLiteHelper.COLUMN_MESSAGE, message);
		  
		  
		  database.insert(tb_name, null,
			        values);
		  
		  Cursor cursor = database.query(tb_name,
			        allColumns, PendingIntentSQLiteHelper.COLUMN_ID + " = " + id, null,
			        null, null, null);
		  
		  cursor.moveToFirst();
		  SMSSchedulerPendingIntent newPendingIntent = cursorToPendingIntent(cursor);
		  cursor.close();
		  return newPendingIntent;
	  }
	  
	  public List<SMSSchedulerPendingIntent> getAllPendingIntents(String tb_name) {
		  List<SMSSchedulerPendingIntent> pendingIntents = new ArrayList<SMSSchedulerPendingIntent>();
		  Cursor cursor = database.query(tb_name,
			        allColumns, null, null, null, null, null);
		  
		  cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	SMSSchedulerPendingIntent pendingIntent = cursorToPendingIntent(cursor);
		    	
		    	pendingIntents.add(pendingIntent);
		      cursor.moveToNext();
		    }
		    
		    cursor.close();
		    return pendingIntents;
	  }
	  
//	  public List<SMSSchedulerPendingIntent> getHistoryPendingIntents() {
//		    c = Calendar.getInstance();
//	        mCurrentYear = c.get(Calendar.YEAR);
//	        mCurrentMonth = c.get(Calendar.MONTH);
//	        mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
//	        mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
//	        mCurrentMinute = c.get(Calendar.MINUTE+1);
//	        d=Calendar.getInstance();
//	        int t=0;
////	        mId = (int) System.currentTimeMillis();
////	        System.out.println(mId);
////	        String sql="SELECT * From pendingintents where _id>'"+mId+"'";
//	        List<SMSSchedulerPendingIntent> pendingIntents = new ArrayList<SMSSchedulerPendingIntent>();
////	        Cursor cursor=database.rawQuery(sql, null);
//	        
//		  Cursor cursor = database.query(PendingIntentSQLiteHelper.TABLE_PENDINGINTENT,
//			        allColumns, null, null, null, null, null);
//		  System.out.println(PendingIntentSQLiteHelper.COLUMN_ID);
//		  System.out.println(cursor.getCount());
//		  
//	        cursor.moveToFirst();
//		    while (!cursor.isAfterLast()) {
//		    	SMSSchedulerPendingIntent pendingIntent = cursorToPendingIntent(cursor);
//		    	d.set(Calendar.HOUR_OF_DAY, pendingIntent.getHour());
//			    d.set(Calendar.MINUTE, pendingIntent.getMinutes());
//			    
//			    
//			    d.set(pendingIntent.getYear(), pendingIntent.getMonth(), pendingIntent.getDay());
//		    	
//			    //System.out.println(pendingIntent.getId());
//		    	if(c.after(d)||c.equals(d)){
//			    pendingIntents.add(pendingIntent);
//			    //t++;
//			    }
//		      cursor.moveToNext();
//		      
//		    }
//		    System.out.println("t"+t);
//		    cursor.close();
//		    return pendingIntents;
//	  }
//	  
//	  public List<SMSSchedulerPendingIntent> getScheduledPendingIntents() {
//		    c = Calendar.getInstance();
//	        mCurrentYear = c.get(Calendar.YEAR);
//	        mCurrentMonth = c.get(Calendar.MONTH);
//	        mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
//	        mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
//	        mCurrentMinute = c.get(Calendar.MINUTE+3);
//	        d=Calendar.getInstance();
//	        int t=0;
////	        mId = (int) System.currentTimeMillis();
////	        System.out.println(mId);
////	        String sql="SELECT * From pendingintents where _id<'"+mId+"'";
//	        List<SMSSchedulerPendingIntent> pendingIntents = new ArrayList<SMSSchedulerPendingIntent>();
////	        Cursor cursor=database.rawQuery(sql, null);
//	        
//		  Cursor cursor = database.query(PendingIntentSQLiteHelper.TABLE_PENDINGINTENT,
//			        allColumns, null, null, null, null, null);
//		  System.out.println(PendingIntentSQLiteHelper.COLUMN_ID);
//		  System.out.println(cursor.getCount());
//		  
//	        cursor.moveToFirst();
//		    while (!cursor.isAfterLast()) {
//		    	SMSSchedulerPendingIntent pendingIntent = cursorToPendingIntent(cursor);
//		    	d.set(Calendar.HOUR_OF_DAY, pendingIntent.getHour());
//			    d.set(Calendar.MINUTE, pendingIntent.getMinutes());
//			    
//			    
//			    d.set(pendingIntent.getYear(), pendingIntent.getMonth(), pendingIntent.getDay());
//		    	System.out.println(pendingIntent.getId());
//		    	if(c.before(d)){
//		    		
//		    	pendingIntents.add(pendingIntent);
//		    	t++;}
//		      cursor.moveToNext();
//		      
//		    }
//		    System.out.println("t"+t);
//		    cursor.close();
//		    return pendingIntents;
//	  }
	  private SMSSchedulerPendingIntent cursorToPendingIntent(Cursor cursor) {
		  SMSSchedulerPendingIntent pendingIntent = new SMSSchedulerPendingIntent();
		  pendingIntent.setId(cursor.getInt(0));
		  pendingIntent.setHour(cursor.getInt(1));
		  pendingIntent.setMinutes(cursor.getInt(2));
		  pendingIntent.setSeconds(cursor.getInt(3));
		  pendingIntent.setYear(cursor.getInt(4));
		  pendingIntent.setMonth(cursor.getInt(5));
		  pendingIntent.setDay(cursor.getInt(6));
		  pendingIntent.setFrequency(cursor.getString(7));
		  pendingIntent.setNumberToSend(cursor.getString(8));
		  pendingIntent.setReceiverName(cursor.getString(9));
		  pendingIntent.setMessage(cursor.getString(10));
		  
		  return pendingIntent;
		  }
	  
	  public void deletePendingIntent(String tb_name,int id) 
		{
		  System.out.println("hmm");
		      database.delete(tb_name, PendingIntentSQLiteHelper.COLUMN_ID + "=" + id, null);
		      System.out.println("hmm");
		      return;
		}
}
