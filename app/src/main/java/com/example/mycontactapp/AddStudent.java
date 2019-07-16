package com.example.mycontactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mycontactapp.model.Student;

public class AddStudent extends AppCompatActivity {

    EditText etName, etEmail, etCountry;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etCountry = findViewById(R.id.et_country);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String country = etCountry.getText().toString().trim();
                Student student = new Student(name, email, country, "");

                Intent returnIntent = new Intent();
                returnIntent.putExtra("STUDENT_KEY", student);
                setResult(1, returnIntent);
                finish();
            }
        });
    }
}
