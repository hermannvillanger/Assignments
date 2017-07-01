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

public class OverviewActivity extends Activity{

    Context context = this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addCourse;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);


        Button toCourse = (Button) findViewById(R.id.toCourse);
        toCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(OverviewActivity.this, CourseActivity.class);
                startActivity(direct);
            }
        });
        addCourse = (Button) findViewById(R.id.addCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(OverviewActivity.this, AddCourseActivity.class);
                startActivity(direct);
            }
        });

    }

}
