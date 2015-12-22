package androidtoast.androidlocaldatabase;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText studentName,mobileNumber;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        studentName     =   (EditText)findViewById(R.id.studentName);
        mobileNumber    =   (EditText)findViewById(R.id.mobileNumber);
        insert          =   (Button)findViewById(R.id.insert);

        //storing the values on button click
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //storing the user entered values in database using content values
                //validating the fields before inserting
                if (studentName.getText().length() == 0 || mobileNumber.getText().length() == 0) {

                    Toast.makeText(getApplicationContext(), "Please enter all the credentials", Toast.LENGTH_LONG).show();
                    return;
                }

                LocalDatabase db = new LocalDatabase(getApplicationContext());
                ContentValues contentValues = new ContentValues();
                contentValues.put(LocalDatabase.STUDENT_NAME, studentName.getText().toString());
                contentValues.put(LocalDatabase.STUDENT_MOBILE_NUMBER, mobileNumber.getText().toString());

                //insertig the values into database now
                db.insert(contentValues);
                Toast.makeText(getApplicationContext(), "successfully inserted", Toast.LENGTH_LONG).show();

                //clearing edittexts after successful insertion
                studentName.getText().clear();
                mobileNumber.getText().clear();

            }
        });






    }
}
