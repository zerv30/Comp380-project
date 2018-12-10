package com.zane.Comp380_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Event> {
    public CustomAdapter(Context context, ArrayList<Event> list) {
        super(context, R.layout.custom_event_row,list);
    }
    TextView titleText,descriptionText,timeText;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView =  inflater.inflate(R.layout.custom_event_row,parent,false);

        final String title = getItem(position).getTitle();
        final String time = getItem(position).getTime();
        final String description = getItem(position).getDescription();


        titleText = customView.findViewById(R.id.CustomRowTitle);
        descriptionText  = customView.findViewById(R.id.CustomRowDescription);
        timeText =  customView.findViewById(R.id.CustomRowTime);

        titleText.setText(title);
        descriptionText.setText(description);
        timeText.setText(time);

        ImageButton editData = customView.findViewById(R.id.editDataButton);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = dataBaseHelper.getDataID(title,time,description);
                int itemId=-1;
                String itemDate = "";
                while(data.moveToNext()) {
                    itemId = data.getInt(0);
                    itemDate = data.getString(1);

                }
                 if(itemId >- 1){
                    Intent editEventIntent = new Intent(getContext(),EditEvent.class);
                    editEventIntent.putExtra("id",itemId);
                    editEventIntent.putExtra("title",title);
                    editEventIntent.putExtra("date",itemDate);
                     editEventIntent.putExtra("time",time);
                     editEventIntent.putExtra("description",description);
                     ((Activity)getContext()).startActivityForResult(editEventIntent, 0);
                     ((Activity)getContext()).finish();
                }else{
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageButton deleteData = customView.findViewById(R.id.deleteDataButton);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = dataBaseHelper.getDataID(title,time,description);
                while(data.moveToNext()) {
                    int itemId = data.getInt(0);
                    dataBaseHelper.deleteEvent(itemId);
                    Intent refreshIntent = new Intent(getContext(),Main2Activity.class);
                    refreshIntent.putExtra("date",data.getString(1));
                    ((Activity)getContext()).recreate();

                }
            }
        });
        return customView;
    }
}
