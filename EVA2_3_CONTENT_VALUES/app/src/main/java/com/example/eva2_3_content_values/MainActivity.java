package com.example.eva2_3_content_values;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqDB;
    EditText edtTxtNom;
    EditText edtTxtApe;
    TextView txtVwShow;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtNom = findViewById(R.id.edTxtNom);
        edtTxtApe = findViewById(R.id.edtTxtApe);
        txtVwShow = findViewById(R.id.txtVwShow);

        sqDB = openOrCreateDatabase("db_prueba",MODE_PRIVATE,null);

        try {
            sqDB.execSQL("CREATE TABLE datos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ape TEXT)");
        }catch (SQLException e){
            e.printStackTrace();
        }


        cursor= sqDB.rawQuery("select * from datos",null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            txtVwShow.append(cursor.getString(cursor.getColumnIndex("nombre")));
            txtVwShow.append(cursor.getString(cursor.getColumnIndex("ape"))+" \n");
            cursor.moveToNext();
        }
    }

    public void onClick(View v){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", edtTxtNom.getText().toString());
        contentValues.put("ape", edtTxtApe.getText().toString());
        sqDB.insert("datos",null,contentValues);

        cursor = sqDB.rawQuery("select * from datos",null);
        cursor.moveToLast();
        txtVwShow.append(cursor.getString(cursor.getColumnIndex("nombre")));
        txtVwShow.append(" ");
        txtVwShow.append(cursor.getString(cursor.getColumnIndex("ape")));
        txtVwShow.append("\n");
        //contentValues.clear(); Cunado quieres limpiar el content
    }
}
