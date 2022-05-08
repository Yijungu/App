package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ListVIewLayout extends ConstraintLayout {

    private TextView btn;
    public ListVIewLayout(Context context, ListItem item){
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.row_listview,this,true);

        btn = (TextView) findViewById(R.id.search_textview);
        btn.setText(item.getText());
    }

    public void setText(String text){
        btn.setText(text);
    }
}
