package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import android.content.Intent;
import android.os.Bundle;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LOGIN_ADMIN extends AppCompatActivity {
    Button btn_login;
    Button registrarse;
    ImageButton atraslogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        atraslogin = findViewById(R.id.atraslogin);
        atraslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LOGIN_ADMIN.this, ACTIVIDAD_MAIN.class));

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn_login = findViewById (R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();

            }

        });

    }

    private void sendData() {
        TextView correo  = findViewById(R.id.correoL);
        TextView password  = findViewById(R.id.passwordL);
        if (correo.getText().toString().equals(" ")  || password.getText().toString().equals("")){
            Toast.makeText(this,"Es necesario llenar los dos campos",Toast.LENGTH_SHORT).show();
            return;

        }
        AsyncHttpClient login = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("email", correo.getText().toString());
        params.add("password", password.getText().toString());
        login.post(Data.REGISTER_LOGIN, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                AlertDialog alertDialog = new AlertDialog.Builder(LOGIN_ADMIN.this).create();
                try {

                    int resp = response.getInt("resp");
                    Toast.makeText(LOGIN_ADMIN.this, "respuesta:"+resp, Toast.LENGTH_SHORT).show();
                    if(resp==200){
                        JSONObject json=response.getJSONObject("dato");

                        //String token = response.getString("token");
                        String tipo= json.getString("tipo");
                        String nombre=json.getString("nombre");
                        String ci=json.getString("ci");
                        String telefono=json.getString("telefono");
                        String correo=json.getString("email");
                        String id=json.getString("_id");

                        //Data.TOKEN="Data "+token;
                        Data.ID_User=id;
                        Data.Tipo=tipo;
                        if(tipo.compareTo("Administrador")==0) {
                            Intent intent =new Intent(LOGIN_ADMIN.this, ADMINISTRADOR.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            intent.putExtra("_id",id);
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent =new Intent(LOGIN_ADMIN.this, CLIENTE.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            intent.putExtra("_id",id);
                            startActivity(intent);
                            finish();
                        }




                        Toast.makeText(LOGIN_ADMIN.this, "Login correctamente: ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LOGIN_ADMIN.this, "error de logueo", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(LOGIN_ADMIN.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        });
    }


}