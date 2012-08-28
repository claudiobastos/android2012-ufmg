package com.dcc052.ufmg2012.moreaqui;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Classe que irá obter os parametros do imóvel.
 * @author Claudio Bastos
 * @author Antonio Leite
 *
 */
public class InsertActivity extends Activity implements OnClickListener {

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
    }

    /**
     * Verifica o telefone preenchido.
     * @param v
     * @return true para telefone corretamente preenchido
     */
    public final boolean telValido(){
        //Avisa que o numero é inválido
        EditText tel = (EditText) findViewById(R.id.editFone);
        int num = Integer.valueOf(tel.getText().toString());
        if (num > 0) {
            Log.w("MoreAqui","");
            return true;
        } else {
            Toast t = Toast.makeText(this,
                      R.string.error_invalid_number, Toast.LENGTH_LONG);
            t.show();
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

            Estate e = new Estate(type.getText().toString(),
                    size.getText().toString(),
                    Integer.valueOf(fone.getText().toString()),
                    building.toString());
            b.putSerializable("estate", (Serializable) e);
            i.putExtra("extras", b);
            setResult(RESULT_OK, i);
            finish();
        }
    }
}
