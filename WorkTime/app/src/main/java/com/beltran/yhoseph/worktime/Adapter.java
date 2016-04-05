package com.beltran.yhoseph.worktime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhoseph on 04/04/2016.
 */
public class Adapter extends BaseAdapter {
    private static LayoutInflater inflater=null;
    ArrayList<TimerCustom> result;
    Context context;
    public Adapter(MainActivity mainActivity, ArrayList<TimerCustom> prgmNameList) {
        this.result=prgmNameList;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.time, null);
        holder.tv=(TextView) rowView.findViewById(R.id.Tipe);
        if(result.get(position).introCustom){
            holder.tv.setText("Entrada");
        }else{
            holder.tv.setText("Salida");
        }
        holder.tv1=(TextView) rowView.findViewById(R.id.dates);
        holder.tv1.setText(result.get(position).date.toString());
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You Clicked " + result.get(position).date.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
        return rowView;
    }

    public class Holder
    {
        TextView tv;
        TextView tv1;
    }

}