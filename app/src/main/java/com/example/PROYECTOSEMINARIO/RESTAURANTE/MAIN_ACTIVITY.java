package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MAIN_ACTIVITY extends AppCompatActivity {
    Button login,Ver_Restaurante;
    Button registrarse;
    Button registrar_restaurante;
    Button menus,registrarMenu;
    static final int code_camera=999;
    private MAIN_ACTIVITY root=this;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_login,R.id.nav_registrar_Usuario,R.id.nav_restaurante)
                .setDrawerLayout(drawer)
                .build();

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

        //ICONO
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setIcon(R.drawable.ic_home_black_24dp);


        Toast.makeText(this, "OnCreate",Toast.LENGTH_LONG).show();
        loadcomponents();
    }

    private void loadcomponents() {
        Ver_Restaurante=this.findViewById(R.id.restaurantes);
        registrarse = findViewById(R.id.register2);
        login=this.findViewById(R.id.login);
        registrar_restaurante=this.findViewById(R.id.Registrar_Restaurante);
        menus=this.findViewById(R.id.menus);
        registrarMenu=this.findViewById(R.id.registroMenus);

        registrarMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root, CREAR_MENU.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });

        menus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root, VER_MENUS.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });


        registrar_restaurante.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registrar_restauranteActivity =new Intent(root, REGISTRAR_RESTAURANTE.class);
                root.startActivity(registrar_restauranteActivity);
            }

        });


        registrarse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Registrar_UsuarioActivity =new Intent(root, REGISTRAR_USUARIO.class);
                root.startActivity(Registrar_UsuarioActivity);
            }

        });


        Ver_Restaurante.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Ver_RestauranteActivity =new Intent(root, VER_RESTAURANTE_1.class);
                root.startActivity(Ver_RestauranteActivity);

            }
        }));
        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginActivity =new Intent(root, LOGIN.class);
                root.startActivity(LoginActivity);

            }
        }));


        /*imagen1.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////CAMARA////
                Intent camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                if(camera.resolveActivity(root.getPackageManager())!=null){
                    root.startActivityForResult(camera,code_camera);
                }
            }
        }));*/
    }
}