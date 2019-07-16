package com.example.mycontactapp;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mycontactapp.adapter.StudentAdapter;
import com.example.mycontactapp.db.StudentsAppDatabase;
import com.example.mycontactapp.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView rvStudent;

    private StudentAdapter adapter;
    private List<Student> studentList;
    private StudentsAppDatabase studentsAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentList = new ArrayList<>();
        studentsAppDatabase = Room.databaseBuilder(this, StudentsAppDatabase.class, "student_db")
                .build();

        new GetAllStudentAsyncTask().execute();

        fabAdd = findViewById(R.id.fab_add);
        rvStudent = findViewById(R.id.rv_studentList);
        rvStudent.setLayoutManager(new LinearLayoutManager(this));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddStudent.class);
                startActivityForResult(i, 1);
//                new DeleteAllStudentAsyncTask().execute();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                studentList.remove(i);
                new DeleteStudentAsyncTask().execute(studentList.get(i));
            }
        });
        itemTouchHelper.attachToRecyclerView(rvStudent);

        adapter = new StudentAdapter(this, studentList);
        rvStudent.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            Student student = data.getParcelableExtra("STUDENT_KEY");
            new AddStudentAsyncTask().execute(student);
        }
    }

    private class GetAllStudentAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... aVoid) {
            studentList.addAll(studentsAppDatabase.studentDAO().getAllStudent());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }

    }

    private class AddStudentAsyncTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            long id = studentsAppDatabase.studentDAO().addStudent(students[0]);

            Student student = studentsAppDatabase.studentDAO().getStudent(id);

            if (student != null) {
                studentList.add(student);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            studentsAppDatabase.studentDAO().deleteStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class DeleteAllStudentAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            studentsAppDatabase.studentDAO().deleteAllStudent();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
