package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button b,b1;
    EditText et1, et2,et3,et4;
    long bal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b= findViewById(R.id.button1);
        b.setOnClickListener(v -> {
            Intent intent= new Intent(MainActivity.this, all_customers.class);
            startActivity(intent);
        });
    }
}