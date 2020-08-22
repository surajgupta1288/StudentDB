package com.suraj.studentdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.StudentInfoHolder> {

    private Context context;
    private ArrayList<Student> studentDetails;
    private StudentClickListener listener;

    public StudentInfoAdapter(Context context,ArrayList<Student> studentDetails){
        this.context = context;
        this.studentDetails = studentDetails;
    }

    public  void setListener(StudentClickListener listener){

        this.listener = listener;
    }



    @NonNull
    @Override
    public StudentInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentInfoHolder holder = new StudentInfoHolder(LayoutInflater.from(context).inflate(R.layout.cell_student,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentInfoHolder holder, int position) {
        final Student student = studentDetails.get(position);

        holder.mTvStudentName.setText(student.getStudentName());
        holder.mTvStudentGender.setText(student.getStudentGender());
        holder.mTvStudentRegd.setText(student.getStudentRegd());
        holder.mTvStudentMobile.setText(student.getStudentMobile());
        holder.mTvStudentBatch.setText(student.getStudentBatch());

        holder.mRlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onUpdateClicked(student);
                }
            }
        });

        holder.mRlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onDeleteClicked(student);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDetails.size();
    }

    // Step1: Create a class for a view Holder (cell)
    // step2: view holder extends to RecyclerView.view Holder
    // step3: findView by id inside the view holder
    //step4 : Extend the parent class with recyclerView.adapter with the View Holder
    //step5 : Implement the override methods


    class StudentInfoHolder extends RecyclerView.ViewHolder{

        private TextView mTvStudentName;
        private TextView mTvStudentGender;
        private TextView mTvStudentRegd;
        private TextView mTvStudentMobile;
        private TextView mTvStudentBatch;

        private RelativeLayout mRlEdit;
        private RelativeLayout mRlDelete;

        public StudentInfoHolder(@NonNull View itemView) {
            super(itemView);

            mTvStudentName = itemView.findViewById(R.id.tv_student_name);
            mTvStudentGender = itemView.findViewById(R.id.tv_student_gender);
            mTvStudentRegd = itemView.findViewById(R.id.tv_student_regd);
            mTvStudentMobile = itemView.findViewById(R.id.tv_student_mobile);
            mTvStudentBatch = itemView.findViewById(R.id.tv_student_batch);

            mRlEdit = itemView.findViewById(R.id.rl_edit);
            mRlDelete = itemView.findViewById(R.id.rl_delete);
        }
    }

    public  interface StudentClickListener{

        void onUpdateClicked(Student student);

        void onDeleteClicked(Student student);

    }
}
