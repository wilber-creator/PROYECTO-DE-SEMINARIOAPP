package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.Menus;
import collects1.VerMenuAdapter;
import cz.msebera.android.httpclient.Header;

public class VER_MENIU extends AppCompatActivity {
    ListView listamenu;
    ImageButton atras;
    ArrayList<Menus> list_data = new ArrayList<Menus> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menu);
        atras = findViewById(R.id.imgatras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VER_MENIU.this, INFO_RESTAURANTE.class));
                finish();
            }
        });
        loadComponents();

    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.102:8000/api/1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
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
                        list_data.add(menus);
                    }
                    VerMenuAdapter adapter =  new VerMenuAdapter(VER_MENIU.this,list_data);
                    listamenu = findViewById(R.id.listamenu);
                    listamenu.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });




    }
}