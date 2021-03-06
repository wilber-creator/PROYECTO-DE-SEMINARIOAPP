package com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_MENU_ADMIN;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.EDITAR_MENU_1;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AMenuAdapter extends BaseAdapter {

    public ArrayList<EsMenuA> LISTMENUAdmi;
    public Context context;

    public AMenuAdapter(ArrayList<EsMenuA> data, Context context) {
        LISTMENUAdmi = data;
        this.context = context;

    }

    @Override
    public int getCount() {
        return LISTMENUAdmi.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTMENUAdmi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listmenuadmi, null);

            final TextView nombre = convertView.findViewById(R.id.nombreVMA);
            nombre.setText(LISTMENUAdmi.get(position).getNombre());

            final TextView descripcion = convertView.findViewById(R.id.descripcionVMA);
            descripcion.setText(LISTMENUAdmi.get(position).getDescripcion());

            final TextView precio = convertView.findViewById(R.id.precioVMA);
            precio.setText(LISTMENUAdmi.get(position).getPrecio());

            final ImageView foto = convertView.findViewById(R.id.imagenVMA);

            if (LISTMENUAdmi.get(position).getFoto().equals("No IMAGE")) {
                Glide.with(context)
                        .load("https://st3.depositphotos.com/1000693/18161/v/1600/depositphotos_181614828-stock-illustration-fork-knife-spoon-and-headdress.jpg")
                        .centerCrop()
                        .into(foto);
            } else {
                Glide.with(context)
                        .load(LISTMENUAdmi.get(position).getFoto())
                        .centerCrop()
                        .into(foto);
            }

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVMA);
            Button editar = convertView.findViewById(R.id.editarVMA);


            id = this.LISTMENUAdmi.get(position).getId();
            eliminar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "eliminado el restaurante id ="+id, Toast.LENGTH_LONG).show();
                    deleteMenu(id);


                }



            });
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EDITAR_MENU_1.class);

                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("descripcion",descripcion.getText());
                    intent.putExtra("precio",precio.getText());
                    intent.putExtra("id",id);
                    context.startActivity(intent);


                }
            });



        }

        return convertView;
    }

    private void deleteMenu(final String id) {


        AsyncHttpClient client = new AsyncHttpClient();

        client.delete("http://192.168.100.102:8000/api/1.0/menus" + "?id=" + id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("msn");
                    if (message != null) {

                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();



                    } else {
                        Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




        });
    }
}
