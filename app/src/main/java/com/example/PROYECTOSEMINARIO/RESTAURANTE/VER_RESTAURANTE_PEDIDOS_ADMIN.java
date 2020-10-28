package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_PEDIDOS_ADD.EsPADRestaurante;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_VER_PEDIDOS_ADD.ResPeAdAdapter;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.ResApi;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL.onLoadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VER_RESTAURANTE_PEDIDOS_ADMIN extends AppCompatActivity implements onLoadData {
    ListView listares;
    String id;
    ImageButton atrasVR;
    TextView _idC;
    ArrayList<EsPADRestaurante> datos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurante_pedidos_admi);

        listares = findViewById(R.id.lisrestaurantPAD2);

        _idC=(TextView) findViewById(R.id.idADresP);
        _idC.setText( getIntent().getExtras().getString("_id"));

        Bundle intent = getIntent().getExtras();
        id = intent.getString("_id");

        ResApi api= new ResApi(this);
        //api.loadRes();
        api.loadRes(id);
    }

    @Override
    public void onJsonLoad(JSONObject data) {

    }

    @Override
    public void onJsonArrayLoad(JSONArray data) {

        for (int i = 0; i< data.length() ; i++) {


            EsPADRestaurante item= new EsPADRestaurante();
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
        ResPeAdAdapter adapter=new ResPeAdAdapter(datos, VER_RESTAURANTE_PEDIDOS_ADMIN.this);

        listares.setAdapter(adapter);

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

    }
}