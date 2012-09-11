/*package com.dcc052.ufmg2012.moreaqui;

import com.dcc052.ufmg2012.EstateServer.*;
import com.dcc052.ufmg2012.EstateServer.src.server.DAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaveEstatesActivity extends Activity {
    /** Called when the activity is first created. *//*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText txtId = (EditText) findViewById(R.id.txtId);
        final EditText txtName = (EditText) findViewById(R.id.txtName);
        final EditText txtGrade = (EditText) findViewById(R.id.txtGrade);
        
        ((Button) findViewById(R.id.read))
            .setOnClickListener(new Button.OnClickListener() {
              public void onClick(View arg0) {
                String strId = txtId.getText().toString();
                long id = Long.parseLong(strId);
                DAO d = new DAO();
                Estate e = d.get(id);
                //txtName.setText(s.name);
                //txtGrade.setText(String.valueOf(s.grade));
              }
            });
    }
}*/