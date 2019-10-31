package com.example.eva2_12_api_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = findViewById(R.id.txtData);
    }

    public void onClick(View v){
        new MYSQLAPIConection().execute();
    }

    //Crear la conexión

    class MYSQLAPIConection extends AsyncTask<String,Void,String>{
        final String url = "http://10.1.8.17:3000/Tasks/"; //Cambiar dependiendo de la red
        @Override
        protected String doInBackground(String... strings) {
            String sRes = null;
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
                txtData.setText(s);
            }else{
                txtData.setText("No se pudo realizar la conexión");
            }
        }
    }
}
