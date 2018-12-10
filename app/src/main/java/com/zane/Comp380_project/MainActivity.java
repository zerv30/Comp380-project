package com.zane.Comp380_project;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.applandeo.materialcalendarview.*;
import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;
import static android.graphics.Typeface.*;
import static android.widget.Toast.LENGTH_SHORT;
import static com.zane.Comp380_project.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
    private  static final String TAG = "CalendarActivity";
    DataBaseHelper db;
    CalendarView calendarView;
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db = new DataBaseHelper(MainActivity.this);
        calendarView = findViewById(R.id.calendarView);
        getEvents();
        notificationManager = NotificationManagerCompat.from(this);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("hello")
                .setContentText("hi there")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);




            calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                int month = clickedDayCalendar.get(Calendar.MONTH) + 1;
                int day = clickedDayCalendar.get(Calendar.DAY_OF_MONTH);
                int year = clickedDayCalendar.get(Calendar.YEAR);
                String date= month + "/" + day + "/" + year;
                Toast.makeText(getApplicationContext(),date ,Toast.LENGTH_SHORT).show();
                Intent selectedDay = new Intent(MainActivity.this, Main2Activity.class);
                selectedDay.putExtra("date",date);
                startActivityForResult(selectedDay,1);
            }
        });



    }
    private void getEvents(){
        Cursor data = db.getData();
        List<EventDay> apple = new ArrayList<>();
        while(data.moveToNext()){
            Calendar currentDay = Calendar.getInstance();
            String date = data.getString(1);
            String dateSplitter[] = date.split("/");
            currentDay.set(Calendar.MONTH,Integer.parseInt(dateSplitter[0])-1);
            currentDay.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateSplitter[1]));
            currentDay.set(Calendar.YEAR,Integer.parseInt(dateSplitter[2]));

                apple.add(new EventDay(currentDay, R.drawable.ic_access_time_black_24dp));
        }
        calendarView.setEvents(apple);

    }

}



