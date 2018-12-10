package com.zane.Comp380_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.*;

public class NewEvent extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    ;
    Button btnSubmitEvent;
    DataBaseHelper mDataBaseHelper;
    EditText descriptionInput,titleInput;
    TextView timeText,startDatePicker;

//    Button datePicker;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String status = "AM";
        if(hourOfDay > 12){
            hourOfDay -= 12;
            status = "PM";
        }
        timeText.setText(hourOfDay+":"+minute + status);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String msg = "Button has been clicked";


        mDataBaseHelper = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        startDatePicker = findViewById(R.id.startDate);
        startDatePicker.setText(getIntent().getStringExtra("date"));
        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewEvent.this,dateSetListener,year,month,day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.));
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startDatePicker.setText(month + "/" + dayOfMonth +"/"+year);
            }
        };




        titleInput = findViewById(R.id.eventTitle);
        descriptionInput = findViewById(R.id.eventDescription);
        timeText = findViewById(R.id.timeText);
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        String status = "AM";
        if( hour > 12){
            hour -= 12;
        }
        if(min < 10) {
            timeText.setText(hour + ":0" + min + status);
        }else{
            timeText.setText(hour + ":" + min + status);

        }
        btnSubmitEvent = findViewById(R.id.btnSubmitEvent);

        Intent getDate = getIntent();

        final String selectedDate = getDate.getStringExtra("date");
        final TextView dateText = findViewById(R.id.date_2);
        dateText.setText(selectedDate);

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimepicker();
            }
        });


        btnSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String title
                String description = descriptionInput.getText().toString();
                String date = dateText.getText().toString();
                String title = titleInput.getText().toString();
                String time = timeText.getText().toString();

                String success = "You have successfully added " + description;
                String failure = "There was a problem adding " + description;



                if (title.length() > 0) {
                    Boolean insertData = mDataBaseHelper.addData(date,title,time,description);//,"12:00","abbple bottom jeabns");
                    makeNotification(title,time,description);
                    if (insertData) {
                        Toast.makeText(getApplicationContext(), success, LENGTH_SHORT).show();
                        finish();
                        Intent main2 = new Intent(NewEvent.this,Main2Activity.class);
                        main2.putExtra("date",selectedDate);
                        startActivity(main2);
                    } else{
                        Toast.makeText(getApplicationContext(), failure, LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Title cannot be empty", LENGTH_SHORT).show();
                }

            }
        });

    }
    public  void openTimepicker(){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(),"time picker");
    }
    public void makeNotification(String title, String time, String descriptin){

    }
}
