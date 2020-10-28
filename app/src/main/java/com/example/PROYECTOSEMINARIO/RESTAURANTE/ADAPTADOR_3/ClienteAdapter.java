package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;

import java.util.ArrayList;

public class ClienteAdapter extends BaseAdapter {

    public ArrayList<EsCliente> LISTCLIENT;
    public Context context;

    public ClienteAdapter(ArrayList<EsCliente> data, Context context) {
        LISTCLIENT = data;
        this.context = context;


    }

    @Override
    public int getCount() {
        return LISTCLIENT.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTCLIENT.get(position);
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
            nombre.setText(LISTCLIENT.get(position).getNombre());

            final TextView email = convertView.findViewById(R.id.descripcionVM);
            email.setText(LISTCLIENT.get(position).getEmail());

            final TextView telefono = convertView.findViewById(R.id.precioVM);
            telefono.setText(LISTCLIENT.get(position).getTelefono());

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVM);
            Button editar = convertView.findViewById(R.id.editarVM);
            id = this.LISTCLIENT.get(position).getId();


        }





        return convertView;
    }
}
