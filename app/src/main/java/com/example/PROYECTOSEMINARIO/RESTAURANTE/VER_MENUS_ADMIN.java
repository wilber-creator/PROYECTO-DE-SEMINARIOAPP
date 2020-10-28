package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_MENU_ADMIN.AMenuAdapter;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_MENU_ADMIN.EsMenuA;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.VMenusAdmi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_MENUS_ADMIN extends AppCompatActivity  implements onLoadData {
    ListView listares;
    ImageButton atrasVMC1;
    TextView _id;
    String id;


    ArrayList<EsMenuA> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menus_admi);

        atrasVMC1 = findViewById(R.id.atrasVMC1);
        atrasVMC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VER_MENUS_ADMIN.this, VENTANA_MENU_ADMIN.class));

            }
        });

        _id=(TextView) findViewById(R.id.idrestauVM);
        _id.setText( getIntent().getExtras().getString("_id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");


        listares = findViewById(R.id.listamenuAdmiVM);


        VMenusAdmi api= new VMenusAdmi(this);
       // api.loadMenu(_id.getText().toString());
        api.loadMenu(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsMenuA item= new EsMenuA();
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
        AMenuAdapter adapter=new AMenuAdapter(datos, VER_MENUS_ADMIN.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}