package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_PEDIDOS_ADD;

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
import com.example.PROYECTOSEMINARIO.RESTAURANTE.VER_MENUS_PEDIDOS_ADMI;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_1.ResAdapter1;

import java.util.ArrayList;

public class ResPeAdAdapter extends BaseAdapter {

    public ArrayList<EsPADRestaurante> LISTRESTAURANTPAD;
    public Context context;
    ResAdapter1.editarRest listem;


    public interface editarRest{
        void EdRest (String data);

    }

    public ResPeAdAdapter(ArrayList<EsPADRestaurante> data, Context context){
        LISTRESTAURANTPAD=data;
        this.context=context;
        // this.listem=listem;


    }
    @Override
    public int getCount() {
        return LISTRESTAURANTPAD.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTRESTAURANTPAD.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listrespedad,null);

            final TextView nombre = convertView.findViewById (R.id.nombreVM);
            nombre.setText(LISTRESTAURANTPAD.get(position).getNombre());

            final TextView calle = convertView.findViewById (R.id.descripcionVM);
            calle.setText(LISTRESTAURANTPAD.get(position).getCalle());

            final TextView telefono = convertView.findViewById (R.id.precioVM);
            telefono.setText(LISTRESTAURANTPAD.get(position).getTelefono());

            final TextView propietario = convertView.findViewById (R.id.propietarioVMPAD);
            propietario.setText(LISTRESTAURANTPAD.get(position).getPropietario());

            ImageView image = convertView.findViewById (R.id.imagenVM);
            if(LISTRESTAURANTPAD.get(position).getImagen().equals("No IMAGE")){
                Glide.with(context)
                        .load("https://st3.depositphotos.com/1000693/18161/v/1600/depositphotos_181614828-stock-illustration-fork-knife-spoon-and-headdress.jpg")
                        .centerCrop()
                        .into(image);
            }else{
                Glide.with(context)
                        .load(LISTRESTAURANTPAD.get(position).getImagen())
                        .centerCrop()
                        .into(image);
            }

            final String id;


            Button info = convertView.findViewById(R.id.infoVMPAD);
            Button menus = convertView.findViewById(R.id.menusVMPAD3);

            id = this.LISTRESTAURANTPAD.get(position).getId();

            info.setOnClickListener(new View.OnClickListener() {
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

            menus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentM = new Intent(context, VER_MENUS_PEDIDOS_ADMI.class);

                    intentM.putExtra("nombre",nombre.getText());
                    intentM.putExtra("telefono",telefono.getText());
                    intentM.putExtra("calle",calle.getText());
                    intentM.putExtra("id",id);
                    context.startActivity(intentM);


                }
            });


        }

        return convertView;
    }


}
