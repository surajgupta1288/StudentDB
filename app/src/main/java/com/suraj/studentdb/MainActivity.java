package com.suraj.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mStudentName;
    private EditText mStudentAge;
    private EditText mStudentBlood;
    private EditText mStudentGender;
    private EditText mStudentRoll;
    private EditText mStudentMobile;
    private EditText mStudentBatch;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      mStudentName = findViewById(R.id.et_student_name);
         mStudentAge = findViewById(R.id.et_student_age);
         mStudentBlood = findViewById(R.id.et_student_blood);
        mStudentGender = findViewById(R.id.et_student_gender);
        mStudentRoll = findViewById(R.id.et_student_regd);
        mStudentMobile = findViewById(R.id.et_student_mobile);
         mStudentBatch = findViewById(R.id.et_student_batch);

         dbHelper = new DBHelper(MainActivity.this);


        Button btnStudentAction = findViewById(R.id.btn_add_student);

        btnStudentAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String StudentName = mStudentName.getText().toString();
                 String StudentAge = mStudentAge.getText().toString();
                 String StudentBlood = mStudentBlood.getText().toString();
                 String StudentGender = mStudentGender.getText().toString();
                 String StudentRoll = mStudentRoll.getText().toString();
                 String StudentMobile = mStudentMobile.getText().toString();
                 String StudentBatch  =  mStudentBatch.getText().toString();


                 Student student = new Student();


                 student.setStudentName(StudentName);
                 student.setStudentAge(StudentAge);
                 student.setStudentBlood(StudentBlood);
                 student.setStudentGender(StudentGender);
                 student.setStudentRoll(StudentRoll);
                 student.setStudentMobile(StudentMobile);
                 student.setStudentBatch(StudentBatch);

                dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(),student);

                mStudentName.setText("");
                mStudentAge.setText("");
                mStudentBlood.setText("");
                mStudentGender.setText("");
                mStudentRoll.setText("");
                mStudentMobile.setText("");
                mStudentBatch.setText("");

            }
        });



    }
}