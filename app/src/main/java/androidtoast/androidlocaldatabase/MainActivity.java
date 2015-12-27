package androidtoast.androidlocaldatabase;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText studentName,mobileNumber;
    Button insert;
    Button getValues;
    Button updateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        studentName     =   (EditText)findViewById(R.id.studentName);
        mobileNumber    =   (EditText)findViewById(R.id.mobileNumber);
        insert          =   (Button)findViewById(R.id.insert);
        getValues       =   (Button)findViewById(R.id.getValues);
        updateValue          =   (Button)findViewById(R.id.updateValue);

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

    //getting the values from local database
        getValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList arrayList = new LocalDatabase(MainActivity.this).getValues();

                if(arrayList.size()==0){

                    Toast.makeText(MainActivity.this,"try inserting values before getting them",Toast.LENGTH_LONG).show();

                    return;
                }

                Toast.makeText(MainActivity.this,arrayList.toString(),Toast.LENGTH_LONG).show();

            }
        });


        //updating the values in local database
        updateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //lets say i'm updating mobile number of student with name akash
                int numberOfRowsUpdated = new LocalDatabase(MainActivity.this).updateValue("akash","1234567890");

                if(numberOfRowsUpdated>0){
                    Toast.makeText(MainActivity.this,"Updated values successfully",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(MainActivity.this,"Please check entered details and try again",Toast.LENGTH_LONG).show();

            }
        });




    }
}
