package com.dcc052.ufmg2012.moreaqui;

import static android.provider.BaseColumns._ID;

import static com.dcc052.ufmg2012.moreaqui.Constants.*;

import com.dcc052.ufmg2012.moreaqui.R;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class EstateListActivity extends ListActivity {
  private EstatesData estates;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.estate_list_activity);
    estates = new EstatesData(this);
    registerForContextMenu(getListView());

    estates = new EstatesData(this);
    
    showEstates(getEvents());
  }

  private void addEstate(String type, String size, int phone, String status) {
  	SQLiteDatabase db = estates.getWritableDatabase();
  	ContentValues values = new ContentValues();
  	values.put("type", type);
  	values.put("size", size);
  	values.put("phone", phone);
  	values.put("status", status);
  	
  	db.insertOrThrow(TABLE_NAME, null, values);
  }
  
  private static String[] FROM = { _ID, TYPE, SIZE, PHONE, STATUS, };
 

  private Cursor getEvents() {
    // Perform a managed query. The Activity will handle closing
    // and re-querying the cursor when needed.
    SQLiteDatabase db = estates.getReadableDatabase();
    Cursor cursor = db
        .query(TABLE_NAME, FROM, null, null, null, null, null);
    startManagingCursor(cursor);
    return cursor;
  }

  private static int[] TO = { R.id.rowid, R.id.type, R.id.size, R.id.phone, R.id.status};

  private void showEstates(Cursor cursor) {
    // Set up data binding
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item,
        cursor, FROM, TO);
    setListAdapter(adapter);
  }
  
}