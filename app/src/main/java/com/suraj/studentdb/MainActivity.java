package com.suraj.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText mStudentName;
    private TextView mStudentAge;
    private EditText mStudentBlood;
    private EditText mStudentGender;
    private EditText mStudentRegd;
    private EditText mStudentMobile;
    private EditText mStudentBatch;

    private DBHelper dbHelper;
    private int studentId;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      mStudentName = findViewById(R.id.et_student_name);
         mStudentAge = findViewById(R.id.et_student_age);
         mStudentBlood = findViewById(R.id.et_student_blood);
        mStudentGender = findViewById(R.id.et_student_gender);
        mStudentRegd = findViewById(R.id.et_student_regd);
        mStudentMobile = findViewById(R.id.et_student_mobile);
         mStudentBatch = findViewById(R.id.et_student_batch);

         dbHelper = new DBHelper(MainActivity.this);


         Bundle data = getIntent().getExtras();
         if(data != null){
             Student student = (Student) data.getSerializable("STUDENT");
             isUpdate = true;
             mStudentName.setText(student.getStudentName());
             mStudentGender.setText(student.getStudentGender());
             mStudentRegd.setText(student.getStudentRegd());
             mStudentMobile.setText(student.getStudentMobile());
             mStudentBatch.setText(student.getStudentBatch());

             studentId = student.getStudentId();

         }

        Button btnStudentAction = findViewById(R.id.btn_add_student);


        btnStudentAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String StudentName = mStudentName.getText().toString();
                 String StudentAge = mStudentAge.getText().toString();
                 String StudentBlood = mStudentBlood.getText().toString();
                 String StudentGender = mStudentGender.getText().toString();
                 String StudentRoll = mStudentRegd.getText().toString();
                 String StudentMobile = mStudentMobile.getText().toString();
                 String StudentBatch  =  mStudentBatch.getText().toString();


                 Student student = new Student();


                 student.setStudentName(StudentName);
                 student.setStudentAge(StudentAge);
                 student.setStudentBlood(StudentBlood);
                 student.setStudentGender(StudentGender);
                 student.setStudentRegd(StudentRoll);
                 student.setStudentMobile(StudentMobile);
                 student.setStudentBatch(StudentBatch);

                 if(isUpdate){
                     student.setStudentId(studentId);
                     dbHelper.updateDataFromDatabase(dbHelper.getWritableDatabase(), student);
                     setResult(Activity.RESULT_OK);
                     finish();
                 }
                 else {

                     dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), student);
                 }
                mStudentName.setText("");
                mStudentAge.setText("");
                mStudentBlood.setText("");
                mStudentGender.setText("");
                mStudentRegd.setText("");
                mStudentMobile.setText("");
                mStudentBatch.setText("");

            }
        });


        Button btnGetInfo = findViewById(R.id.btn_get_student_info);

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              ArrayList<Student> studentList = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());
                Toast.makeText(MainActivity.this," Name : "+studentList.get(0).getStudentName(),Toast.LENGTH_LONG).show();
            }
        });

        mStudentAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
    }

    private void openDatePicker(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            DatePickerDialog datePickerDialog = null;
//
//             datePickerDialog = new DatePickerDialog(MainActivity.this);
//            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int day) {
//
//                    String date = String.valueOf(day).concat("-").concat(String.valueOf(month+1)).concat("-").concat(String.valueOf(year));
//                    mStudentAge.setText(date);
//                }
//            });
//            datePickerDialog.show();

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int min) {

                    String duration = "";
                    int hourvalue;
                    if(hours< 12){
                        hourvalue = hours;
                        duration ="AM";
                    }else if(hours == 12){
                        hourvalue = hours;
                        duration ="PM";
                    }else {
                        hourvalue = hours%12;
                        duration = "PM";
                    }
                    String date = String.valueOf(hourvalue).concat(":").concat(String.valueOf(min)).concat(" ").concat(duration);

                    mStudentAge.setText(date);
                }
            }, Calendar.HOUR_OF_DAY,Calendar.MINUTE,false);
            timePickerDialog.show();

            Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
        String currentTime = timeFormat.format(calendar.getTime());
        String currentDate = dateFormat.format(calendar.getTime());
        mStudentAge.setText(currentDate + " - " + currentTime);
        }
    }
