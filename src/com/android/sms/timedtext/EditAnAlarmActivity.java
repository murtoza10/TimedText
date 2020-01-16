package com.android.sms.timedtext;

import java.security.PublicKey;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class EditAnAlarmActivity extends Activity implements View.OnClickListener{
	
	public String mNumber;
	EditText mEditTextNumber;
	EditText mEditTextNumber11;
	EditText mEditTextMessage;
	EditText mEditDate;
	EditText mEditTime;
	public String mContactdisplayName;
	private long mInterval;
	private String mMessage;
	public static final String tb_name = "pendingintents";
	private String mFrequency;
	ImageButton mButtonContact;
	Button mDatePickUp;
	Button mTimePickUp;
	Button mConfirm,mCancel;
	RadioButton mOneTime, mFifteenMinuets, mHalfHour, mHourR, mHalfDay, mDaily, mWeekly, mMonthly, mYearly; 
	private int mHour;
	private int mMinutes;
	private int mSeconds;
	
	private int mmHour;
	private int mmMinutes;
	private int mmSeconds;
	
	private String Date;
	private String Time;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	
	private int mmYear;
	private int mmMonth;
	private int mmDay;
	
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;
    private int mCurrentHour;
    private int mCurrentMinute;
    
    DatePickerDialog mDatePickerDialog;
    TimePickerDialog mTimePickerDialog;
    Calendar c;
    private static EditAnAlarmActivity mMainActivity;
    private SetuppageActivity mSetuppageActivity;
    public int dispnm=0;
    private int mId;
    
    private boolean isOneTime = false;
    private CancelAnAlarmActivity mCancelAnAlarmActivity;
    private PendingIntentsDataSource mDatasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editanalarm);
        mMainActivity = this;
        mButtonContact = (ImageButton)findViewById(R.id.buttonContact1);
        mEditTextNumber = (EditText)findViewById(R.id.editTextNumber1);
        mEditTextNumber11 = (EditText)findViewById(R.id.editTextNumber11);
        mEditDate = (EditText)findViewById(R.id.editDate1);
        mEditTime = (EditText)findViewById(R.id.editTime1);
        mConfirm = (Button)findViewById(R.id.buttonConfirm1);
        mCancel = (Button)findViewById(R.id.buttonCancel);
        mEditTextMessage = (EditText)findViewById(R.id.editTextMessage1);
        mOneTime = (RadioButton)findViewById(R.id.oneTime1);
        mOneTime.setOnClickListener(this);
        mFifteenMinuets = (RadioButton)findViewById(R.id.fifteenMinutes1);
        mFifteenMinuets.setOnClickListener(this);
        mHalfHour = (RadioButton)findViewById(R.id.halfHour1);
        mHalfHour.setOnClickListener(this);
        mHourR= (RadioButton)findViewById(R.id.hour1);
        mHourR.setOnClickListener(this);
        mHalfDay = (RadioButton)findViewById(R.id.halfDay1);
        mHalfDay.setOnClickListener(this);
        mDaily = (RadioButton)findViewById(R.id.daily1);
        mDaily.setOnClickListener(this);
        mWeekly = (RadioButton)findViewById(R.id.weekly1);
        mWeekly.setOnClickListener(this);
        mMonthly = (RadioButton)findViewById(R.id.monthly1);
        mMonthly.setOnClickListener(this);
        mYearly = (RadioButton)findViewById(R.id.Yearly1);
        mEditTextNumber.setText("");
        mEditTextMessage.setText("");
        
        mCancelAnAlarmActivity=CancelAnAlarmActivity.getCancelAlarmActivity();
        mEditTextNumber.setText(mCancelAnAlarmActivity.getReceiverName());
        mEditTextMessage.setText(mCancelAnAlarmActivity.getMessage());
        mEditTextNumber11.setText(mCancelAnAlarmActivity.getNumberToSend());
        mYear = mCancelAnAlarmActivity.getYear();
        mMonth = mCancelAnAlarmActivity.getMonth()+1;
        mDay = mCancelAnAlarmActivity.getDay();
        
        if(mDay<10){
        	Date="0"+mDay;
        }
        else{
        	Date=""+mDay;
        }
        if(mMonth<10){
        	Date+="-0"+mMonth;
        }
        else{
        	Date+="-"+mMonth;
        }
        if(mYear<10){
        	Date+="-0"+mYear;
        }
        else{
        	Date+="-"+mYear;
        }
        mEditDate.setText(Date);
        mHour = mCancelAnAlarmActivity.getHour();
        mMinutes = mCancelAnAlarmActivity.getMinutes();
        
        if(mHour<10){
        	Time="0"+mHour;
        }
        else{
        	Time=""+mHour;
        }
        if(mMinutes<10){
        	Time+=":0"+mMinutes;
        }
        else{
        	Time+=":"+mMinutes;
        }
        mEditTime.setText(Time);
        if(mCancelAnAlarmActivity.getFrequency().equals("Once")){
        	
        	mOneTime.setChecked(true);
        	isOneTime = true;
        	mFrequency = "Once";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("15 mins")){
        	mFifteenMinuets.setChecked(true);
        	mInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        	isOneTime = false;
        	mFrequency = "15 mins";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Half Hour")){
        	mHalfHour.setChecked(true);
        	mInterval = AlarmManager.INTERVAL_HALF_HOUR;
        	isOneTime = false;
        	mFrequency = "Half Hour";
        }

        else if(mCancelAnAlarmActivity.getFrequency().equals("Hour")){
        	mHourR.setChecked(true);
        	mInterval = AlarmManager.INTERVAL_HOUR;
        	isOneTime = false;
        	mFrequency = "Hour";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Half Day")){
        	mHalfDay.setChecked(true);
        	mInterval = AlarmManager.INTERVAL_HALF_DAY;
        	isOneTime = false;
        	mFrequency = "Half Day";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Daily")){
        	mDaily.setChecked(true);
        	mInterval = AlarmManager.INTERVAL_DAY;
        	isOneTime = false;
        	mFrequency = "Daily";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Week")){
        	mWeekly.setChecked(true);
        	mInterval = 7*24*60*60*1000;
        	isOneTime = false;
        	mFrequency = "Week";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Month")){
        	mMonthly.setChecked(true);
        	int month = c.get(Calendar.MONTH);
			if((month == Calendar.JANUARY) || (month == Calendar.MARCH) || (month == Calendar.MAY) || (month == Calendar.JULY)|| (month == Calendar.AUGUST) || (month == Calendar.OCTOBER)|| (month == Calendar.DECEMBER)){
				mInterval = 31*24*60*60*1000;
			}
			if((month == Calendar.APRIL) || (month == Calendar.JUNE) || (month == Calendar.SEPTEMBER) || (month == Calendar.NOVEMBER)){
				mInterval = 30*24*60*60*1000;
			}
			if(month == Calendar.FEBRUARY){
				int year = c.get(Calendar.YEAR);
				if ((year%4) == 0){
					mInterval = 29*24*60*60*1000;
				}
				if((year%4) != 0){
					mInterval = 28*24*60*60*1000;
				}
					
			}
        	isOneTime = false;
        	mFrequency = "Month";
        }
        else if(mCancelAnAlarmActivity.getFrequency().equals("Year")){
        	mYearly.setChecked(true);
        	mInterval = 365*24*60*60*1000;
        	isOneTime = false;
        	mFrequency = "Year";
        }
        
        PendingIntentSQLiteHelper.TABLE_PENDINGINTENT="pendingintents";
        mDatasource = new PendingIntentsDataSource(this);
        mDatasource.open();
        
        c = Calendar.getInstance();
        mCurrentYear = c.get(Calendar.YEAR);
        mCurrentMonth = c.get(Calendar.MONTH);
        mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
        mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
        mCurrentMinute = c.get(Calendar.MINUTE);
        mButtonContact.setOnClickListener( new OnClickListener() {
	    	public void onClick(View v) {
//	    		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//	            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
//	            startActivityForResult(intent, 1);
	    		Intent refresh = new Intent();
	    		refresh.putExtra("Activity", "Edit");
	    	    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.TabActivity1");
	    	    startActivity(refresh);
	        }
        });
        mDatePickerDialog = new DatePickerDialog(this, mDateSetListener, mCancelAnAlarmActivity.getYear(), mCancelAnAlarmActivity.getMonth(),
				mCancelAnAlarmActivity.getDay());
        mTimePickerDialog = new TimePickerDialog(this, mTimeSetListener, mCancelAnAlarmActivity.getHour(), mCancelAnAlarmActivity.getMinutes(), true);
        mEditDate.setOnTouchListener(new View.OnTouchListener() {
            

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				mDatePickerDialog.show();
				
				return false;
			}
        });
        
        mEditTime.setOnTouchListener(new View.OnTouchListener() {
            
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				mTimePickerDialog.show();
				return false;
			}
        });
        
        mCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent refresh = new Intent();
			    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.CancelAnAlarmActivity");
			    startActivity(refresh);
			}
			});
        mConfirm.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//System.out.println("hmm");
				mMessage = mEditTextMessage.getText().toString();
				mContactdisplayName=mEditTextNumber.getText().toString();
