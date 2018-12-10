package com.zane.Comp380_project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private  static final String TAG  = "DatabaseHelper";
    private static final String TABLE_NAME = "eventTable";
    private static final String COL_ID = "id";
    private static final String COL_DATE = "date";
    private static final String COL_TITLE= "title";
    private static final String COL_TIME= "time";
    private static final String COL_DESCRIPTION = "description";

    private static final String QUERY_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_DATE + " TEXT, " + COL_TITLE +" TEXT, " + COL_TIME+" TEXT, " + COL_DESCRIPTION + " TEXT )";

    public DataBaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(QUERY_CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db ,int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS  " + TABLE_NAME);
        onCreate(db);
    }

    public  boolean addData(String date,String title,String time,String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE,date);
        contentValues.put(COL_TITLE,title);
        contentValues.put(COL_TIME,time);
        contentValues.put(COL_DESCRIPTION,description);
        Log.d(TAG,"addData adding: " + date + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != 1;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME;
        Cursor data= db.rawQuery(query,null);
        return  data;
    }
    public Cursor getDataID(String title,String time,String description){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + ", "+COL_DATE+" FROM " +TABLE_NAME +" WHERE " +COL_TITLE+" = '" + title + "' "+"AND "+COL_DESCRIPTION +" = '" + description + "'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }
    public void updateTitle(String newTitle, int id, String oldTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME +" SET "+COL_TITLE  + " = '" + newTitle + "' WHERE "+ COL_ID + " = '" + id + "'" + " AND " + COL_TITLE + " = '" + oldTitle + "' ";
        db.execSQL(query);
    }
    public void updateTime(String newTime, int id, String oldTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME +" SET "+COL_TIME  + " = '" + newTime + "' WHERE "+ COL_ID + " = '" + id + "'" + " AND " + COL_TIME + " = '" + oldTime + "' ";
        db.execSQL(query);
    }
    public void updateDescription(String newTitle, int id, String oldTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+TABLE_NAME +" SET "+COL_DESCRIPTION  + " = '" + newTitle + "' WHERE "+ COL_ID + " = '" + id + "'" + " AND " + COL_DESCRIPTION + " = '" + oldTitle + "' ";
        db.execSQL(query);
    }

    public void  deleteEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WhERE " + COL_ID + " = '" + id + "'";
        db.execSQL(query);
    }
}

