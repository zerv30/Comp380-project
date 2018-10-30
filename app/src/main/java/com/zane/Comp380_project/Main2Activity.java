package com.zane.Comp380_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.CalendarView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //receives intent and displays date
        Intent getevent = getIntent();
        String date = getevent.getStringExtra("date");
        TextView theDate = (TextView) findViewById(R.id.date);
        theDate.setText(date);

    }
}
