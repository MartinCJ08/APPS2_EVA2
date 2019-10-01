package com.example.eva2_4_sqlite_adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqDB;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList = findViewById(R.id.myList);

        sqDB = openOrCreateDatabase("db_adapter",MODE_PRIVATE,null);
        try {
            sqDB.execSQL("CREATE TABLE datos(id INTEGER PRIMARY KEY AUTOINCREMENT, dato TEXT)");
        }catch (SQLException e){
            e.printStackTrace();
        }

        sqDB.execSQL("insert into datos(dato) values ('Montados La juta')");
        sqDB.execSQL("insert into datos(dato) values ('Samuelitos')");
        sqDB.execSQL("insert into datos(dato) values ('Olayos')");
        sqDB.execSQL("insert into datos(dato) values ('El granero')");
        sqDB.execSQL("insert into datos(dato) values ('La viuda negra')");
        sqDB.execSQL("insert into datos(dato) values ('Buffalucas')");
        sqDB.execSQL("insert into datos(dato) values ('El escuadron')");
        sqDB.execSQL("insert into datos(dato) values ('Burker king')");
        sqDB.execSQL("insert into datos(dato) values ('KFC')");
        sqDB.execSQL("insert into datos(dato) values ('Ventum')");
        sqDB.execSQL("insert into datos(dato) values ('Pollo feliz')");
        sqDB.execSQL("insert into datos(dato) values ('Tortas piloin')");
        sqDB.execSQL("insert into datos(dato) values ('Gorditas Lily')");

        Cursor cursor = sqDB.rawQuery("select id as _id, dato from datos order by dato",null);

        myList.setAdapter(new MyCursorAdapter(this,cursor));


    }
}
