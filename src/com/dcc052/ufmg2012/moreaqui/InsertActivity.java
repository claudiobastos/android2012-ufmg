package com.dcc052.ufmg2012.moreaqui;

import java.io.Serializable;


import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;
import static android.provider.BaseColumns._ID;
import static com.dcc052.ufmg2012.moreaqui.Estate.*;
import static com.dcc052.ufmg2012.moreaqui.EstatesData.*;
import static com.dcc052.ufmg2012.moreaqui.Constants.*;

/**
 * Classe que irá obter os parametros do imóvel.
 * @author Claudio Bastos
 * @author Antonio Leite
 *
 */
public class InsertActivity extends Activity implements OnClickListener {
	private EstatesData estates;

    /**
     * Inicia a apresentação da tela.
     * @param savedInstanceState Dados da instancia anterior
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        
        Button btSave = (Button) findViewById(R.id.btSave);
        btSave.setOnClickListener(this);

        estates = new EstatesData(this);
    }

    /**
     * Verifica o telefone preenchido.
     * @param v
     * @return true para telefone corretamente preenchido
     */
    public final boolean telValido(){
        //Avisa que o numero é inválido
    	
        EditText tel = (EditText) findViewById(R.id.editFone);
        if(tel.getText().toString().equals("")){
        	Toast t = Toast.makeText(this,
            R.string.error_invalid_number, Toast.LENGTH_LONG);
        	t.show();
        }else{
        	int num = Integer.valueOf(tel.getText().toString());
            return true;
        }
        return false;
    }

    /**
     * trata o evento de confirmação do imóvel.
     * @param v botao do click de confirmação
     */
    @Override
    public final void onClick(final View v) {
        if (telValido()) {
        	
            Intent i = new Intent();
            Bundle b = new Bundle();

            
            RadioGroup typeGroup = (RadioGroup) findViewById(R.id.rdgType);
            int typeSelected = typeGroup.getCheckedRadioButtonId();
            RadioButton type = (RadioButton) findViewById(typeSelected);

            RadioGroup sizeGroup = (RadioGroup) findViewById(R.id.rdgSize);
            int sizeSelected = sizeGroup.getCheckedRadioButtonId();
            RadioButton size = (RadioButton) findViewById(sizeSelected);

            EditText fone = (EditText) findViewById(R.id.editFone);
            CheckBox building = (CheckBox) findViewById(R.id.ckBuilding);
            
            String status;
            if(building.isChecked()){
            	status = "Em construcao";
            }else{
            	status = "Concluido";
            }
            
            
           
            
            Estate e = new Estate(type.getText().toString(),
                    size.getText().toString(),
                    Integer.valueOf(fone.getText().toString()),
                    status);
            Log.v("New", e.toString());
                            
            
            
            try {
            		addEstate(e.TYPE,e.SIZE,e.PHONE,e.STATUS);
            		Cursor cursor = getEstates();
            	} finally {
            		estates.close();
            }
                       
            
            //finish();
        }
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
    
    private Cursor getEstates() {
    	SQLiteDatabase db = estates.getReadableDatabase();
    	String sql;
    	Cursor cursor; 
    	
    	sql = "SELECT * FROM estates";
    	cursor = db.rawQuery(sql, null);
    	extracted(cursor);
    	return cursor;
    }

	private void extracted(Cursor cursor) {
		startManagingCursor(cursor);
	}
	
	private void showEstates(Cursor cursor) {
		StringBuilder builder = new StringBuilder( "Saved estates:\n");
		while (cursor.moveToNext()) {
		long id = cursor.getLong(0);
		String type = cursor.getString(1);
		String size = cursor.getString(2);
		int phone = cursor.getInt(3);
		String status = cursor.getString(4);
		builder.append(id).append(": ");
		builder.append(type).append(" - ");
		builder.append(size).append(" - ");
		builder.append(phone).append(" - ");
		builder.append(status).append(" - \n");
		}
		setContentView(R.layout.view);
		TextView text = (TextView) findViewById(R.id.text);
		text.setText(builder);
		}
}