//			    mNumber=mEditTextNumber11.getText().toString();
				String s =mEditTextNumber.getText().toString() ;
			    Pattern p = Pattern.compile("[A-Za-z]");
			    Matcher m = p.matcher(s);
			    if (!m.find()){
			    	mNumber=mEditTextNumber.getText().toString();
			    }
//				if(dispnm==0){
//			    	mContactdisplayName=mEditTextNumber.getText().toString();
//			    	mNumber=mEditTextNumber.getText().toString();
//			    }
				//mNumber = mEditTextNumber.getText().toString();
//				mYear = mCancelAnAlarmActivity.getYear();
//		        mMonth = mCancelAnAlarmActivity.getMonth();
//		        mDay = mCancelAnAlarmActivity.getDay();
//		        mHour = mCancelAnAlarmActivity.getHour();
//		        mMinutes = mCancelAnAlarmActivity.getMinutes();
				//System.out.println("hmm");
				int f=0;
				if(mMessage.equals("")||mNumber.equals("")||Date.equals("")||Time.equals("")||mFrequency.equals("")){
					Toast.makeText(getApplicationContext(), "Fill out every content of the form", Toast.LENGTH_LONG).show();
					f=1;
				}
				if(f==0){
					//System.out.println("hmm");
			    c.set(Calendar.HOUR_OF_DAY, mHour);
			    c.set(Calendar.MINUTE, mMinutes);
			    c.set(Calendar.SECOND, 0);
			    
			    c.set(mYear, mMonth-1, mDay);
			    //System.out.println("hmm");
			    Intent i = new Intent(getApplicationContext(), SendSMSAlarmService.class);
			    i.putExtra("com.android.sms.timedtext.number",mNumber);
			    i.putExtra("com.android.sms.timedtext.message",mMessage );
			    i.putExtra("com.android.sms.timedtext.frequency", mFrequency);
			    i.putExtra("com.android.sms.timedtext.contactname",mContactdisplayName);
			    i.putExtra("com.android.sms.timedtext.hour", mHour);
			    i.putExtra("com.android.sms.timedtext.minutes", mMinutes);
			    i.putExtra("com.android.sms.timedtext.sec", mSeconds);
			    i.putExtra("com.android.sms.timedtext.month", mMonth);
			    i.putExtra("com.android.sms.timedtext.day", mDay);
			    i.putExtra("com.android.sms.timedtext.year", mYear);
			    //System.out.println("hmm");
			    mId = (int) System.currentTimeMillis();
			    //System.out.println("hmm");
			    i.putExtra("com.android.sms.timedtext.id", mId);
			    //System.out.println("hmm");
			    AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(getApplicationContext().ALARM_SERVICE);
			    PendingIntent pi = PendingIntent.getService(getApplicationContext(), mId, i, PendingIntent.FLAG_UPDATE_CURRENT);
			    //System.out.println("hmm");
			    if(isOneTime == false){
			    	am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), mInterval, pi);
			    	addToDatabase(true);			    
			    	}
			    if(isOneTime == true){
			    	am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pi);
			    	addToDatabase(false);
			    }			    
			    System.out.println("hmm");
			    Toast.makeText(getApplicationContext(), "Your SMS has been Edited...", Toast.LENGTH_LONG).show();

			    mCancelAnAlarmActivity.CancelAnAlarm(mCancelAnAlarmActivity.getId());
