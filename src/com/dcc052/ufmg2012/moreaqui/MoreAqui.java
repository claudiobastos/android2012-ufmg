package com.dcc052.ufmg2012.moreaqui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Classe basica do programa.
 * @author Particular
 *
 */
public class MoreAqui extends Activity implements OnClickListener {

    /**
     * codigo para atividade de inserção de novos imóveis.
     */
    private final int mCodeNewState = 1;

    /**
     * Prepara o layout principal.
     * @param savedInstanceState Bundle com informações
     *  para re-criação da atividade.
     */
    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btNew = (Button) findViewById(R.id.btNew);
        Button btSearch = (Button) findViewById(R.id.btSearch);
        Button btMap = (Button) findViewById(R.id.btMap);

        btNew.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        btMap.setOnClickListener(this);

    }

    /**
     * trata os clicks nos botoes.
     * @param v visão onde será verificado o click
     */
    @Override
    public final void onClick(final View v) {
        int who = v.getId();
        switch(who) {
        case R.id.btNew:
            Intent i = new Intent(this, InsertActivity.class);
            startActivityForResult(i, mCodeNewState);
            break;
        case R.id.btSearch: break;
        case R.id.btMap: break;
        default:
            break;
        }
    }

    /**
     * @param requestCode auxilia determinar qual operação está retornando
     * @param resultCode resultado da operação da atividade
     * @param data Intent com os valores passados pela atividade anterior
     */
    @Override
    protected final void onActivityResult(final int requestCode,
        final int resultCode, final Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
        case mCodeNewState:
            Estate e = (Estate) data.getSerializableExtra("estate");
            Log.w("New", e.toString());
            Toast.makeText(this, "New instance", Toast.LENGTH_SHORT).show();
            break;
        default:
            break;
        }
    }

}
