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
=======
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/** MoreAqui.
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
        Button btSave = (Button) findViewById(R.id.btSave);

        btNew.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        btMap.setOnClickListener(this);
        btSave.setOnClickListener(this);

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
        case R.id.btSearch: 
        	Intent j = new Intent(this, EstateListActivity.class);
        	startActivityForResult(j, mCodeNewState);
        	break;
        case R.id.btSave: 
        	Intent k = new Intent(this, Client.class);
        	startActivityForResult(k, mCodeNewState);
        	break;
        case R.id.btMap: 
        	break;
        default:
            break;
        }
    }

}
