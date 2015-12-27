package androidtoast.androidlocaldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sai Umesh on 12/22/2015.
 */
public class LocalDatabase extends SQLiteOpenHelper {

    //you can define any name for the database here, for this instance i'm just writing database_name.
    public static final String DATABASE_NAME = "database_name";

    //if you increase the database version then onUpgrade() method will be called
    public static final int DATABASE_VERSION = 1;

    //table name
    public static final String TABLE_NAME = "table_name";

    //attribute name for the table
    public static final String STUDENT_NAME = "student_name";
    public static final String STUDENT_MOBILE_NUMBER = "student_mobile_name";



    public LocalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String query  = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + STUDENT_NAME + " TEXT,"
                + STUDENT_MOBILE_NUMBER + " TEXT" + ")";

        db.execSQL(query);

    }

    //inserting values into local database
    public void insert(ContentValues contentValues){

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NAME, null, contentValues);

    }

    //getting the values from database
    public ArrayList getValues(){

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<HashMap> arrayList = new ArrayList<>();

        String query = "select * from "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){

            do{

                HashMap<String,String> map = new HashMap<>();
                map.put(STUDENT_NAME,cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                map.put(STUDENT_MOBILE_NUMBER,cursor.getString(cursor.getColumnIndex(STUDENT_MOBILE_NUMBER)));
                arrayList.add(map);

            }while (cursor.moveToNext());
        }

        cursor.close();

        return arrayList;


    }

    public int updateValue(String name,String number){

        String[] searchCriteria = new String[]{name};

        ContentValues values = new ContentValues();

        values.put(STUDENT_MOBILE_NUMBER, number);

        SQLiteDatabase db = this.getWritableDatabase();

        //table name, setting values,where to set
        return  db.update(TABLE_NAME, values, STUDENT_NAME + " = ?", searchCriteria);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }
}
