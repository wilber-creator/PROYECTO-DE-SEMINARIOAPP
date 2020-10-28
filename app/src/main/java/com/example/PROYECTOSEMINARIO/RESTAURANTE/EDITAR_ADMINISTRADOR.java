package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EDITAR_ADMINISTRADOR extends AppCompatActivity {
    Button guardar1;
String _id;
    ImageButton atrasEAD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_admin_1);

        atrasEAD = findViewById(R.id.atrasEAD);
        atrasEAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EDITAR_ADMINISTRADOR.this, ADMINISTRADOR.class));

            }
        });

        final TextView nombre=(TextView) findViewById(R.id.nombre4);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView ci=(TextView) findViewById(R.id.ci4);
        ci.setText( getIntent().getExtras().getString("ci"));
        final TextView telefono=(TextView) findViewById(R.id.phone4);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView email=(TextView) findViewById(R.id.correo4);
        email.setText( getIntent().getExtras().getString("email"));
        final TextView tipo=(TextView) findViewById(R.id.tipo);



        guardar1 = findViewById(R.id.guardar1);
        guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();

            }
        });
    }

    public void sedData() {
        TextView  nombre4 = findViewById(R.id.nombre4);
        TextView ci4 = findViewById(R.id.ci4);
        TextView telefono4 = findViewById(R.id.phone4);
        TextView email4 = findViewById(R.id.correo4);

        Bundle intent = getIntent().getExtras();
        _id = intent.getString("_id");

        AsyncHttpClient client = new AsyncHttpClient();



        RequestParams params = new RequestParams();

        params.put("nombre", nombre4.getText().toString());
        params.put("ci", ci4.getText().toString());
        params.put("telefono", telefono4.getText().toString());
        params.put("email", email4.getText().toString());


        client.patch(Data.REGISTER_CLIENTE+"?id="+_id, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent  pruebaED=new Intent(EDITAR_ADMINISTRADOR.this, LOGIN.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }


        });
    }


}