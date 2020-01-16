package com.android.sms.timedtext;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PendingIntentsArrayAdapter1 extends ArrayAdapter<SMSSchedulerPendingIntent> /*implements OnClickListener*/{
	private List<SMSSchedulerPendingIntent> mPendingIntentList1;
	private Context mContext;
	
	String newL = System.getProperty("line.separator");
	String messageAlertDialog;
	long mIdOfAnAlarm;
	private SentHistoryActivity mSentHistoryActivity;
	public PendingIntentsArrayAdapter1(Context context, int resource,
			 List<SMSSchedulerPendingIntent> objects) {
		super(context, R.layout.history,  objects);
		// TODO Auto-generated constructor stub
		mContext = context;
		mPendingIntentList1 = objects;
		mSentHistoryActivity = SentHistoryActivity.getSentHistoryActivity();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	    if (convertView == null) {
	        
	        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.history, parent, false);
	    }
	    
	    TextView to= (TextView)convertView.findViewById(R.id.textViewRecv1);
	    TextView tovalue= (TextView)convertView.findViewById(R.id.textViewRecvValue1);
	    
	    TextView msg= (TextView)convertView.findViewById(R.id.textViewMsg1);
	    TextView msgvalue= (TextView)convertView.findViewById(R.id.textViewMsgValue1);
	    
	    TextView hr = (TextView)convertView.findViewById(R.id.textViewHr1);
	    TextView hrValue = (TextView)convertView.findViewById(R.id.textViewHrValue1);
	    
	    TextView mins = (TextView)convertView.findViewById(R.id.textViewMins1);
	    TextView minsValue = (TextView)convertView.findViewById(R.id.textViewMinsValue1);
	    
	    TextView secs = (TextView)convertView.findViewById(R.id.textViewSecs1);
	    TextView secsValue = (TextView)convertView.findViewById(R.id.textViewSecsValue1);
	
	    TextView day = (TextView)convertView.findViewById(R.id.textViewDay1);
	    TextView dayValue = (TextView)convertView.findViewById(R.id.textViewDayValue1);
	    
	    TextView month = (TextView)convertView.findViewById(R.id.textViewMonth1);
	    TextView monthValue = (TextView)convertView.findViewById(R.id.textViewMonthValue1);
	    
	    TextView year = (TextView)convertView.findViewById(R.id.textViewYear1);
	    TextView yearValue = (TextView)convertView.findViewById(R.id.textViewYearValue1);
	    
	    
	    SMSSchedulerPendingIntent smsSchedulerPendingIntent = mPendingIntentList1.get(position);
	    String receiverNumber = smsSchedulerPendingIntent.getNumberToSend();
	    String receiverName = smsSchedulerPendingIntent.getReceiverName();
	    String message = smsSchedulerPendingIntent.getMessage();
	    String frequency = smsSchedulerPendingIntent.getFrequency();
	    
	    messageAlertDialog = "Receiver's Number: " + receiverNumber + newL + "Receiver's Name: " + receiverName + newL + "Message: " + message + newL + "Frequency: " + frequency;
	    mIdOfAnAlarm = smsSchedulerPendingIntent.getId();
	    if(position == 0){
	    	to.setText("To");
	    	msg.setText("Message");
	    	hr.setText("Hr");
		    mins.setText("Mins");
		    secs.setText("Secs");
		    day.setText("Day");
		    month.setText("Month");
		    year.setText("Year");
		    
	    }
	    
	    else{
	    	to.setText("");
	    	msg.setText("");
	    	hr.setText("");
		    mins.setText("");
		    secs.setText("");
		    day.setText("");
		    month.setText("");
		    year.setText("");
		    
	    }
	    
	    tovalue.setText(smsSchedulerPendingIntent.getReceiverName());
	    msgvalue.setText(smsSchedulerPendingIntent.getMessage());
	    hrValue.setText(Long.toString(smsSchedulerPendingIntent.getHour()));
	    minsValue.setText(Long.toString(smsSchedulerPendingIntent.getMinutes()));
	    secsValue.setText(Long.toString(smsSchedulerPendingIntent.getSeconds()));
	    
	    dayValue.setText(Integer.toString(smsSchedulerPendingIntent.getDay()));
	    
	    monthValue.setText(Integer.toString(smsSchedulerPendingIntent.getMonth()+1));
	    yearValue.setText(Integer.toString(smsSchedulerPendingIntent.getYear()));
	    
	    
	    return convertView;
	}
	
	public List<SMSSchedulerPendingIntent> getPendingIntentList(){
		return mPendingIntentList1;
	}

	

}
