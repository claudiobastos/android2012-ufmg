package com.dcc052.ufmg2012.moreaqui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class MoreAqui extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btNew = (Button) findViewById(R.id.btNew);
        Button btSearch = (Button) findViewById(R.id.btSearch);
        Button btMap = (Button) findViewById(R.id.btMap);
        
        
        btNew.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        btMap.setOnClickListener(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	@Override
	public void onClick(View v) {
		int who = v.getId();
		switch(who){
		case R.id.btNew: break;
		case R.id.btSearch: break;
		case R.id.btMap: break;
		}
	}

    
}
