package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    public ArrayList<EsMenu> LISTMENU;
    public Context context;

    public MenuAdapter(ArrayList<EsMenu> data, Context context) {
        LISTMENU = data;
        this.context = context;



    }


    @Override
    public int getCount() {
        return LISTMENU.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENU.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listmenu, null);

            final TextView nombre = convertView.findViewById(R.id.nombreVM);
            nombre.setText(LISTMENU.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVM);
            descripcion.setText(LISTMENU.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVM);
            precio.setText(LISTMENU.get(position).getPrecio());

            ImageView foto = convertView.findViewById(R.id.imagenVM);
            if (LISTMENU.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://st3.depositphotos.com/1000693/18161/v/1600/depositphotos_181614828-stock-illustration-fork-knife-spoon-and-headdress.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENU.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }



        }
        return convertView;
    }
}