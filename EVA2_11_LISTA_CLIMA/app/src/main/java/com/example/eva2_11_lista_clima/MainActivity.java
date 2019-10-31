package com.example.eva2_11_lista_clima;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements ListView.OnItemClickListener {
    ListView lstVwCiudades;
    static Clima[] acClimaCd = {
            new Clima(0,"delicias","Despejado","Seco y polvoriento",17),
            new Clima(0,"Entre rios","Soleado","Despejado",24),
            new Clima(0,"Cuauhtemoc","Lluvioso","Nublado con intervalos",22),
            new Clima(0,"Meoqui","Nevado","Cayendo nievecita",-2),
            new Clima(0,"Saucillo","Mucho viento","Creeemos que hay un tornado",14),
            new Clima(0,"Camargo","Tormenta electrico","Thor esta enojado",8.7),
            new Clima(0,"Jimenez","Nublado","Hay una que otra nube",23),
            new Clima(0,"Silent Hill","Neblina","Creo que ya perdí una pierna",666)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase("myfriendsDB",
                MODE_PRIVATE,
                null);
        try{
            db.execSQL("create table clima(id int, ciudad text, clima text, desc_clima text, temperatura double, idImg int)");
        }catch(SQLException e){
            e.printStackTrace();
        }
        db.execSQL("insert into clima values ('El martin')");
        lstVwCiudades = findViewById(R.id.lstVwCiudades);
        lstVwCiudades.setAdapter(
                new ClimaAdapter(this,R.layout.layout_clima,acClimaCd)
        );
        lstVwCiudades.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Dialog dlgMyDialog;
        dlgMyDialog = new Dialog(this);
        dlgMyDialog.setContentView(R.layout.layout_dialogo);

        ImageView imgVwClima;
        TextView txtVwCiudad,txtVwTemp,txtVwClima,txtVwDesc;
        Button btnOK;

        imgVwClima = dlgMyDialog.findViewById(R.id.imgVwClima);
        txtVwCiudad = dlgMyDialog.findViewById(R.id.txtVwCiudad);
        txtVwTemp = dlgMyDialog.findViewById(R.id.txtVwTemp);
        txtVwClima = dlgMyDialog.findViewById(R.id.txtVwClima);
        txtVwDesc = dlgMyDialog.findViewById(R.id.txtVwDesc);
        btnOK = dlgMyDialog.findViewById(R.id.btnOK);

//        imgVwClima.setImageResource(acClimaCd[i].getImagen_clima());
        txtVwCiudad.setText(acClimaCd[i].getCiudad());
        txtVwTemp.setText(acClimaCd[i].getTemperatura()+"*C");
        txtVwClima.setText(acClimaCd[i].getClima());
        txtVwDesc.setText(acClimaCd[i].getDesc_clima());

        btnOK.setText("OK");
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgMyDialog.dismiss();
            }
        });

        dlgMyDialog.show();
    }
}
