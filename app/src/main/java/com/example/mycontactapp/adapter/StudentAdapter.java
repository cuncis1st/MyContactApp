package com.example.mycontactapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycontactapp.R;
import com.example.mycontactapp.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvName.setText(student.getName());
        holder.tvEmail.setText(student.getEmail());
        holder.tvCountry.setText(student.getCountry());
        holder.tvDate.setText(student.getDate());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail, tvCountry, tvDate;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvDate = itemView.findViewById(R.id.tv_datetime);
        }
    }

}

















