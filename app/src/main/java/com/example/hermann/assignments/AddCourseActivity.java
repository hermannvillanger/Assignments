package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.DatabaseHelper;
import model.Course;

/**
 * Created by Hermann on 30.06.2017.
 */

public class AddCourseActivity extends Activity {
    EditText CourseName, CourseCode;
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button saveCourse;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        context = getBaseContext();

        CourseName = findViewById(R.id.course_name);
        CourseCode = findViewById(R.id.course_code);

        saveCourse = (Button) findViewById(R.id.saveCourse);
        saveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourse(view);
                Intent direct = new Intent(AddCourseActivity.this, OverviewActivity.class);
                startActivity(direct);
            }
        });
    }

    public void addCourse(View view){
        String name = CourseName.getText().toString();
        String code = CourseCode.getText().toString();
        Course course = new Course(null, name, code);

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        boolean saved = databaseHelper.createCourse(course, sqLiteDatabase);
        if(saved){
            Toast("Course Saved");
        }
        else{
            Toast("Something went wrong");
        }
        databaseHelper.close();
    }

    private void Toast(String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
