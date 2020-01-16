package com.android.sms.timedtext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Groups;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Display1 extends Activity implements OnItemClickListener,
		OnClickListener {

	List<String> name1 = new ArrayList<String>();
	List<String> name2 = new ArrayList<String>();
	List<String> phno1 = new ArrayList<String>();
	List<String> id1 = new ArrayList<String>();
	List<String> phno2 = new ArrayList<String>();
	List<String> id2 = new ArrayList<String>();
	List<String> grp1 = new ArrayList<String>();
	List<String> grpno1 = new ArrayList<String>();
	// List<ConatctData> contactList = new ArrayList<ConatctData>();
	HashMap<Integer, String> myMapname = new HashMap<Integer, String>();
	HashMap<Integer, String> myMapphno = new HashMap<Integer, String>();
	MyAdapter ma1;
	CheckBox cb;
	SparseBooleanArray mCheckStates;
	Button select;
	HashMap<String, Long> group1;
	HashMap<String, Long> group2;
	StringBuilder checkedcontacts1;
	StringBuilder checkedcontacts2;
	String value;
	private static Display1 mDisplay1;
	SetuppageActivity mSetuppageActivity;
	EditAnAlarmActivity mEditAnAlarmActivity;
	TabActivity1 mTabActivity;
	EditText inputSearch;
	private ArrayList<String> array_sort_name = new ArrayList<String>();
	private ArrayList<String> array_sort_id = new ArrayList<String>();
	private ArrayList<String> array_sort_phno = new ArrayList<String>();
	int textlength = 0;
//	private final ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display1);

		getAllContacts(this.getContentResolver());
		ListView lv = (ListView) findViewById(R.id.lv1);
		
		inputSearch = (EditText) findViewById(R.id.inputSearch);
		
		ma1 = new MyAdapter();
		lv.setAdapter(ma1);
		lv.setOnItemClickListener(this);
		lv.setDescendantFocusability(ListView.FOCUS_BEFORE_DESCENDANTS);
		//lv.setItemsCanFocus(false);
		lv.setTextFilterEnabled(true);

		// mTabActivity=TabActivity1.getTabActivity1();
		// if(mTabActivity.value2.equals("Markall")){
		// for(int t=0;t<name1.size();t++){
		// ma1.toggle(t);
		// //mDisplay1.mCheckStates.put(t, true);
		// // mDisplay1.cb.setTag(t);
		// // mDisplay1.cb.setChecked(mDisplay1.mCheckStates.get(t,true));
		// }
		// }
		// adding
		
		inputSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				textlength = inputSearch.getText().length();
				System.out.println(textlength);
				array_sort_name.clear();
				array_sort_phno.clear();
				array_sort_id.clear();
				for (int i = 0; i < name2.size(); i++) {
					if (textlength <= name2.get(i).length()) {
						if (inputSearch.getText()
								.toString()
								.equalsIgnoreCase(
										(String) name2.get(i).subSequence(0,
												textlength))) {
							System.out.println(textlength);
							array_sort_name.add(name2.get(i));
							array_sort_phno.add(phno2.get(i));
							array_sort_id.add(id2.get(i));
						}
					}
				}
				name1=array_sort_name;
				phno1=array_sort_phno;
				id1=array_sort_id;
				ma1.notifyDataSetChanged();
				inputSearch.requestFocus();
			}
		});

		select = (Button) findViewById(R.id.button1);
		select.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				StringBuilder checkedcontacts = new StringBuilder();
				checkedcontacts1 = new StringBuilder();
				checkedcontacts2 = new StringBuilder();
				mSetuppageActivity = SetuppageActivity.getMAinActivity();
				mEditAnAlarmActivity = EditAnAlarmActivity.getMAinActivity();
				int f = 0;
				for (int i = 0; i < name1.size(); i++)

				{
					if (ma1.mCheckStates.get(i) == true) {
						// checkedcontacts.append(name1.get(i).toString());
						// checkedcontacts.append("\n");
						if (f == 0) {
							checkedcontacts1.append(phno1.get(i).toString());
							checkedcontacts2.append(name1.get(i).toString());
							// mSetuppageActivity.mEditTextNumber.setText(phno1.get(i).toString());
							f++;
						} else {
							// mSetuppageActivity.mEditTextNumber.setText(";"+phno1.get(i).toString());
							checkedcontacts1.append(";"
									+ phno1.get(i).toString());
							checkedcontacts2.append(","
									+ name1.get(i).toString());

						}

					} else {

					}

				}
				// Bundle extras = getIntent().getExtras();
				// if (extras != null) {
				// value = extras.getString("Activity");
				// }
				mTabActivity = TabActivity1.getTabActivity1();

				if (mTabActivity.value1.equals("Setup")) {
					mSetuppageActivity.mEditTextNumber
							.setText(checkedcontacts2);
					mSetuppageActivity.mNumber = checkedcontacts1.toString();
					mSetuppageActivity.mEditTextNumber10
							.setText(checkedcontacts1);
					mSetuppageActivity.mContactdisplayName = checkedcontacts2
							.toString();
					mSetuppageActivity.dispnm = 1;

				} else if (mTabActivity.value1.equals("Edit")) {

					mEditAnAlarmActivity.mEditTextNumber
							.setText(checkedcontacts2);
					mEditAnAlarmActivity.mEditTextNumber11
							.setText(checkedcontacts1);
					mEditAnAlarmActivity.mContactdisplayName = checkedcontacts2
							.toString();
					mEditAnAlarmActivity.dispnm = 1;

				}

				// Toast.makeText(Display1.this, checkedcontacts,1000).show();
				finish();
				// Intent refresh = new Intent();
				//
				// refresh.setClassName("com.android.sms.timedtext",
				// "com.android.sms.timedtext.SetuppageActivity");
				//
				// startActivity(refresh);
			}
		});

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		ma1.toggle(arg2);
	}

	public void getAllContacts(ContentResolver cr) {

		Cursor phones = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		while (phones.moveToNext()) {
			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			String id = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
			name1.add(name);
			phno1.add(phoneNumber);
			id1.add(id);
		}
		name2=name1;
		phno2=phno1;
		id2=id1;
		phones.close();
		// Cursor groupCursor = getContentResolver().query(
		// ContactsContract.Groups.CONTENT_URI,
		// new String[]{
		// ContactsContract.Groups._ID,
		// ContactsContract.Groups.TITLE
		// }, null, null, null
		// );
		//
		// groupCursor.moveToFirst();
		// while (groupCursor.moveToNext()) //
		// {
		// name1.add(groupCursor.getString(1));
		// grp1.add(groupCursor.getString(0));
		// //groups.put(tempCur.getString(1), tempCur.getLong(0));
		// }
		// groupCursor.close();
	}

	public static Bitmap loadContactPhoto(ContentResolver cr, long id) {
		Uri uri = ContentUris.withAppendedId(
				ContactsContract.Contacts.CONTENT_URI, id);
		InputStream input = ContactsContract.Contacts
				.openContactPhotoInputStream(cr, uri);
		if (input == null) {
			return null;
		}
		return BitmapFactory.decodeStream(input);
	}

	public static Display1 getDisplay1() {
		return mDisplay1;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.display1, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.markall:
			mTabActivity = TabActivity1.getTabActivity1();

			if (mTabActivity.value1.equals("Setup")) {
				Intent refresh = new Intent();
				refresh.putExtra("Activity2", "Setup");
				refresh.setClassName("com.android.sms.timedtext",
						"com.android.sms.timedtext.TabActivity2");
				startActivity(refresh);
				finish();

			} else if (mTabActivity.value1.equals("Edit")) {

				Intent refresh = new Intent();
				refresh.putExtra("Activity2", "Edit");
				refresh.setClassName("com.android.sms.timedtext",
						"com.android.sms.timedtext.TabActivity2");
				startActivity(refresh);
				finish();
			}

			return true;

			// case R.id.groups:
			// Intent n = new Intent();
			// n.setClassName("com.android.sms.timedtext",
			// "com.android.sms.timedtext.Display2");
			// // n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// startActivity(n);
			// finish();
			// return true;

		}
		return false;
	}

	public void onBackPressed() {

		// Intent refresh = new Intent();
		// refresh.setClassName("com.android.sms.timedtext",
		// "com.android.sms.timedtext.SetuppageActivity");
		// startActivity(refresh);
		finish();

	}

	// public void getSampleContactList(String groupID) {
	//
	//
	// Uri groupURI = ContactsContract.Data.CONTENT_URI;
	// String[] projection = new String[] {
	// ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
	// ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID };
	//
	// Cursor c = getContentResolver().query(
	// groupURI,
	// projection,
	// ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID
	// + "=" + groupID, null, null);
	//
	// int i=0;
	// while (c.moveToNext()) {
	// String id = c
	// .getString(c
	// .getColumnIndex(ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID));
	// Cursor pCur = getContentResolver().query(
	// ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
	// ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
	// new String[] { id }, null);
	//
	// while (pCur.moveToNext()) {
	// ConatctData data = new ConatctData();
	// String name = pCur
	// .getString(pCur
	// .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
	//
	// String phno = pCur
	// .getString(pCur
	// .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	//
	// myMapname.put(i, name);
	// myMapphno.put(i, phno);
	// //contactList.add(data);
	//
	// }
	//
	// pCur.close();
	// i++;
	//
	// }
	// }
	//
	// class ConatctData {
	// String[] phone, name;
	// }
	// public HashMap<String, Long> fillGroupsHashMap(HashMap<String, Long>
	// groups) //
	// {
	//
	// Cursor tempCur = this.managedQuery(Groups.CONTENT_URI,
	// new String[] { Groups.GROUP_MY_CONTACTS, Groups.NAME }, null, null,
	// null);
	//
	// tempCur.moveToFirst();
	// while (tempCur.moveToNext()) //
	// {
	// name1.add(tempCur.getString(1));
	// phno1.add(tempCur.getString(0));
	// //groups.put(tempCur.getString(1), tempCur.getLong(0));
	// }
	// return groups;
	// }
	class MyAdapter extends BaseAdapter implements
			CompoundButton.OnCheckedChangeListener {
		SparseBooleanArray mCheckStates;
		LayoutInflater mInflater;
		TextView tv1, tv;
		ImageView profile;

		MyAdapter() {
			mCheckStates = new SparseBooleanArray(name1.size());
			mInflater = (LayoutInflater) Display1.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return name1.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub

			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View vi = convertView;
			if (convertView == null)
				vi = mInflater.inflate(R.layout.row1, null);
			tv = (TextView) vi.findViewById(R.id.textView3);
			tv1 = (TextView) vi.findViewById(R.id.textView4);
			cb = (CheckBox) vi.findViewById(R.id.checkBox1);
			tv.setText("Name :  " + name1.get(position));
			tv1.setText("Phone No :  " + phno1.get(position));
			ImageView profile = (ImageView) vi.findViewById(R.id.profile_pic);
			if (profile != null) {
				long l = Long.parseLong(id1.get(position));
				Bitmap bitmap = loadContactPhoto(getContentResolver(), l);
				if (bitmap != null) {
					profile.setImageBitmap(bitmap);
				} else {
					bitmap = BitmapFactory.decodeResource(getResources(),
							R.drawable.photo_id);
					profile.setImageBitmap(bitmap);
				}
				// set it here in the ImageView

			}
			cb.setTag(position);
			cb.setChecked(mCheckStates.get(position, false));

			cb.setOnCheckedChangeListener(this);

			return vi;
		}

		public boolean isChecked(int position) {
			return mCheckStates.get(position, false);
		}

		public void setChecked(int position, boolean isChecked) {
			mCheckStates.put(position, isChecked);
		}

		public void toggle(int position) {
			setChecked(position, !isChecked(position));
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub

			mCheckStates.put((Integer) buttonView.getTag(), isChecked);
		}

		public View getView1(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public void onItemClick1(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
