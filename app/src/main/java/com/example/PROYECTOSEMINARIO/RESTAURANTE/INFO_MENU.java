package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class INFO_MENU extends AppCompatActivity {
    Button crear,ver,edit,delete;
    ImageButton atrasIMVC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__menus_c);

        atrasIMVC = findViewById(R.id.atrasIMVC);
        atrasIMVC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(INFO_MENU.this, VER_MENUS_CLIENTE.class));

            }
        });
        final TextView nombre=(TextView) findViewById(R.id.nombreIMVC);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView precio=(TextView) findViewById(R.id.precioIMVC);
        precio.setText( getIntent().getExtras().getString("precio"));
        final TextView descripcion=(TextView) findViewById(R.id.descripcionIMVC);
        descripcion.setText( getIntent().getExtras().getString("descripcion"));




        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");
    }

}