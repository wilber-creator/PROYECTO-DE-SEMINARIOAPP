package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_PEDIDOS.EsPedidoMenAd;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_PEDIDOS.PedMeAdapterA;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.PediMApi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_PEDIDOS_MENU_ADMIN extends AppCompatActivity implements onLoadData {
    ListView listares;
    ImageButton atrasVPC;
    TextView _id;
    String id;
    ArrayList<EsPedidoMenAd> datos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos_menu_admi);

        atrasVPC = findViewById(R.id.atrasVPC);
        atrasVPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VER_PEDIDOS_MENU_ADMIN.this, CLIENTE.class));

            }
        });
        _id=(TextView) findViewById(R.id.idPediMe);
        _id.setText( getIntent().getExtras().getString("id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("id");

        listares = findViewById(R.id.lisrestaurantAdmiP);
        PediMApi api= new PediMApi(this);
        api.loadPed(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {

        for (int i = 0; i< data.length() ; i++) {
            //datos.add("item" + i);

            EsPedidoMenAd item= new EsPedidoMenAd();
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
                if(data.getJSONObject(i).has("menus")){
                    item.setMenus(data.getJSONObject(i).getString("menus"));
                }else{
                    item.setMenus("");
                }
                if(data.getJSONObject(i).has("estado")){
                    item.setEstado(data.getJSONObject(i).getString("estado"));
                }else{
                    item.setEstado("");
                }

                if(data.getJSONObject(i).has("cliente")){
                    item.setCliente(data.getJSONObject(i).getString("cliente"));
                }else{
                    item.setCliente("");
                }

                if(data.getJSONObject(i).has("correo")){
                    item.setCorreo(data.getJSONObject(i).getString("correo"));
                }else{
                    item.setCorreo("");

                }

                if(data.getJSONObject(i).has("correoC")){
                    item.setCorreoC(data.getJSONObject(i).getString("correoC"));
                }else{
                    item.setCorreoC("");

                }

                item.setId(data.getJSONObject(i).getString("_id"));




                datos.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        PedMeAdapterA adapter=new PedMeAdapterA(datos, VER_PEDIDOS_MENU_ADMIN.this);

        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}