package com.example.myapplication;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Searchable extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private SearchAdapter adapter;
    private EditText editSearch;
    private ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);

        editSearch = (EditText) findViewById(R.id.search_edittext);
        listView = (ListView) findViewById(R.id.search_view);

        list = new ArrayList<String>();
        settingList();

        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);
        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });
    }

    public void search(String charText){

        list.clear();

        if(charText.length() == 0){
            list.addAll(arraylist);
        }
        else{
            for(int i = 0; i < arraylist.size(); i++){
                if(arraylist.get(i).toLowerCase().contains(charText)){
                    list.add(arraylist.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void settingList() {
        list.add("?????????");
        list.add("?????????");
        list.add("??????");
        list.add("?????????");
        list.add("?????????");
        list.add("????????????");
        list.add("?????????");
        list.add("?????????");
        list.add("?????????");
        list.add("??????");
        list.add("??????");
        list.add("??????");
        list.add("?????????");
        list.add("?????????");
        list.add("??????");
        list.add("??????");
        list.add("?????????");
        list.add("??????");
        list.add("??????");
        list.add("?????????");
        list.add("??????");
        list.add("?????????");
        list.add("??????");
        list.add("?????????");
        list.add("??????");
        list.add("??????");
        list.add("?????????");

    }
}
