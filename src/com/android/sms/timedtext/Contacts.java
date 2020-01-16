package com.android.sms.timedtext;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



public class Contacts extends ListActivity {
private CheckBox checkBox;
private ListView listView;

@Override
public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 listView=(ListView)findViewById (R.id.list);
 final ListView listView = getListView();
 listView.setItemsCanFocus(false);
 listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
// listView.setAdapter(new ArrayAdapter<String>(this,  
//  android.R.layout.simple_list_item_multiple_choice, lv_items));  





 Cursor cursor = getContentResolver().query(People.CONTENT_URI, 
         new String[]{People._ID,People.NAME,People.NUMBER}, null, null, null);
 startManagingCursor(cursor);

 // start mappings
 String[] columns = new String[] {People.NAME, People.NUMBER};
 int[] names = new int[] {R.id.contact_name, R.id.phone_number};

 SimpleCursorAdapter myAdapter = new SimpleCursorAdapter(this, R.layout.contact,   cursor, columns, names);
 setListAdapter(myAdapter);
 this.setListAdapter(myAdapter);
}



//  public boolean onCreateOptionsMenu(Menu menu) {
// MenuInflater inflater = getMenuInflater();
// inflater.inflate(R.menu.firstmenu,menu);
// return true;  
//}  
// public boolean onOptionsItemSelected(MenuItem item){
//switch(item.getItemId()){
//case R.id.next:
//   next();
// break;
//case R.id.select:
//  return true;
//case R.id.back:
//   final Intent i = new Intent(this,SelectContact.class);
//   startActivity(i);
//   break;
//}
//return false;
}