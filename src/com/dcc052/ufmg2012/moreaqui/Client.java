package com.dcc052.ufmg2012.moreaqui;

import com.dcc052.ufmg2012.EstateServer.*;
import com.dcc052.ufmg2012.EstateServer.src.server.Command;
import com.dcc052.ufmg2012.EstateServer.src.server.DaoImpl;
import com.dcc052.ufmg2012.EstateServer.src.server.DAO;
import com.dcc052.ufmg2012.EstateServer.src.server.Invoker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Client extends Activity {
  private EditText txtId = null;
  private EditText txtName = null;
  private EditText txtGrade = null;

  abstract class AbstractCommand implements Command, Button.OnClickListener {
    public abstract void execute(DaoImpl d);

    public void onClick(View arg0) {
      Invoker i = new Invoker(DAO.HOST, DAO.PORT);
      DaoImpl d = new DaoImpl();
      i.invoke(d, this);
    }
  }

  class DelCmd extends AbstractCommand implements Command,
      Button.OnClickListener {
    public void execute(DaoImpl d) {
      long id = Long.parseLong(txtId.getText().toString());
      d.delete(id);
    }
  }

  class UpdCmd extends AbstractCommand implements Command,
      Button.OnClickListener {
    public void execute(DaoImpl d) {
    	
      long id = Long.parseLong(txtId.getText().toString());
      String type = "Casa";
      String size = "Pequena";
      int phone = 456;
      String status = "Concluida";
      
      Estate est = new Estate(type, size, phone, status);
      d.update(id, est);
    }
  }

  class AddCmd extends AbstractCommand implements Command,
      Button.OnClickListener {
    public void execute(DaoImpl d) {
    	
    	long id = (long)10.00;
        String type = "Apartamento";
        String size = "Grande";
        int phone = 789;
        String status = "Concluida";
        
        Estate est = new Estate(type, size, phone, status);
      
      d.add(id, est);
    }
  }

  class GetCmd extends AbstractCommand implements Command,
      Button.OnClickListener {
    public void execute(DaoImpl d) {
      String strId = txtId.getText().toString();
      long id = Long.parseLong(strId);
      Estate e = (Estate) d.get(id);
      txtName.setText(e.TYPE);
      txtGrade.setText(String.valueOf(e.SIZE));
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.client);
    txtId = (EditText) findViewById(R.id.txtId);
    txtName = (EditText) findViewById(R.id.txtName);
    txtGrade = (EditText) findViewById(R.id.txtGrade);

    ((Button) findViewById(R.id.get)).setOnClickListener(new GetCmd());
    ((Button) findViewById(R.id.add)).setOnClickListener(new AddCmd());
    ((Button) findViewById(R.id.upd)).setOnClickListener(new UpdCmd());
    ((Button) findViewById(R.id.del)).setOnClickListener(new DelCmd());

    ((Button) findViewById(R.id.cln))
        .setOnClickListener(new Button.OnClickListener() {
          public void onClick(View arg0) {
            txtId.setText("");
            txtName.setText("");
            txtGrade.setText("");
          }
        });
    
    DaoImpl d = new DaoImpl();
    
    long id = (long)10.00;
    String type = "Apartamento";
    String size = "Grande";
    int phone = 789;
    String status = "Concluida";
    
    Estate est = new Estate(type, size, phone, status);
  long i = (long)10;
   d.add(i, est);

  }
}
