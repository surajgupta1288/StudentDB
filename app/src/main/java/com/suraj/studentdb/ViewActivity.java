package com.suraj.studentdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements StudentInfoAdapter.StudentClickListener {
    private DBHelper dbHelper;
    private  RecyclerView mRcStudentInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

         mRcStudentInfos = findViewById(R.id.rc_student_info);

        //Linear
        mRcStudentInfos.setLayoutManager(new LinearLayoutManager(ViewActivity.this,RecyclerView.VERTICAL,false));
        //Grid
       // mRcStudentInfos.setLayoutManager(new GridLayoutManager(ViewActivity.this,3));
        //staggered
       // mRcStudentInfos.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

         dbHelper = new DBHelper(ViewActivity.this);

         loadDataToRecycler();
    }

    @Override
    public void onUpdateClicked(Student student) {

        Intent updateIntent = new Intent(ViewActivity.this,MainActivity.class);
        updateIntent.putExtra("STUDENT",student);
        startActivityForResult(updateIntent,1001);
    }

    @Override
    public void onDeleteClicked(Student student) {

        dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(),student);
        loadDataToRecycler();
    }

    private void loadDataToRecycler(){
        ArrayList<Student> studentDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());
        StudentInfoAdapter adapter = new StudentInfoAdapter(ViewActivity.this,studentDetails);
        adapter.setListener(this);
        mRcStudentInfos.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001){
            if(resultCode == Activity.RESULT_OK){
                loadDataToRecycler();
            }
        }
    }
}