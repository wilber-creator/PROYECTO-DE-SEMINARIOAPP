package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class INFO_REST_CA extends AppCompatActivity {
    Button crear,ver,edit,delete;
    ImageButton atrasIRC2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__restaurante_c);
        crear = findViewById(R.id.crearmenuIR);
        ver = findViewById(R.id.vermenuIR);
        edit = findViewById(R.id.editarmenuIR);
        delete = findViewById(R.id.elimenuIR);


        atrasIRC2 = findViewById(R.id.atrasIRC2);
        atrasIRC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_REST_CA.this, VER_RESTAURANTE_CLIENTE.class));

            }
        });

        final TextView nombre=(TextView) findViewById(R.id.nombreIRV);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView calle=(TextView) findViewById(R.id.calleIRV);
        calle.setText( getIntent().getExtras().getString("calle"));
        final TextView telefono=(TextView) findViewById(R.id.telefonoIRV);
        telefono.setText( getIntent().getExtras().getString("telefono"));

        final TextView propietario=(TextView) findViewById(R.id.propertyIRV);
        propietario.setText( getIntent().getExtras().getString("propietario"));


        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");

       /*
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_RESTAURANTE.this,CREAR_MENU.class));

            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_RESTAURANTE.this,VER_MENIU.class));

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_RESTAURANTE.this,EDITAR_MENU.class));
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_RESTAURANTE.this,ELIMINAR_MENU.class));

            }
        });*/
    }
}