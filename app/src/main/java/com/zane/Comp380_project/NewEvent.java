package com.zane.Comp380_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class NewEvent extends AppCompatActivity {
    Button btnSubmitEvent;
    DataBaseHelper mDataBaseHelper;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         final String msg = "Button has been clicked";

         mDataBaseHelper = new DataBaseHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);



        editText = findViewById(R.id.eventTitle);
        btnSubmitEvent = findViewById(R.id.btnSubmitEvent);
        Intent getDate = getIntent();
        final String selectedDate = getDate.getStringExtra("date");
        final TextView dateText = findViewById(R.id.date_2);
        dateText.setText(selectedDate);
        btnSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entry2 = editText.getText().toString();
                String date = dateText.getText().toString();
//                Toast.makeText(getApplicationContext() ,date,LENGTH_SHORT).show();
                String success = "You have successfully added " + entry2;
                String failure = "There was a problem adding " + entry2;



                if (entry2.length() > 0) {
                    Boolean insertData = mDataBaseHelper.addData(date,entry2);
                    if (insertData) {
                        Toast.makeText(getApplicationContext(), success, LENGTH_SHORT).show();
                        finish();
                        Intent main2 = new Intent(NewEvent.this,Main2Activity.class);
                        main2.putExtra("date",selectedDate);
                        startActivity(main2);
                    }
                    {
                        Toast.makeText(getApplicationContext(), failure, LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "You need to input something", LENGTH_SHORT).show();
                }

            }
        });

    }
}
