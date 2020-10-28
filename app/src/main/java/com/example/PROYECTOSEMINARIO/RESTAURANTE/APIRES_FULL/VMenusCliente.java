package com.example.PROYECTOSEMINARIO.RESTAURANTE.APIRES_FULL;

import com.example.PROYECTOSEMINARIO.RESTAURANTE.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class VMenusCliente {
    private onLoadData interfaceevent;
    private AsyncHttpClient client;
    public VMenusCliente(onLoadData interfaceevent){
        this.interfaceevent=interfaceevent;
        client =new AsyncHttpClient();

    }
    public void loadMenu(String dato){



        client.get(Data. REGISTER_MENUS+"/"+dato,new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                interfaceevent.onJsonArrayLoad(response);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                interfaceevent.onFailure(statusCode,headers,throwable,errorResponse);
            }
        });


    }
}
