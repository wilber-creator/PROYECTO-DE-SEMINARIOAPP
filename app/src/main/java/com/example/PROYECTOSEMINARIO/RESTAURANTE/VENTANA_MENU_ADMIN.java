package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class VENTANA_MENU_ADMIN extends AppCompatActivity {
    Button CrearMenuAD,VerMenuAD;

    TextView nombre;
    TextView ci;
    TextView telefono;
    TextView calle;
    TextView tipo,_id;
    ImageButton atrasAD;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_menus_admi);

        atrasAD = findViewById(R.id.atrasAD);
        atrasAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VENTANA_MENU_ADMIN.this, VER_RESTAURANTE_ADMIN.class));

            }
        });

        CrearMenuAD =  findViewById(R.id.CrearMenuAD);
        VerMenuAD =  findViewById(R.id.VerMenuAD);


        nombre=(TextView) findViewById(R.id.nombreRAD);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        telefono=(TextView) findViewById(R.id.telefonoRAD);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        calle=(TextView) findViewById(R.id.calleRAD);
        calle.setText( getIntent().getExtras().getString("calle"));
        _id=(TextView) findViewById(R.id.idrestau);
        _id.setText( getIntent().getExtras().getString("id"));




        CrearMenuAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent t=new Intent(VENTANA_MENU_ADMIN.this, CREAR_MENU.class);
                t.putExtra("calle",calle.getText());
                t.putExtra("nombre",nombre.getText());
                t.putExtra("telefono",telefono.getText());
                t.putExtra("_id",_id.getText());


                startActivity(t);
            }
        });
        VerMenuAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(VENTANA_MENU_ADMIN.this, VER_MENUS_ADMIN.class);

                t.putExtra("_id",_id.getText());
                startActivity(t);

            }
        });





    }

}