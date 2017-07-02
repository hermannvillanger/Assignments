package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import helper.DatabaseHelper;
import model.Course;

/**
 * Created by Hermann on 01.07.2017.
 */

public class OverviewActivity extends Activity{

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addCourse;
    Button toCourse;
    List<Course> courseList;
    Integer courseId;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courses);
        context = getBaseContext();

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        courseList = databaseHelper.getAllCourses(sqLiteDatabase);

        addCourse = (Button) findViewById(R.id.addCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(OverviewActivity.this, AddCourseActivity.class);
                startActivity(direct);
            }
        });

        //TODO Knapp for her Course, som sender courseId videre
        toCourse = (Button) findViewById(R.id.toCourse);
        toCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(OverviewActivity.this, CourseActivity.class);
                direct.putExtra("courseId", courseId);
                startActivity(direct);
            }
        });


        sqLiteDatabase.close();
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        databaseHelper.close();
        super.onDestroy();
    }
}
