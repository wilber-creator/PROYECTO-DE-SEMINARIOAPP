package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_1.EsRestaurante;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_1.ResAdapter1;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.ResApiAD;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_RESTAURANTE_ADMIN extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVR;

    ArrayList<EsRestaurante> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurante_admi);
         /*
        atrasVR = findViewById(R.id.imatras);
        atrasVR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EDITAR_MENU.this,InfoRestaurant.class));

            }
        });*/
        listares = findViewById(R.id.lisrestaurant3);
        ResApiAD api= new ResApiAD(this);
        api.loadRes();
        /*
        for (int i = 0; i < 100; i++) {
            //datos.add("item" + i);

            EsRestaurante item= new EsRestaurante();
            item.setNombre("nombre"+ i);
            item.setTelefono("telefono" + i);
            item.setCalle("calle"+ i);
            item.setImagen("No IMAGE");
            datos.add(item);
        }
        ResAdapter1 adapter=new ResAdapter1(datos,this.getBaseContext());
       // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);*/
    }


    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsRestaurante item= new EsRestaurante();
            try {
                if(data.getJSONObject(i).has("name")){
                    item.setNombre(data.getJSONObject(i).getString("name"));
                }else{
                    item.setNombre("");
                }
                if(data.getJSONObject(i).has("phone")){
                    item.setTelefono(data.getJSONObject(i).getString("phone"));
                }else{
                    item.setTelefono("");
                }
                if(data.getJSONObject(i).has("street")){
                    item.setCalle(data.getJSONObject(i).getString("street"));
                }else{
                    item.setCalle("");
                }
                if(data.getJSONObject(i).has("picture")){
                    item.setImagen(data.getJSONObject(i).getString("picture"));
                }else{
                    item.setImagen("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        ResAdapter1 adapter=new ResAdapter1(datos, VER_RESTAURANTE_ADMIN.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}