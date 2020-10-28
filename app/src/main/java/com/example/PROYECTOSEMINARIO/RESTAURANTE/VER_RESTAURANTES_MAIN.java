package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_MAIN.EsVRestauranteMAIN;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_MAIN.VResAdapterMAIN;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.VResApi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_RESTAURANTES_MAIN extends AppCompatActivity implements onLoadData {
    ListView listares;

    String id;
    TextView tipo,_idc;
    ArrayList<EsVRestauranteMAIN> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurantes_main);

        listares = findViewById(R.id.lisrestaurantcliente2A);
        VResApi api= new VResApi(this);
        api.loadRes();
    }


    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {

            EsVRestauranteMAIN item= new EsVRestauranteMAIN();
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
                if(data.getJSONObject(i).has("property")){
                    item.setPropietario(data.getJSONObject(i).getString("property"));
                }else{
                    item.setImagen("");
                }





                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        VResAdapterMAIN adapter=new VResAdapterMAIN(datos, VER_RESTAURANTES_MAIN.this);

        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}