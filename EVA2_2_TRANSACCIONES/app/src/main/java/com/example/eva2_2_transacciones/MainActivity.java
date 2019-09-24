package com.example.eva2_2_transacciones;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqDB = openOrCreateDatabase("mi_bd", MODE_PRIVATE, null);
        try {
            sqDB.execSQL("CREATE TABLE prueba(" +
                    "id INTEGER primary key autoincrement,algo text)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqDB.beginTransaction();
        try {
            sqDB.execSQL("insert into prueba(algo) values('xxx')");
            sqDB.execSQL("insert into prueba(algo) values('yyy')");
            sqDB.execSQL("insert into prueba(algo) values('zzz')");
            //int i = 1 / 0;
            sqDB.execSQL("insert into prueba(algo) values('aaa')");
            sqDB.execSQL("insert into prueba(algo) values('bbb')");
            sqDB.execSQL("insert into prueba(algo) values('ccc')");
            sqDB.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            sqDB.endTransaction();
        }
        String[] args = {"Martin"};
        Cursor cursor = sqDB.rawQuery("select * from prueba where algo = ?", args);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.wtf("cursor",cursor.getString(cursor.getColumnIndex("algo")));
            cursor.moveToNext();
        }
    }
}
