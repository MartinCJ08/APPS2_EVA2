package com.example.eva2_1_sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase("myfriendsDB",
                MODE_PRIVATE,
                null);
        db.execSQL("create table hola(id int, nombre text)");
        db.execSQL("insert into hola values (1, 'El martin')");
    }
}
