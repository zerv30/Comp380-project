package com.zane.Comp380_project;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private  static final String TAG = "CalendarActivity";
    //  private TextView theDate;
//    ArrayList eventArray =  new ArrayList();
    Button skipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CalendarView calendarView = findViewById(R.id.calendarView);
        skipButton = findViewById(R.id.dateSkip);

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        final String currentDate = (month+1) + "/" + day + "/" + year;
        final String msg = "This feature isn't available at the moment";
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setDate(Calendar.getInstance().getTimeInMillis(),false,true);


            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                // Log.e("abc","123");


                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);

                //Goes to Main2Activity
                Intent selectedDay = new Intent(MainActivity.this, Main2Activity.class);
                selectedDay.putExtra("date",date);

                startActivity(selectedDay);


            }
        });
    }

}



