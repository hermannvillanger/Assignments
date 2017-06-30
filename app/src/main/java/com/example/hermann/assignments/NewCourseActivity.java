package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import helper.DatabaseHelper;
import model.Course;

/**
 * Created by Hermann on 30.06.2017.
 */

public class NewCourseActivity extends Activity {
    EditText CourseName, CourseCode;
    Context context = this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addcourse);
        CourseName = (EditText) findViewById(R.id.course_name);
        CourseCode = (EditText) findViewById(R.id.course_code);

    }

    public void addCourse(View view){
        String name = CourseName.getText().toString();
        String code = CourseCode.getText().toString();
        Course course = new Course(null, name, code);

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        databaseHelper.createCourse(course, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Course Saved", Toast.LENGTH_SHORT).show();
        databaseHelper.close();
    }

}
