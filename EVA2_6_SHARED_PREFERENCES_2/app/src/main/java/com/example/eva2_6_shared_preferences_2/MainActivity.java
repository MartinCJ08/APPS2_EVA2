package com.example.eva2_6_shared_preferences_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Intent inSecond;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inSecond = new Intent(this,SecondActivity.class);
        txtData = findViewById(R.id.txtData);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String opcion1 = sp.getString("opcion_1", "");
        boolean opcion2 = sp.getBoolean("opcion_2",false);
        boolean opcion3 = sp.getBoolean("opcion_3",false);
        Toast.makeText(this,"1:"+opcion1+" 2:"+opcion2+" 3:"+opcion3,Toast.LENGTH_SHORT).show();
    }


}
