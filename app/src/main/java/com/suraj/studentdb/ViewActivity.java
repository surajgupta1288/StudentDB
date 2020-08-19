package com.suraj.studentdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RecyclerView mRcStudentInfos = findViewById(R.id.rc_student_info);

        //Linear
        mRcStudentInfos.setLayoutManager(new LinearLayoutManager(ViewActivity.this,RecyclerView.VERTICAL,false));
        //Grid
       // mRcStudentInfos.setLayoutManager(new GridLayoutManager(ViewActivity.this,3));
        //staggered
       // mRcStudentInfos.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        DBHelper dbHelper = new DBHelper(ViewActivity.this);
        ArrayList<Student> studentDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());
        StudentInfoAdapter adapter = new StudentInfoAdapter(ViewActivity.this,studentDetails);
        mRcStudentInfos.setAdapter(adapter);
    }
}