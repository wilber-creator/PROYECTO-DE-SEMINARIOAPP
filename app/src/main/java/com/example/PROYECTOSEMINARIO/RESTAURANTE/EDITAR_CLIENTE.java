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

public class EDITAR_CLIENTE extends AppCompatActivity {
    TextView nombre1,ci1,phone1,correo1,_id1;
    String nombrecli,cicli,phonecli,correocli,_id;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__cliente);


        nombre1 = findViewById(R.id.nombreEC);
        ci1 = findViewById(R.id.ciEC);
        phone1 = findViewById(R.id.phoneEC);
        correo1 = findViewById(R.id.correoEC);

        informacion();
        guardar = findViewById(R.id.guardarEC);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();
            }
        });
    }

    public void informacion() {
        Bundle intent = getIntent().getExtras();

        nombrecli = intent.getString("nombre");
        cicli = intent.getString("ci");
        phonecli = intent.getString("telefono");
        correocli = intent.getString("email");


        nombre1.setText(nombrecli);
        ci1.setText(cicli);
        phone1.setText(phonecli);
        correo1.setText(correocli);

    }
    public void sedData() {

        TextView  nombre4 = findViewById(R.id.nombreEC);
        TextView ci4 = findViewById(R.id.ciEC);
        TextView phone4 = findViewById(R.id.phoneEC);
        TextView correo4 = findViewById(R.id.correoEC);

        AsyncHttpClient client = new AsyncHttpClient();


        Bundle intent = getIntent().getExtras();
        _id = intent.getString("_id");

        RequestParams params = new RequestParams();

        params.put("nombre", nombre4.getText().toString());
        params.put("ci", ci4.getText().toString());
        params.put("telefono", phone4.getText().toString());
        params.put("email", correo4.getText().toString());

        client.patch(Data.REGISTER_CLIENTE+"?id="+_id ,params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent  pruebaED=new Intent(EDITAR_CLIENTE.this, ACTIVIDAD_MAIN.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }



            }


        });
    }

}