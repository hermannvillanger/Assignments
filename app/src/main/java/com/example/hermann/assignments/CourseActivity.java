package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import helper.DatabaseHelper;
import model.Course;

/**
 * Created by Hermann on 01.07.2017.
 */

public class CourseActivity extends Activity{

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addAssignment;
    Integer courseId;
    Course course;
    TextView courseName, courseCode;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        context = getBaseContext();
        Intent intent = getIntent();
        courseId = intent.getIntExtra("courseId",0);

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        courseName = findViewById(R.id.courseName);
        courseCode = findViewById(R.id.courseCode);

        addAssignment = (Button) findViewById(R.id.addAssignment);
        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct = new Intent(CourseActivity.this, AddAssignmentActivity.class);
                direct.putExtra("courseId",courseId);
                startActivity(direct);
            }
        });
        course = databaseHelper.getCourse(courseId, sqLiteDatabase);

        courseName.setText(course.getName());
        courseCode.setText(course.getCCode());
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        databaseHelper.close();
        super.onDestroy();
    }
}
