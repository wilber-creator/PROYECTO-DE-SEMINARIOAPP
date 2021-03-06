package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class INFO_PEDIDOS extends AppCompatActivity {
    ImageView image1;
    TextView lugar_envioVPAD,cantidadVPAD;
    String titulo,descripcion,img,_id,ESTADO;
    RadioGroup group;
    RadioButton rb1,rb2,rb3;
    Button guardarIPAD;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pedidos);


        lugar_envioVPAD=(TextView) findViewById(R.id.lugar_envioVPAD);
        lugar_envioVPAD.setText( getIntent().getExtras().getString("lugar_envio"));
        cantidadVPAD=(TextView) findViewById(R.id.cantidadVPAD);
        cantidadVPAD.setText( getIntent().getExtras().getString("cantidad"));




        rb1=(RadioButton)findViewById(R.id.enviado);
        rb2=(RadioButton)findViewById(R.id.proceso);
        rb3=(RadioButton)findViewById(R.id.espera);

        group = findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.enviado){
                    Toast.makeText(getApplicationContext(), "enviado", Toast.LENGTH_SHORT).show();
                    result="enviado";

                }
                if (checkedId == R.id.proceso){
                    Toast.makeText(getApplicationContext(), "proceso", Toast.LENGTH_SHORT).show();
                    result="proceso";
                }
                if (checkedId == R.id.espera){
                    Toast.makeText(getApplicationContext(), "espera", Toast.LENGTH_SHORT).show();
                }
            }


        });



        int radioid= group.getCheckedRadioButtonId();
        rb1=findViewById(radioid);

        guardarIPAD = findViewById(R.id.guardarIPAD);
        guardarIPAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sedData();

            }
        });

    }

    public void informacion(){
        Intent intent = getIntent();
        titulo = intent.getStringExtra("lugar_envio");
        descripcion = intent.getStringExtra("cantidad");


    }
    public void sedData() {
        TextView  lugar_envioVPAD = findViewById(R.id.lugar_envioVPAD);
        TextView cantidadVPAD = findViewById(R.id.cantidadVPAD);




        Bundle intent = getIntent().getExtras();
        _id = intent.getString("id");

        AsyncHttpClient client = new AsyncHttpClient();



        RequestParams params = new RequestParams();


        params.put("estado",result);



        client.patch(Data.REGISTER_ORDEN+"?id="+_id, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                try {
                    String res=response.getString("msn");
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                    Intent  pruebaED=new Intent(INFO_PEDIDOS.this, LOGIN.class);
                    startActivity(pruebaED);
                } catch (JSONException e) {

                    e.printStackTrace();
                }




            }


        });
    }



}