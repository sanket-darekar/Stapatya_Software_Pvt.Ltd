package com.example.stapatyasoftwarwpvtltd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

        public class MainActivity extends AppCompatActivity {
            private EditText firstName, lastName, email, mobileNo, panNo, adharNo, dob;
            private Button saveBtn, updateBtn, displayBtn;
            private DatabaseHelper dbHelper;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                dbHelper = new DatabaseHelper(this);

                firstName = findViewById(R.id.etFirstName);
                lastName = findViewById(R.id.etLastName);
                email = findViewById(R.id.etEmail);
                mobileNo = findViewById(R.id.etMobileNo);
                panNo = findViewById(R.id.etPanNo);
                adharNo = findViewById(R.id.etAdharNo);
                dob = findViewById(R.id.etDob);

                saveBtn = findViewById(R.id.btnSave);
                updateBtn = findViewById(R.id.btnUpdate);
                displayBtn = findViewById(R.id.btnDisplay);

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbHelper.insertData(
                                firstName.getText().toString(),
                                lastName.getText().toString(),
                                email.getText().toString(),
                                mobileNo.getText().toString(),
                                panNo.getText().toString(),
                                adharNo.getText().toString(),
                                dob.getText().toString()
                        );

                        if (isInserted) {
                            Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = dbHelper.updateData(
                                firstName.getText().toString(),
                                lastName.getText().toString(),
                                email.getText().toString(),
                                mobileNo.getText().toString(),
                                panNo.getText().toString(),
                                adharNo.getText().toString(),
                                dob.getText().toString()
                        );

                        if (isUpdated) {
                            Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                displayBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String data = dbHelper.getData();
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