//			    Intent refresh = new Intent();
//			    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext. CancelAnAlarmActivity");
//		    	startActivity(refresh);
			
			}
			}});
    }


    
    @Override
    public void onPause(){
    	super.onPause();
    	mDatasource.close();
    	
    }
      @Override
    public void onResume(){
    	super.onResume();
    	ActiveActivity.active = this.getClass();
    	mDatasource.open();
    	 c = Calendar.getInstance();
         mCurrentYear = c.get(Calendar.YEAR);
         mCurrentMonth = c.get(Calendar.MONTH);
         mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
         mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
         mCurrentMinute = c.get(Calendar.MINUTE);
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	
    	mDatasource.open();
    	 c = Calendar.getInstance();
         mCurrentYear = c.get(Calendar.YEAR);
         mCurrentMonth = c.get(Calendar.MONTH);
         mCurrentDay = c.get(Calendar.DAY_OF_MONTH);
         mCurrentHour = c.get(Calendar.HOUR_OF_DAY);
         mCurrentMinute = c.get(Calendar.MINUTE);
    	
    }
    @Override
    public void onStop(){
    	super.onStop();
    	if(ActiveActivity.active == this.getClass())
    		   ActiveActivity.active = null;
    	mDatasource.close();
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	
    	mDatasource.close();
    }
    @Override
    public void onBackPressed(){
    	
    	mDatasource.close();
    	Intent refresh = new Intent();
	    refresh.setClassName("com.android.sms.timedtext", "com.android.sms.timedtext.CancelAnAlarmActivity");
	    startActivity(refresh);
	    
    }
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri uri = data.getData();

	        if (uri != null) {
	            Cursor c = null;
	            try {
	                c = getContentResolver().query(uri, new String[]{ 
	                            ContactsContract.CommonDataKinds.Phone.NUMBER,  
	                            ContactsContract.CommonDataKinds.Phone.TYPE,
	                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME},
	                        null, null, null);

	                if (c != null && c.moveToFirst()) {
	                    
	                    int type = c.getInt(1);
	                    mEditTextNumber.setText(c.getString(0));
	                    mContactdisplayName = c.getString(2);
	                }
	            } finally {
	                if (c != null) {
	                    c.close();
	                }
	            }
	        }
	    }
    }
    
 
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear+1;
                    mDay = dayOfMonth;
                    
                    if(mDay<10){
                    	Date="0"+mDay;
                    }
                    else{
                    	Date=""+mDay;
                    }
                    if(mMonth<10){
                    	Date+="-0"+mMonth;
                    }
                    else{
                    	Date+="-"+mMonth;
                    }
                    if(mYear<10){
                    	Date+="-0"+mYear;
                    }
                    else{
                    	Date+="-"+mYear;
                    }	
                    mEditDate.setText(Date);
                }
            };

     
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            	mHour = hourOfDay;
                mMinutes = minute;
                
                if(mHour<10){
                	Time="0"+mHour;
                }
                else{
                	Time=""+mHour;
                }
                if(mMinutes<10){
                	Time+=":0"+mMinutes;
                }
                else{
                	Time+=":"+mMinutes;
                }
                	
                mEditTime.setText(Time);
               
                
            }
        };
     
        public static EditAnAlarmActivity getMAinActivity(){
        	return mMainActivity;
        }
        
        public String getNumberToSend(){
        	return mNumber;
        }
        
        public String getSMSMessage(){
        	return mMessage;
        }
        
        public int getDay(){
        	return mDay;
        }
        
        public int getMonth(){
        	return mMonth;
        }
        public int getYear(){
        	return mYear;
        }
        
        public int getHour(){
        	return mHour;
        }
        
        public int getMinutes(){
        	return mMinutes;
        }
        
        public int getSeconds(){
        	return mSeconds;
        }
        
        public PendingIntentsDataSource getDataSource(){
        	
        	return mDatasource;
        }
        

		@Override
		public void onClick(View v) {
			
			// TODO Auto-generated method stub
			
			
			if(v.equals(mOneTime)){
				isOneTime = true;
				mFrequency = "Once";
			}
			if(v.equals(mFifteenMinuets)){
				
				mInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
				mFrequency = "15 mins";
				isOneTime = false;
				
			}
			if(v.equals(mHalfHour)){
				
				mInterval = AlarmManager.INTERVAL_HALF_HOUR;
				mFrequency = "Half Hour";
				
				isOneTime = false;
			}
			if(v.equals(mHourR)){
				
				mInterval = AlarmManager.INTERVAL_HOUR;
				mFrequency = "Hour";
				isOneTime = false;
			}
			if(v.equals(mHalfDay)){
					
					mInterval = AlarmManager.INTERVAL_HALF_DAY;
					mFrequency = "Half Day";
					isOneTime = false;
			}
			if(v.equals(mDaily)){
				
				mInterval = AlarmManager.INTERVAL_DAY;
				mFrequency = "Daily";
				isOneTime = false;
			}
			if(v.equals(mWeekly)){
				
				mInterval = 7*24*60*60*1000;
				mFrequency = "Week";
				isOneTime = false;
				
			}
			if(v.equals(mMonthly)){
				
				isOneTime = false;
				mFrequency = "Month";
				int month = c.get(Calendar.MONTH);
				if((month == Calendar.JANUARY) || (month == Calendar.MARCH) || (month == Calendar.MAY) || (month == Calendar.JULY)|| (month == Calendar.AUGUST) || (month == Calendar.OCTOBER)|| (month == Calendar.DECEMBER)){
					mInterval = 31*24*60*60*1000;
				}
				if((month == Calendar.APRIL) || (month == Calendar.JUNE) || (month == Calendar.SEPTEMBER) || (month == Calendar.NOVEMBER)){
					mInterval = 30*24*60*60*1000;
				}
				if(month == Calendar.FEBRUARY){
					int year = c.get(Calendar.YEAR);
					if ((year%4) == 0){
						mInterval = 29*24*60*60*1000;
					}
					if((year%4) != 0){
						mInterval = 28*24*60*60*1000;
					}
						
				}
				
			}
			if(v.equals(mYearly)){
				
				mInterval = 365*24*60*60*1000;
				mFrequency = "Year";
				isOneTime = false;
			}
			
		}
		
		private void addToDatabase(boolean isRepeated){
			if(isRepeated == true){
				
				SMSSchedulerPendingIntent newDatabeseEntry = mDatasource.createPendingIntents(tb_name,mId, mHour, mMinutes, mSeconds, mYear, mMonth-1, mDay, mFrequency, mNumber, mContactdisplayName, mMessage);
			}
			if(isRepeated == false){
				SMSSchedulerPendingIntent newDatabeseEntry = mDatasource.createPendingIntents(tb_name,mId, mHour, mMinutes, mSeconds, mYear, mMonth-1, mDay, mFrequency, mNumber, mContactdisplayName, mMessage);
			}
		}
		
		
}

