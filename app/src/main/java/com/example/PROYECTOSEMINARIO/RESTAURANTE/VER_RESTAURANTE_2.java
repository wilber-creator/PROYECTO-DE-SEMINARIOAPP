package com.example.PROYECTOSEMINARIO.RESTAURANTE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collects1.ResAdapter2;
import collects1.Restaurants;
import cz.msebera.android.httpclient.Header;

public class VER_RESTAURANTE_2 extends AppCompatActivity {
    ListView listares;
    ArrayList<Restaurants> restaurants=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurante_2);
        listares = findViewById(R.id.lisrestaurant2);
        loadComponents();
    }

    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.100.102:8000/api/1.0/restaurante",  new JsonHttpResponseHandler(){


            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("msn");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("name"));
                        res.setTelefono(object.getInt("phone"));
                        res.setCalle(object.getString("street"));
                        res.setImagen(object.getString("picture"));

                        restaurants.add(res);
                    }
                    ResAdapter2 adapter =  new ResAdapter2(VER_RESTAURANTE_2.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });

    }
}