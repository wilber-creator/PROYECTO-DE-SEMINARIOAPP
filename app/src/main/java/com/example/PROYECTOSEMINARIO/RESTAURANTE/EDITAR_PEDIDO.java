package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EDITAR_PEDIDO extends AppCompatActivity {
    TextView lugar_envio1,cantidad1,pago_total1;
    String lugar_envioP,cantidadP,pago_totalP,id;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pedido);

        lugar_envio1 = findViewById(R.id.luegar_envioEP);
        cantidad1 = findViewById(R.id.cantidadEP);

        informacion();
        guardar = findViewById(R.id.guardarpedidoDP);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();
            }
        });
    }

    public void informacion() {
        Bundle intent = getIntent().getExtras();

        lugar_envioP = intent.getString("lugar_envio");
        cantidadP = intent.getString("cantidad");

        id = intent.getString("id");

        lugar_envio1.setText(lugar_envioP);
        cantidad1.setText(cantidadP);

    }
    public void sedData() {
        TextView   lugar_envio4 = findViewById(R.id.luegar_envioEP);
        TextView cantidad4 = findViewById(R.id.cantidadEP);


        AsyncHttpClient client = new AsyncHttpClient();



        RequestParams params = new RequestParams();

        params.put("lugar_envio", lugar_envio4.getText().toString());
        params.put("cantidad", cantidad4.getText().toString());


        client.patch(Data.REGISTER_ORDEN+"?id="+id ,params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent pruebaED=new Intent(EDITAR_PEDIDO.this, VER_PEDIDO.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }



            }


        });
    }

}