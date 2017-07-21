package com.example.hermann.assignments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import helper.DatabaseHelper;
import model.Course;

/**
 * Created by Hermann on 01.07.2017.
 */

public class CourseActivity extends Activity{

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button addAssignment,deleteCourse;
    Integer courseId;
    Course course;
    TextView courseName, courseCode;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        context = CourseActivity.this;
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

        deleteCourse = findViewById(R.id.deleteCourse);
        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder deleteConfirmation = new AlertDialog.Builder(context);

                deleteConfirmation.setTitle("Confirm delete...");
                deleteConfirmation.setMessage("Are you sure you want to delete this course?");
                deleteConfirmation.setCancelable(true);

                deleteConfirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent direct = new Intent(CourseActivity.this, OverviewActivity.class);
                        boolean deleted = databaseHelper.deleteCourse(course, sqLiteDatabase);
                        if(deleted){
                            Toast("Course Deleted");
                            startActivity(direct);
                        }
                        else{
                            Toast("Something went wrong");
                        }
                    }
                });
                deleteConfirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                deleteConfirmation.show();

            }
        });

        course = databaseHelper.getCourse(courseId, sqLiteDatabase);

        courseName.setText(course.getName());
        courseCode.setText(course.getCCode());
    }

    private void Toast(String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        databaseHelper.close();
        super.onDestroy();
    }
}
