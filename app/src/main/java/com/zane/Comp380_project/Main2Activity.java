package com.zane.Comp380_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    DataBaseHelper mDataBaseHelper;
    String selectedDay;
    ListView eventList;
    ArrayList <Event>events = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = findViewById(R.id.button2);
        eventList =findViewById(R.id.eventlist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDataBaseHelper = new DataBaseHelper(this);

        //receives intent and displays date
        Intent getDate = getIntent();
        final String selectedDate = getDate.getStringExtra("date");
        TextView dateText = findViewById(R.id.date);
        dateText.setText(selectedDate);
        selectedDay = dateText.getText().toString();
        getEvents();


    eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object name = "apple";
            Toast.makeText(Main2Activity.this,name.toString(),Toast.LENGTH_SHORT).show();
        }
    });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent newEvent = new Intent(Main2Activity.this,NewEvent.class);
            newEvent.putExtra("date",selectedDate);
            startActivity(newEvent);
//                finish();
            }
        });


    }
    private void getEvents(){
        Cursor data = mDataBaseHelper.getData();

        while(data.moveToNext()){
                    if(selectedDay.equals(data.getString(1))){
                        events.add(new Event(data.getString(2),data.getString(3),data.getString(4)));
                    }
        }
        ListAdapter adapter = new CustomAdapter(this,events);
        eventList.setAdapter(adapter);

    }




}
