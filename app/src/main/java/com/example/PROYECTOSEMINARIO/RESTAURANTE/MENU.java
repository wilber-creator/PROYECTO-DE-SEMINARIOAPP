package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.MenuAdapter;
import collects1.Menus;
import cz.msebera.android.httpclient.Header;

public class MENU extends AppCompatActivity {
    ListView lista;
    EditText c;
    TextView p;
    String precios ,cant, total1;
    Double pre,total,can;

    ArrayList<Menus> list_data = new ArrayList<Menus> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lista = MENU.this.findViewById (R.id.lismenu);
        loadComponents();
        lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Menus menus= list_data.get (position);
                AlertDialog.Builder builder = new AlertDialog.Builder (MENU.this);
                LayoutInflater inflater = (MENU.this).getLayoutInflater();
                builder.setTitle("Cantidad");

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                Dialog dialog = builder.create ();
                dialog.show ();


            }
        });

    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.102:8000/api/1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = data.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));

                        list_data.add(menus);
                    }
                    MenuAdapter adapter =  new MenuAdapter(MENU.this,list_data);
                    lista.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });




    }

}