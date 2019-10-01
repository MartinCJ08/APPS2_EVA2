package com.example.eva2_4_sqlite_adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
    Context context;
    Cursor cursor;
    public MyCursorAdapter (Context context, Cursor cursor){
        super(context,cursor);
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.cursor_layout,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtVwShow = view.findViewById(R.id.txtVwShow);
        txtVwShow.setText(cursor.getString(cursor.getColumnIndex("dato")));
    }

}
