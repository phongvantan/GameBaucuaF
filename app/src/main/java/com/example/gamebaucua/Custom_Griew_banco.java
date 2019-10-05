package com.example.gamebaucua;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class Custom_Griew_banco extends ArrayAdapter<Integer> {
Context context;
int reasoure;
Integer[]objects;
Integer[]giaTien={0,100,200,300,400,500};
ArrayAdapter<Integer>adapter;

    public Custom_Griew_banco( Context context, int resource,Integer[] objects) {
        super(context, resource, objects);

        this.context=context;
        this.reasoure=resource;
        this.objects=objects;
        adapter= new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item,giaTien);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context,reasoure,null);
        ImageView imbau=(ImageView)view.findViewById(R.id.img_bau);
        Spinner spinner=(Spinner)view.findViewById(R.id.spiner_giatien);

        imbau.setImageResource(objects[position]);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View agr1, int positionpin, long id) {
             MainActivity.gtDatcuoc[position]=giaTien[positionpin];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

}
