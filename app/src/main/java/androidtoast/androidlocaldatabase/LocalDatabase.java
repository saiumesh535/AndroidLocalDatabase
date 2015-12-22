package androidtoast.androidlocaldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sai Umesh on 12/22/2015.
 */
public class LocalDatabase extends SQLiteOpenHelper {

    //you can define any name for the database here, for this instance i'm just writing database_name.
    public static final String DATABASE_NAME = "database_name";

    //database version, when change the value of it, it will call onUpgrade() method.
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

        String query  = "CREATE TABLE " + TABLE_NAME + "("
                + STUDENT_NAME + " TEXT,"
                + STUDENT_MOBILE_NUMBER + " TEXT" + ")";

        db.execSQL(query);

    }

    public void insert(ContentValues contentValues){

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_NAME,null,contentValues);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }
}
