package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_MAIN.CMenuAdapterMain;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_MAIN.EsMenuMain;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.VMenusMAIN;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_MENUS_MAIN extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVMC1;
    TextView _id;
    String id;

    ArrayList<EsMenuMain> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menus__main);

        listares = findViewById(R.id.listamenuClienVM);

        _id=(TextView) findViewById(R.id.idrestauVMC);
        _id.setText( getIntent().getExtras().getString("id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("id");

        VMenusMAIN api= new VMenusMAIN(this);
        api.loadMenu(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsMenuMain item= new EsMenuMain();
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
        CMenuAdapterMain adapter=new CMenuAdapterMain(datos, VER_MENUS_MAIN.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}