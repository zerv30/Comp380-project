package com.zane.Comp380_project;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    int count= 0;
    DataBaseHelper mDataBaseHelper;
    String selectedDay;
    ListView eventList;
//    ArrayList <String>list = new ArrayList<>();
ArrayList <String>list = new ArrayList<>();
    ArrayList <String>description = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = findViewById(R.id.button2);
        eventList =findViewById(R.id.eventlist);

        mDataBaseHelper = new DataBaseHelper(this);

        //receives intent and displays date
        Intent getDate = getIntent();
        final String selectedDate = getDate.getStringExtra("date");
        TextView dateText = findViewById(R.id.date);
        dateText.setText(selectedDate);
        selectedDay = dateText.getText().toString();
        getEvents();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            adapter.add(count);
//            count++;
            Intent newEvent = new Intent(Main2Activity.this,NewEvent.class);
            newEvent.putExtra("date",selectedDate);
            startActivity(newEvent);
                finish();
//                startActivity(getIntent());

            }
        });

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(getApplicationContext(),list.get(position),Toast.LENGTH_SHORT).show();


            }

        });


    }
    private void getEvents(){
        Cursor data = mDataBaseHelper.getData();

        while(data.moveToNext()){
//            Toast.makeText(this,data.getString(0)+"apple",Toast.LENGTH_SHORT).show();

                    if(selectedDay.equals(data.getString(1))){
                        list.add(data.getString(1));
                        description.add(data.getString(2));
                    }
//           Toast.makeText(this,"welpppp",Toast.LENGTH_SHORT).show();
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,description);
        eventList.setAdapter(adapter);
//       Toast.makeText(this,"welpppp",Toast.LENGTH_SHORT).show();

    }
}
