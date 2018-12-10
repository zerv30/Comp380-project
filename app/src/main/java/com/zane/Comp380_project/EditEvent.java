 package com.zane.Comp380_project;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

 public class EditEvent extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    Button submitEvent;
    ImageButton timePickerbtn;
    TextView dateText,timeText;
    EditText titleText, descriptionText;
    String originalDate,originalTitle,originalDescription,originalTime;
    int eventId;

     @Override
     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
         String status = "AM";
         if(hourOfDay > 12){
             status = "PM";
         }
         timeText.setText(hourOfDay+":"+minute + status);
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        final DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        submitEvent = findViewById(R.id.btnSubmitEvent);

        timePickerbtn = findViewById(R.id.timePickerButton);

        dateText= findViewById(R.id.eventDate);

        titleText = findViewById(R.id.eventTitle);
        timeText = findViewById(R.id.timeText);
        descriptionText = findViewById(R.id.eventDescription);


        Intent editEventIntent = getIntent();
        originalDate=editEventIntent.getStringExtra("date");
        originalTitle = editEventIntent.getStringExtra("title");
        originalTime = editEventIntent.getStringExtra("time");
        originalDescription = editEventIntent.getStringExtra("description");
        eventId = editEventIntent.getIntExtra("id",0);

        dateText.setText(originalDate);
        titleText.setText(originalTitle);
        timeText.setText(originalTime);
        timePickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });
        descriptionText.setText(editEventIntent.getStringExtra("description"));

        submitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateTitle(titleText.getText().toString(), eventId, originalTitle);
                dataBaseHelper.updateDescription(descriptionText.getText().toString(), eventId, originalDescription);
                dataBaseHelper.updateTime(timeText.getText().toString(), eventId, originalTime);
                Intent main2 = new Intent(EditEvent.this, Main2Activity.class);
                main2.putExtra("date",dateText.getText().toString());
                startActivity(main2);
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });


    }
}
