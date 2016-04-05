package com.beltran.yhoseph.worktime;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Context context;
    Button Entrada;
    Button Salida;

    ArrayList<TimerCustom> prgmName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entrada = (Button)findViewById(R.id.entradaButton);
        Salida = (Button)findViewById(R.id.salidaButton);
        context=this;
        prgmName = new ArrayList<TimerCustom>();
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, prgmName));

    }
    public void entrada(View view){
        prgmName.add(new TimerCustom(new Date(),true));
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, prgmName));
        Entrada.setVisibility(View.GONE);
        Salida.setVisibility(View.VISIBLE);
    }
    public void salida(View view){
        prgmName.add(new TimerCustom(new Date(),false));
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, prgmName));
        Entrada.setVisibility(View.VISIBLE);
        Salida.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
