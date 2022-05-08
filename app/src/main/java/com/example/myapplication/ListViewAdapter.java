package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<ListItem> listItem = new ArrayList<ListItem>();

    public ListViewAdapter(Context context){
        mContext = context;
    }

    public void addItem(ListItem item){
        listItem.add(item);
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListVIewLayout itemView;
        if(view == null){
            itemView = new ListVIewLayout(mContext, listItem.get(i));
        }else{
            itemView = (ListVIewLayout) view;
            itemView.setText(listItem.get(i).getText());
        }

        return itemView;
    }
}
