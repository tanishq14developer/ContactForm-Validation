package com.example.contact_form;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listview extends AppCompatActivity {
    ListView listView;
    Database g = new Database(this);
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.listview);
        populateListView();
    }

    private void populateListView() {
        Cursor t =  g.getdata();
        listItem = new ArrayList<>();
        if (t.getCount()==0)
        {
            Toast.makeText(listview.this,"No Data Found",Toast.LENGTH_SHORT).show();
        }
        while (t.moveToNext()) {

            Log.i("String",t.getString(0));
            listItem.add("Username-->" + t.getString(0)+"\n" + "Email-->" + t.getString(1));
        }

         adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
         listView.setAdapter(adapter);

    }
}