package com.example.mycontactapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mycontactapp.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentsAppDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();
}
