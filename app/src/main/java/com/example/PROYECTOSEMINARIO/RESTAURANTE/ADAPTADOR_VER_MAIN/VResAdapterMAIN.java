package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_MAIN;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.INFO_REST_CA;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.VER_MENUS_MAIN;

import java.util.ArrayList;

public class VResAdapterMAIN extends BaseAdapter {



    TextView tipo,_idc;

    public ArrayList<EsVRestauranteMAIN> LISTRESTAURANTMAIN;
    public Context context;



    public interface editarRest{
        void EdRest (String data);

    }
    public VResAdapterMAIN(ArrayList<EsVRestauranteMAIN> data, Context context){
        LISTRESTAURANTMAIN=data;
        this.context=context;



    }
    @Override
    public int getCount() {
        return LISTRESTAURANTMAIN.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTRESTAURANTMAIN.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listrescliente,null);

            final TextView nombre = convertView.findViewById (R.id.nombreVM);
            nombre.setText(LISTRESTAURANTMAIN.get(position).getNombre());

            final TextView calle = convertView.findViewById (R.id.descripcionVM);
            calle.setText(LISTRESTAURANTMAIN.get(position).getCalle());

            final TextView telefono = convertView.findViewById (R.id.precioVM);
            telefono.setText(LISTRESTAURANTMAIN.get(position).getTelefono());

            final TextView propietario = convertView.findViewById (R.id.propietarioVM);
            propietario.setText(LISTRESTAURANTMAIN.get(position).getPropietario());

            ImageView image = convertView.findViewById (R.id.imagenVM);
            if(LISTRESTAURANTMAIN.get(position).getImagen().equals("No IMAGE")){
                Glide.with(context)
                        .load("https://st3.depositphotos.com/1000693/18161/v/1600/depositphotos_181614828-stock-illustration-fork-knife-spoon-and-headdress.jpg")
                        .centerCrop()
                        .into(image);
            }else{
                Glide.with(context)
                        .load(LISTRESTAURANTMAIN.get(position).getImagen())
                        .centerCrop()
                        .into(image);
            }

            final String id;

            Button menus = convertView.findViewById(R.id.ver_menuLRC);
            Button infoRes = convertView.findViewById(R.id.ver_infoLRC);
            id = this.LISTRESTAURANTMAIN.get(position).getId();

            menus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VER_MENUS_MAIN.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("telefono",telefono.getText());
                    intent.putExtra("calle",calle.getText());
                    intent.putExtra("id",id);
                    context.startActivity(intent);
/*
                String EDRESTAURANTE=nombre.getText()+"/"+telefono.getText()+"/"+calle.getText()+"/"+id;
                listem.EdRest(EDRESTAURANTE);*/

                }
            });
            infoRes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, INFO_REST_CA.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("telefono",telefono.getText());
                    intent.putExtra("calle",calle.getText());
                    intent.putExtra("propietario",propietario.getText());
                    intent.putExtra("id",id);
                    context.startActivity(intent);


                }
            });
        }

        return convertView;
    }
}
