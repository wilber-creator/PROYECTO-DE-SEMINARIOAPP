package com.example.PROYECTOSEMINARIO.RESTAURANTE.adapter1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.EDITAR_RESTAURANTE;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class RESTAURANTE_ADAPTAR extends BaseAdapter {
     public ArrayList<EsRestaurante> LISTRESTAURANT;
     public Context context;
     editarRest listem;

     public interface editarRest{
         void EdRest (String data);

     }

    public RESTAURANTE_ADAPTAR(ArrayList<EsRestaurante> data, Context context){
        LISTRESTAURANT=data;
        this.context=context;
       // this.listem=listem;


    }

    @Override
    public int getCount() {

        return LISTRESTAURANT.size();
    }

    @Override
    public Object getItem(int position) {
        return LISTRESTAURANT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listrest1,null);

            final TextView nombre = convertView.findViewById (R.id.nombreVM);
            nombre.setText(LISTRESTAURANT.get(position).getNombre());

            final TextView calle = convertView.findViewById (R.id.descripcionVM);
            calle.setText(LISTRESTAURANT.get(position).getCalle());

            final TextView telefono = convertView.findViewById (R.id.precioVM);
            telefono.setText(LISTRESTAURANT.get(position).getTelefono());

            //ImageView image = convertView.findViewById (R.id.imagenVM);
            /*if(LISTRESTAURANT.get(position).getImagen().equals("No IMAGE")){
                Glide.with(context)
                        .load("https://image.freepik.com/vector-gratis/plantilla-fondo-menu-restaurante_23-2147490036.jpg")
                        .centerCrop()
                        .into(image);
            }else{
                Glide.with(context)
                        .load(LISTRESTAURANT.get(position).getImagen())
                        .centerCrop()
                        .into(image);
            }*/

            final String id;

            Button eliminar = convertView.findViewById(R.id.eliminarVM);
            Button editar = convertView.findViewById(R.id.editarVM);
            id = this.LISTRESTAURANT.get(position).getId();

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
                    Intent intent = new Intent(context, EDITAR_RESTAURANTE.class);
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
        }
        return convertView;
    }

    // METODO DELETE RESTAURANTE

    private void deleteMenu(final String id) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.delete("http://192.168.100.102:8000/api/1.0/restaurante" + "?id=" + id, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("msn");
                    if (message != null) {

                        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                        // loadComponents();
                                /*BorrarMenuAdapter adapter = new BorrarMenuAdapter(view);
                                        adapter.notifyDataSetChanged();9*/


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
