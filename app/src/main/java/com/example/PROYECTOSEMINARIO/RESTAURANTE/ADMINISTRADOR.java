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

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ADMINISTRADOR extends AppCompatActivity {
/*
    public ArrayList<EsCliente> LISTCLIENT;
    public Context context;*/
    Button crearres,veres,VerMenusResAD;
    Button edicuenta1;
    Button elicuenta1,controlar;
    TextView nombre;
    TextView ci;
    TextView telefono;
    TextView email;
    TextView tipo,_id;
    ImageButton atrasAD;
    static String em="";
/*
    public ADMINISTRADOR(ArrayList<EsCliente> data, Context context) {
        LISTCLIENT = data;
        this.context = context;
        // this.listem=listem;


    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        atrasAD = findViewById(R.id.atrasAD);
        atrasAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ADMINISTRADOR.this, ACTIVIDAD_MAIN.class));

            }
        });

        elicuenta1 = findViewById(R.id.elicuenta1);
        crearres =  findViewById(R.id.crestaurant);
        veres =  findViewById(R.id.verestaurant);
     //   VerMenusResAD =  findViewById(R.id.VerMenusResAD);
        edicuenta1 = findViewById(R.id.edicuenta1);
        controlar = findViewById(R.id.irpedidos);



        nombre=(TextView) findViewById(R.id.nombre2);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        ci=(TextView) findViewById(R.id.ci2);
        ci.setText( getIntent().getExtras().getString("ci"));
        telefono=(TextView) findViewById(R.id.telefono2);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        email=(TextView) findViewById(R.id.correo2);
        email.setText( getIntent().getExtras().getString("email"));
        tipo=(TextView) findViewById(R.id.tipo);
        tipo.setText( getIntent().getExtras().getString("tipo"));
        _id=(TextView) findViewById(R.id.idadmi);
        _id.setText( getIntent().getExtras().getString("_id"));

       // _id = this.LISTCLIENT.toString();


        controlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ADMINISTRADOR.this, VER_RESTAURANTE_PEDIDOS_ADMIN.class);
                t.putExtra("_id",_id.getText());
                // t.putExtra("_id",_id);
                startActivity(t);
            }
        });


        crearres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(ADMINISTRADOR.this,REGISTRAR_RESTAURANTE.class));
                Intent t=new Intent(ADMINISTRADOR.this, REGISTRAR_RESTAURANTE.class);
                t.putExtra("email",email.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("ci", ci.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("tipo",tipo.getText());
                t.putExtra("_id",_id.getText());
               // t.putExtra("_id",_id);
                startActivity(t);
            }
        });
        veres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ADMINISTRADOR.this, VER_RESTAURANTE_1.class);
                t.putExtra("_id",_id.getText());
                // t.putExtra("_id",_id);
                startActivity(t);

            }
        });
/*
        VerMenusResAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ADMINISTRADOR.this,VENTANA_MENU_ADMIN.class);
                t.putExtra("_id",_id.getText());
                // t.putExtra("_id",_id);
                startActivity(t);

            }
        });*/
        edicuenta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ADMINISTRADOR.this, EDITAR_ADMINISTRADOR.class);
                t.putExtra("email",email.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("ci", ci.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("tipo",tipo.getText());
                t.putExtra("_id",_id.getText());
                startActivity(t);

                /*Bundle b=getIntent().getExtras();
                Toast.makeText(getApplicationContext(),b.getString("nombreMod")+"",Toast.LENGTH_LONG).show();*/

            }
        });


        elicuenta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
                startActivity(new Intent(ADMINISTRADOR.this, ACTIVIDAD_MAIN.class));

            }
        });



    }

    private void sedData() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.delete(Data.REGISTER_delete+"/"+Data.ID_User, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String message = response.getString("message");
                    if (message != null){
                        Toast.makeText(ADMINISTRADOR.this,message, Toast.LENGTH_LONG).show();


                    }else   {
                        Toast.makeText(ADMINISTRADOR.this,"Error al borrar", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}