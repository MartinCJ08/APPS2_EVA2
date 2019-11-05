package com.example.eva2_12_api_mysql;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<JSONObject> {
    private Context context;
    private  int layout;
    private List<JSONObject> objects;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<JSONObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView txtProd;
        TextView txtPrice;

        if(convertView == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            convertView =layoutInflater.inflate(layout,parent,false);
        }

        txtProd= convertView.findViewById(R.id.txtProd);
        txtPrice = convertView.findViewById(R.id.txtPrice);

        try {
            txtProd.setText("Product: "+objects.get(position).getString("productname"));
            txtPrice.setText("Price:"+objects.get(position).getString("unitprice"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.getView(position, convertView, parent);
    }
}
