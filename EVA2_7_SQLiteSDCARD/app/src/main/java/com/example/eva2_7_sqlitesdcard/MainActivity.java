package com.example.eva2_7_sqlitesdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TextView txtPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPath = findViewById(R.id.txtPath);
        String sRuta = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this,sRuta,Toast.LENGTH_SHORT).show();
        txtPath.setText(sRuta);
        String sDir = "eva2_7_sqlite_sdcard";
        String sdb = "prueba";
        String sPath = sRuta+"/"+sDir+"/"+sdb;
        File fRuta = new File(sRuta+"/"+sDir+"/");
        if(!fRuta.exists()){
            fRuta.mkdir(); //PRIEMARA VEZ; CREAMOR LA RUTA
        }
        SQLiteDatabase sDB = SQLiteDatabase.openDatabase(sPath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
    }
}
