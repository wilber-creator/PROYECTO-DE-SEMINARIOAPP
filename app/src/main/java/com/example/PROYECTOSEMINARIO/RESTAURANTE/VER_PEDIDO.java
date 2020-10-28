package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_4.EsPedido;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_4.PedAdapter;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.PediCli;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_PEDIDO extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVPC;
    TextView _id;
    String id;
    ArrayList<EsPedido> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedido);

        atrasVPC = findViewById(R.id.atrasVPC);
        atrasVPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VER_PEDIDO.this, CLIENTE.class));

            }
        });

        listares = findViewById(R.id.lisrestaurant2);

        _id=(TextView) findViewById(R.id.verPC);
        _id.setText( getIntent().getExtras().getString("_id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");
        PediCli api = new PediCli(this);
        api.loadPed(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {
        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsPedido item= new EsPedido();
            try {
                if(data.getJSONObject(i).has("lugar_envio")){
                    item.setLugar_envio(data.getJSONObject(i).getString("lugar_envio"));
                }else{
                    item.setLugar_envio("");
                }
                if(data.getJSONObject(i).has("precios")){
                    item.setPrecios(data.getJSONObject(i).getString("precios"));
                }else{
                    item.setPrecios("");
                }
                if(data.getJSONObject(i).has("cantidad")){
                    item.setCantidad(data.getJSONObject(i).getString("cantidad"));
                }else{
                    item.setCantidad("");
                }
                if(data.getJSONObject(i).has("pago_total")){
                    item.setPago_total(data.getJSONObject(i).getString("pago_total"));
                }else{
                    item.setPago_total("");
                }
                if(data.getJSONObject(i).has("nombre")){
                    item.setNombre(data.getJSONObject(i).getString("nombre"));
                }else{
                    item.setNombre("");
                }
                if(data.getJSONObject(i).has("estado")){
                    item.setEstado(data.getJSONObject(i).getString("estado"));
                }else{
                    item.setEstado("");
                }


                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        PedAdapter adapter=new PedAdapter(datos, VER_PEDIDO.this);
        // ArrayAdapter<String> adapter =new ArrayAdapter(this.getApplicationContext(),android.R.layout.simple_list_item_1,datos);
        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}