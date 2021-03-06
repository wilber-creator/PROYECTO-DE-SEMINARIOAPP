package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_1.EsRestaurante;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_1.ResAdapter1;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.ResApi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_RESTAURANTE_1 extends AppCompatActivity implements onLoadData {

    ListView listares;
    String id;
    ImageButton atrasVR;
    ArrayList<EsRestaurante> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurante1);

        listares = findViewById(R.id.lisrestaurant2);

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");

        ResApi api= new ResApi(this);

        api.loadRes(id);

    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {


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
        ResAdapter1 adapter=new ResAdapter1(datos, VER_RESTAURANTE_1.this);

        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }



/*
    private void loadComponents() {

        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.180:8000/api/1.0/restaurante",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("nombre"));
                        res.setTelefono(object.getInt("telefono"));
                        res.setCalle(object.getString("calle"));
                        //menus.setFoto(object.getString("foto"));
                        restaurants.add(res);
                    }
                    ResAdapter adapter =  new ResAdapter(VER_RESTAURANTE_1.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });

    }*/
}