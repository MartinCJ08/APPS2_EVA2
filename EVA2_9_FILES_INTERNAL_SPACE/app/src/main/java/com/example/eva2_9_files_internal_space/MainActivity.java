package com.example.eva2_9_files_internal_space;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView txtShow;
    EditText edtTxtData;
    final String FILE_NAME  = "mi_archivo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtShow = findViewById(R.id.txtShow);
        edtTxtData = findViewById(R.id.edtTxtData);
        txtShow.setText("");

        try {
            String sVal;
            InputStream is = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while((sVal = br.readLine())!=null){
                txtShow.append(sVal);
                txtShow.append("\n");
            }
        }catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    public void onClick(View v){
        try {
            String[] sVals = edtTxtData.getText().toString().split("\n");
            OutputStream os = openFileOutput(FILE_NAME,0);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            for (int i = 0; i<sVals.length;i++){
                bw.append(sVals[i]);
                bw.append("\n");
            }
        }catch (FileNotFoundException e){}
        catch (IOException e){}

    }
}
