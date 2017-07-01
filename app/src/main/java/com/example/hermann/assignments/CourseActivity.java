package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import helper.DatabaseHelper;

/**
 * Created by Hermann on 01.07.2017.
 */

public class CourseActivity extends Activity{

    Context context = this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addAssignment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);

        addAssignment = (Button) findViewById(R.id.addAssignment);
        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(CourseActivity.this, AddCourseActivity.class);
                startActivity(direct);
            }
        });
    }

}
