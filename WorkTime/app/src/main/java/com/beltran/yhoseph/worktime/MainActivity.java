package com.beltran.yhoseph.worktime;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beltran.yhoseph.worktime.util.TimerDate;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Context context;
    Button Entry;
    Button Exit;
    boolean EntryStatus;
    final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

    ArrayList<TimerCustom> listTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Entry = (Button)findViewById(R.id.entradaButton);
        Exit = (Button)findViewById(R.id.salidaButton);
        context=this;
        EntryStatus = false;
        listTimer = new ArrayList<TimerCustom>();
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, listTimer));

    }
    public void entry(View view){
        EntryStatus = true;
        listTimer.add(new TimerCustom(new Date(),true));
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, listTimer));
        Entry.setVisibility(View.GONE);
        Exit.setVisibility(View.VISIBLE);
    }
    public void exit(View view){
        EntryStatus = false;
        listTimer.add(new TimerCustom(new Date(),false));
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new Adapter(this, listTimer));
        Entry.setVisibility(View.VISIBLE);
        Exit.setVisibility(View.GONE);
        updateTime(view);
    }
    public void updateTime(View view){
        TextView time = (TextView)findViewById(R.id.timeLess);
        int max = listTimer.size();
        if (EntryStatus){
            max = max - 1;
        }
        TimerDate timer= new TimerDate(0,0,0,0);
        for (int i = 0;i< max ; i=i+2){
            Date a= listTimer.get(i).date;
            Date b= listTimer.get(i+1).date;
            long diff = b.getTime() - a.getTime() ;
            timer = changeTime(timer,diff);
            Log.e("YHOSEPH",""+b.getTime() +"----"+ a.getTime()+"-----"+diff);
        }
        if (EntryStatus){
            Date a= listTimer.get(max).date;
            Date b= new Date();
            long diff = b.getTime() - a.getTime() ;
            timer = changeTime(timer,diff);

        }
        time.setText(""+timer.hours + ":" + timer.minutes + ":"+ timer.seconds);
    }
    public TimerDate changeTime(TimerDate time,long diff){
        long mill =  diff%1000;
        long diffSegundos =  Math.abs (diff / 1000);
        long segundos = diffSegundos%60;
        long diffMinutos =  Math.abs (diff / (60 * 1000));
        long minutes = diffMinutos%60;
        long hours =   (diff / (60 * 60 * 1000));
        time.milliseconds = time.milliseconds + mill;
        if (time.milliseconds>999){
            time.seconds = time.seconds +1;
            time.milliseconds = time.milliseconds - 1000;
        }
        time.seconds = time.seconds + segundos;
        if (time.seconds>59){
            time.minutes = time.minutes +1;
            time.seconds = time.seconds - 60;
        }
        time.minutes = time.minutes + minutes;
        if (time.minutes>59){
            time.hours = time.hours +1;
            time.minutes = time.minutes - 60;
        }
        time.hours = time.hours + hours;
        return new TimerDate(time.hours,time.minutes,time.seconds,time.milliseconds);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
