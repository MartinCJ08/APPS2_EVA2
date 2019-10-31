package com.example.eva2_10_object_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtNom, edtTxtApe;
    TextView txtShow;
    RadioButton rdBtn1,rdBtn2,rdBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxtNom = findViewById(R.id.edtTxtNom);
        edtTxtApe = findViewById(R.id.edtTxtApe);
        txtShow = findViewById(R.id.txtShow);
        rdBtn1 = findViewById(R.id.rdBtn1);
        rdBtn1 = findViewById(R.id.rdBtn2);
        rdBtn1 = findViewById(R.id.rdBtn3);
    }

    public void onClick(View v){
        guardar();
        leer();
    }

    public void guardar(){
        int iGen = 0;
        if (rdBtn1.isChecked()){
            iGen = 0;
        }else if(rdBtn2.isChecked()){
            iGen = 1;
        }else if(rdBtn3.isChecked()){
            iGen = 2;
        }
        Personas pObj = new Personas(edtTxtNom.getText().toString(),
                edtTxtApe.getText().toString(),iGen);

        try{
            FileOutputStream fos = openFileOutput("datos.xxx",0);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pObj);
            oos.writeObject(new Personas());
        }catch(FileNotFoundException e){

        }catch(IOException e){}


    }

    public void leer(){
        txtShow.setText("");
        ObjectInputStream ois = null;
        try{
            FileInputStream fis = openFileInput("datos.xxx");
            ois = new ObjectInputStream(fis);
            Personas pObj = (Personas) ois.readObject();
            while(true){
                txtShow.append("Nombre: "+pObj.getNom()+" Apellido:"+pObj.getApe()+" genero:"+pObj.getGenero());
                txtShow.append("------------------------\n");
                pObj = (Personas) ois.readObject();
            }
        }catch(FileNotFoundException e){

        }catch (IOException e){

        }catch (ClassNotFoundException e){

        }finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){}
            }
        }
    }
}

class Personas implements Serializable{
    private String nom;
    private String ape;
    private int genero;

    public Personas(){
        this.nom = "Martin";
        this.ape = "Carrasco";
        this.genero = 0;
    }

    public Personas(String nom, String ape, int genero) {
        this.nom = nom;
        this.ape = ape;
        this.genero = genero;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }
}
