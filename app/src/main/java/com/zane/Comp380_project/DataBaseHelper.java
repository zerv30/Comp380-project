package com.zane.Comp380_project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  static final String TAG  = "DatabaseHelper";
    private static final String USER_TABLE_NAME = "user";
    private static final String USER_COL_ID = "id";
    private static final String USER_COL_NAME = "userName";
    private static final String USER_COL_AGE = "age";

    private static final String QUERY_USER_CREATE_TABLE =
            "CREATE TABLE " + USER_TABLE_NAME + " (" + USER_COL_ID + " INTEGER PRIMARY KEY, " +
                    USER_COL_NAME + " TEXT, " + USER_COL_AGE + " INTEGER DEFAULT 0 );";

    public DataBaseHelper(Context context){
        super(context,USER_TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(QUERY_USER_CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db ,int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS  " + USER_TABLE_NAME);
        onCreate(db);
    }

    public  boolean addData(String date,String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_NAME,date);
        contentValues.put(USER_COL_AGE,title);
        Log.d(TAG,"addData adding: " + date + " to " + USER_TABLE_NAME);
        long result = db.insert(USER_TABLE_NAME,null,contentValues);
        return result != 1;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " +USER_TABLE_NAME;
        Cursor data= db.rawQuery(query,null);
        return  data;

    }

}
