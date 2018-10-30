package com.zane.Comp380_project;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  static final String TAG = "CalendarActivity";
    //  private TextView theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
                // Log.e("abc","123");


                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);

                //Goes to Main2Activity
                Intent event = new Intent(MainActivity.this, Main2Activity.class);
                event.putExtra("date",date);

                startActivity(event);


            }
        });
    }
}



