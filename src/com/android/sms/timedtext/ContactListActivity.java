package com.android.sms.timedtext;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

public class ContactListActivity extends ListActivity {
	public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
       String[] from = new String[] {ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.Contacts.HAS_PHONE_NUMBER,ContactsContract.Contacts._ID};
       int[] to = new int[] {R.id.checkBox};
       Cursor cursor = null;
       ContactListCursorAdapter adapter = new ContactListCursorAdapter(getApplicationContext(), R.layout.listview,  cursor, from, to);
       setListAdapter(adapter);
    }
}
