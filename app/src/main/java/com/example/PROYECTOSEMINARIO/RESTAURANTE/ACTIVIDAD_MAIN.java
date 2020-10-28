package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ACTIVIDAD_MAIN extends AppCompatActivity {
    Button Ver_Restaurante;
    Button login;
    Button registrarse;
    Button registrar_restaurante;
    Button menus,registrarMenu;
    Button pedido,registrarPedido;
    static final int code_camera=999;
    private ACTIVIDAD_MAIN root=this;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_login,R.id.nav_registrar_Usuario,R.id.nav_restaurante)
                .setDrawerLayout(drawer)
                .build();

        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.drawable.ic_home_black_24dp);


        Toast.makeText(this, "OnCreate",Toast.LENGTH_LONG).show();
        loadcomponents();
    }

    private void loadcomponents() {
        Ver_Restaurante=this.findViewById(R.id.restaurantes);
        login=this.findViewById(R.id.login);
        registrarse=this.findViewById(R.id.registrarse);



        Ver_Restaurante.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root, VER_RESTAURANTES_MAIN.class);
                root.startActivity(Ver_RestauranteActivity);
            }
        }));
        // BUTTON DE LOGIN EN MAIN PRINCIPAL
        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginactivity =new Intent(root, LOGIN.class);
                root.startActivity(loginactivity);

            }
        }));
        // BUTTON DE REGISTRARSE EN MAIN PRINCIPAL
        registrarse.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root, REGISTRAR_USUARIO.class);
                root.startActivity(Ver_RestauranteActivity);

            }
        }));





    }


}