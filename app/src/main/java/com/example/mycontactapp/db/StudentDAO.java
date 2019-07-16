package com.example.mycontactapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mycontactapp.model.Student;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    long addStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> getAllStudent();

    @Query("SELECT * FROM student WHERE student_id =:studentId")
    Student getStudent(long studentId);

    @Query("DELETE FROM student")
    void deleteAllStudent();
}
