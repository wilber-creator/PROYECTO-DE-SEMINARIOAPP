package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import collects1.RESTAURANTE_ADAPTAR;
import collects1.Menus;
import cz.msebera.android.httpclient.Header;

public class ELIMINAR_MENU extends AppCompatActivity {
    ListView list3;
    ImageButton atras2;
   // RESTAURANTE_ADAPTAR adapter;

    ArrayList<Menus> list_data2 = new ArrayList<Menus>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_menu);
        atras2 = findViewById(R.id.imageatras);
        list3 = findViewById(R.id.elist);
        atras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ELIMINAR_MENU.this, INFO_RESTAURANTE.class));
                finish();
            }
        });

        loadComponents();
        list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                list_data2.remove(position);

                //adapter.notifyDataSetChanged();
            }
        });
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.delete ("http://192.168.100.71:8000/api/1.0/menus",  new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {

                try {

                    for (int i =0 ; i < jsonArray.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = jsonArray.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));
                        //menus.setFoto(object.getString("foto"));
                        list_data2.add(menus);
                    }
                    //adapter =  new RESTAURANTE_ADAPTAR(ELIMINAR_MENU.this,list_data2);
                    //list3.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }


}