package com.example.eva2_8_files_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtShow  = findViewById(R.id.txtShow);
        txtShow.setText("");

        InputStream is = getResources().openRawResource(R.raw.mi_text);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        try{
            String sVal;
            while ((sVal = br.readLine()) != null){
                txtShow.append(sVal);
                txtShow.append("\n");
            }
            br.close();
        }catch (IOException e){}
    }
}
