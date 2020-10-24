package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import android.os.Bundle;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LOGIN extends AppCompatActivity {
    Button btn_login;
    Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        btn_login = findViewById (R.id.login);
        /*
        registrarse = findViewById(R.id.registrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RegistrarUsuario.class));
                finish();
            }

        });
*/
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
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
                AlertDialog alertDialog = new AlertDialog.Builder(LOGIN.this).create();
                try {

                    //String resp = response.getString("resp");
                    Toast.makeText(LOGIN.this, "respuesta:"+ response.getString("msn"), Toast.LENGTH_SHORT).show();
                    if(statusCode == 200){
                        //JSONObject json=response.getJSONObject("dato");

                        //String token = response.getString("token");
                        /*String tipo= json.getString("tipo");
                        String nombre=json.getString("nombre");
                        String ci=json.getString("ci");
                        String telefono=json.getString("telefono");
                        String correo=json.getString("email");
                        String id=json.getString("_id");*/

                        //Data.TOKEN="Data "+token;
                        //Data.ID_User=id;
                        //Data.Tipo=tipo;
                        //if(tipo.compareTo("Administrador")==0) {
                            /*Intent intent =new Intent(LOGIN.this, Admi1.class);
                            intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                            startActivity(intent);
                            finish();
                        }else{*/
                            /*if(response.getString("msn").compareToIgnoreCase("Credenciales incorrectas")!=0) {
                                Intent intent = new Intent(LOGIN.this, Cliente1.class);
                            /*intent.putExtra("email",correo);
                            intent.putExtra("nombre",nombre);
                            intent.putExtra("ci",ci);
                            intent.putExtra("telefono",telefono);
                            intent.putExtra("tipo",tipo);
                                startActivity(intent);
                                finish();
                                Toast.makeText(LOGIN.this, "Login correctamente: ", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(LOGIN.this, " "+response.getString("msn"), Toast.LENGTH_SHORT).show();
                            */
                        //}



                        //Toast.makeText(LOGIN.this, "Login correctamente: "+ token, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(LOGIN.this, "error de logueo", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(LOGIN.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }


        });
    }

}
