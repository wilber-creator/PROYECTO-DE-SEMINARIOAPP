package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_2.EsMenu;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_2.MenuAdapter1;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.MenuApi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_MENUS extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVR;
    ArrayList<EsMenu> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menus);

        listares = findViewById(R.id.listamenuVM);
        MenuApi api= new MenuApi(this);
        api.loadMenu();
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {

        for (int i = 0; i< data.length() ; i++) {


            EsMenu item= new EsMenu();
            try {
                if(data.getJSONObject(i).has("nombre")){
                    item.setNombre(data.getJSONObject(i).getString("nombre"));
                }else{
                    item.setNombre("");
                }
                if(data.getJSONObject(i).has("precio")){
                    item.setPrecio(data.getJSONObject(i).getString("precio"));
                }else{
                    item.setPrecio("");
                }
                if(data.getJSONObject(i).has("descripcion")){
                    item.setDescripcion(data.getJSONObject(i).getString("descripcion"));
                }else{
                    item.setDescripcion("");
                }
                if(data.getJSONObject(i).has("foto")){
                    item.setFoto(data.getJSONObject(i).getString("foto"));
                }else{
                    item.setFoto("");
                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        MenuAdapter1 adapter=new MenuAdapter1(datos, VER_MENUS.this);

        listares.setAdapter(adapter);




    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}