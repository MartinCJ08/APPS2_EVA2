package com.example.eva2_12_api_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtData;
    ListView lstJSON;
    ArrayList<JSONObject> miLista = new ArrayList<JSONObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstJSON = findViewById(R.id.lstJSON);

//        txtData = findViewById(R.id.txtData);
    }

    public void onClick(View v){
        new MYSQLAPIConection().execute();
    }

    //Crear la conexión

    class MYSQLAPIConection extends AsyncTask<String,Void,String>{
        final String url = "http://192.168.43.227:3000/Tasks"; //Cambiar dependiendo de la red
        String sRes = null;
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL ruta = new URL(url);
                HttpURLConnection httpCon = (HttpURLConnection) ruta.openConnection();

                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
                    BufferedReader br =  new BufferedReader(isr);
                    sRes = br.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return sRes;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s != null){
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsProduct = jsonArray.getJSONObject(i);
//                        txtData.append("Product name: "+jsProduct.getString("productname"));
//                        txtData.append("Unit price: "+jsProduct.getString("unitprice"));
                        miLista.add(jsProduct);
                    }

                    lstJSON.setAdapter(new MyAdapter(MainActivity.this,R.layout.layout_msg, miLista));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                txtData.setText(s);
            }else{
                txtData.setText("No se pudo realizar la conexión");
            }
        }
    }
}
