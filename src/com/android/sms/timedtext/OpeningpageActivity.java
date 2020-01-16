package com.android.sms.timedtext;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

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


public class OpeningpageActivity extends Activity implements View.OnClickListener {
	
	Button mNew, mHistory, mScheduled;
	private static OpeningpageActivity mMainActivity1;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openingpage);
		mMainActivity1 = this;

		mNew = (Button) findViewById(R.id.new2);
		mHistory = (Button) findViewById(R.id.history);
		mScheduled = (Button) findViewById(R.id.scheduled);
		mNew.getBackground().setAlpha(240);
		mHistory.getBackground().setAlpha(240);
		mScheduled.getBackground().setAlpha(240);
		mNew.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {

				Intent refresh = new Intent();
				refresh.setClassName("com.android.sms.timedtext",
						"com.android.sms.timedtext.SetuppageActivity");
				startActivity(refresh);

			}
		});

		mHistory.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent refresh = new Intent();
				refresh.setClassName("com.android.sms.timedtext",
						"com.android.sms.timedtext.SentHistoryActivity");
				startActivity(refresh);

			}
		});

		mScheduled.setOnClickListener(new View.OnClickListener() {

			
			public void onClick(View v) {

				Intent refresh = new Intent();
				refresh.setClassName("com.android.sms.timedtext",
						"com.android.sms.timedtext.CancelAnAlarmActivity");
				startActivity(refresh);

			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		

	}

	@Override
	public void onResume() {
		super.onResume();
		ActiveActivity.active = this.getClass();
		System.out.println(ActiveActivity.active);
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onStop() {
		super.onStop();
		if(ActiveActivity.active == this.getClass())
			   ActiveActivity.active = null;

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onBackPressed() {
		super.onDestroy();
		finish();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		//intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);

	}

	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
