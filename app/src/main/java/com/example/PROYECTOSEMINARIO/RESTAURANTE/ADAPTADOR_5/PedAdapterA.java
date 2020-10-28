package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.INFO_PEDIDOS;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_4.PedAdapter;

import java.util.ArrayList;

public class PedAdapterA extends BaseAdapter {

    public ArrayList<EsPedidoA> LISTPEDIDOSA;
    public Context context;
    PedAdapter.editarRest listem;

    public interface editarRest{
        void EdRest (String data);

    }

    public PedAdapterA(ArrayList<EsPedidoA> data, Context context) {
        LISTPEDIDOSA = data;
        this.context = context;
        // this.listem=listem;
    }

    @Override
    public int getCount() {
        return LISTPEDIDOSA.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTPEDIDOSA.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listpedidoadmi,null);

            final TextView pago_total = convertView.findViewById (R.id.pago_totalVP);
            pago_total.setText(LISTPEDIDOSA.get(position).getPago_total());

            final TextView lugar_envio = convertView.findViewById (R.id.lugar_envioVP);
            lugar_envio.setText(LISTPEDIDOSA.get(position).getLugar_envio());

            final TextView cantidad = convertView.findViewById (R.id.cantidadVP);
            cantidad.setText(LISTPEDIDOSA.get(position).getCantidad());

            final TextView precios = convertView.findViewById (R.id.preciosVP);
            precios.setText(LISTPEDIDOSA.get(position).getPrecios());








            final String id;


            Button info = convertView.findViewById(R.id.infoVPA);
            id = this.LISTPEDIDOSA.get(position).getId();

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, INFO_PEDIDOS.class);

                    intent.putExtra("lugar_envio",lugar_envio.getText());
                    //  intent.putExtra("precios",precios.getText());
                    intent.putExtra("cantidad",cantidad.getText());
                    // intent.putExtra("pago_total",pago_total.getText());
                   intent.putExtra("id",id);
                    context.startActivity(intent);


                }
            });
        }

        return convertView;
    }




    }

