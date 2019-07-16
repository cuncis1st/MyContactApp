package com.example.mycontactapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "student")
public class Student implements Parcelable {

    @ColumnInfo(name = "student_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "student_name")
    private String name;

    @ColumnInfo(name = "student_email")
    private String email;

    @ColumnInfo(name = "student_country")
    private String country;

    @ColumnInfo(name = "datetime")
    private String date;

    @Ignore
    public Student() {
    }

    public Student(String name, String email, String country, String date) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.country = country;
        this.date = date;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        country = in.readString();
        date = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(country);
        dest.writeString(date);
    }
}
