package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.ADAPTADOR_2.EsMenu;
import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CREAR_MENU extends AppCompatActivity {
    Button aceptar,foto;

    EditText producto,precio,descripcion;
    ImageView imagen;
    TextView _id;
    ListView listcrear;
    ArrayList<EsMenu> list_data = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_menu);
        foto = findViewById (R.id.tomarfotoCM);
        producto = findViewById(R.id.productoCM);
        precio = findViewById(R.id.precioproductoCM);
        descripcion = findViewById(R.id.descripcionCM);
        aceptar = findViewById (R.id.aceptarCM);

        _id=(TextView) findViewById(R.id.idRestauM);
        _id.setText( getIntent().getExtras().getString("_id"));


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sedData();
            }
        });




    }



    public void sedData(){
        final EditText nombre  = findViewById(R.id.productoCM);
        final EditText precio  = findViewById(R.id.precioproductoCM);
        final EditText descripcion = findViewById(R.id.descripcionCM);



        if (nombre.getText().toString().equals("") || precio.getText().toString().equals("") || descripcion.getText().toString().equals("")){
            Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();
            return;
        }



        AsyncHttpClient client = new AsyncHttpClient();



        RequestParams params = new RequestParams();


        params.put("nombre", nombre.getText().toString());
        params.put("precio", precio.getText().toString());
        params.put("descripcion",descripcion.getText().toString());
        params.put("restaurante",_id.getText().toString());

        client .post(Data.REGISTER_MENUS, params, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {




                AlertDialog alertDialog = new AlertDialog.Builder(CREAR_MENU.this).create();
                try {





                    String id = response.getString("id");
                    Data.ID_RESTORANT = id;
                    String msn = response.getString("msn");
                    alertDialog.setTitle("REGISTRO EXITOSAMENTE");
                    alertDialog.setMessage(msn);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ACEPTAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });
    }
}