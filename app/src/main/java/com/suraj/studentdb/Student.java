package com.suraj.studentdb;

import java.io.Serializable;

public class Student implements Serializable {

   /* public String StudentName;
    public String StudentAge;
    public String StudentBlood;
    public String StudentGender;
    public String StudentRegd;
    public String StudentMobile;
    public String StudentBatch;
*/
    private int StudentId;
    private String StudentName;
    private String StudentAge;
    private String StudentBlood;
    private String StudentGender;
    private String StudentRegd;
    private String StudentMobile;
    private String StudentBatch;

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(String studentAge) {
        StudentAge = studentAge;
    }

    public String getStudentBlood() {
        return StudentBlood;
    }

    public void setStudentBlood(String studentBlood) {
        StudentBlood = studentBlood;
    }

    public String getStudentGender() {
        return StudentGender;
    }

    public void setStudentGender(String studentGender) {
        StudentGender = studentGender;
    }

    public String getStudentRegd() {
        return StudentRegd;
    }

    public void setStudentRegd(String studentRegd) {
        StudentRegd = studentRegd;
    }

    public String getStudentMobile() {
        return StudentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        StudentMobile = studentMobile;
    }

    public String getStudentBatch() {
        return StudentBatch;
    }

    public void setStudentBatch(String studentBatch) {
        StudentBatch = studentBatch;
    }
}
