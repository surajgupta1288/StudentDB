package com.suraj.studentdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student_info_table";
    private static final String COL_ID = "id";
    private static final String COL_STUDENT_NAME ="student_name" ;
    private static final String COL_STUDENT_AGE ="student_age" ;
    private static final String COL_STUDENT_BLOOD ="student_blood" ;
    private static final String COL_STUDENT_GENDER ="student_gender" ;
    private static final String COL_STUDENT_REGD ="student_roll" ;
    private static final String COL_STUDENT_MOBILE ="student_mobile" ;
    private static final String COL_STUDENT_BATCH = "student_batch";



    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_STUDENT_NAME+
            " TEXT,"+COL_STUDENT_AGE+" TEXT,"+COL_STUDENT_BLOOD+" TEXT,"+COL_STUDENT_GENDER+" TEXT,"+COL_STUDENT_REGD+
            " TEXT,"+COL_STUDENT_MOBILE+" TEXT,"+COL_STUDENT_BATCH+" TEXT)";



    public DBHelper(@Nullable Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertDataToDatabase(SQLiteDatabase db,Student student){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STUDENT_NAME,student.getStudentName());
        contentValues.put(COL_STUDENT_AGE,student.getStudentAge());
        contentValues.put(COL_STUDENT_BLOOD,student.getStudentBlood());
        contentValues.put(COL_STUDENT_GENDER,student.getStudentGender());
        contentValues.put(COL_STUDENT_REGD,student.getStudentRegd());
        contentValues.put(COL_STUDENT_MOBILE,student.getStudentMobile());
        contentValues.put(COL_STUDENT_BATCH,student.getStudentBatch());

        db.insert(TABLE_NAME,null,contentValues);

    }

    public void updateDataFromDatabase(SQLiteDatabase db,Student student){

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_STUDENT_NAME,student.getStudentName());
        contentValues.put(COL_STUDENT_AGE,student.getStudentAge());
        contentValues.put(COL_STUDENT_BLOOD,student.getStudentBlood());
        contentValues.put(COL_STUDENT_GENDER,student.getStudentGender());
        contentValues.put(COL_STUDENT_REGD,student.getStudentRegd());
        contentValues.put(COL_STUDENT_MOBILE,student.getStudentMobile());
        contentValues.put(COL_STUDENT_BATCH,student.getStudentBatch());

        db.update(TABLE_NAME,contentValues,COL_ID+"=" + student.getStudentId(),null);
    }

    public void deleteDataFromDatabase(SQLiteDatabase db,Student student){
        db.delete(TABLE_NAME,COL_ID+"="+student.getStudentId(),null);
    }

    public ArrayList<Student> getDataFromDatabase(SQLiteDatabase database){
        ArrayList<Student> studentsLists = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor !=null && cursor.moveToFirst())
        {
            do{

                Student studentinfo = new Student();
                studentinfo.setStudentName(cursor.getString(cursor.getColumnIndex(COL_STUDENT_NAME)));
                studentinfo.setStudentId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                studentinfo.setStudentAge(cursor.getString(cursor.getColumnIndex(COL_STUDENT_AGE)));
                studentinfo.setStudentBlood(cursor.getString(cursor.getColumnIndex(COL_STUDENT_BLOOD)));
                studentinfo.setStudentGender(cursor.getString(cursor.getColumnIndex(COL_STUDENT_GENDER)));
                studentinfo.setStudentRegd(cursor.getString(cursor.getColumnIndex(COL_STUDENT_REGD)));
                studentinfo.setStudentMobile(cursor.getString(cursor.getColumnIndex(COL_STUDENT_MOBILE)));
                studentinfo.setStudentBatch(cursor.getString(cursor.getColumnIndex(COL_STUDENT_BATCH)));

                studentsLists.add(studentinfo);

            }while (cursor.moveToNext());

            cursor.close();
        }

        return studentsLists;
    }

}
