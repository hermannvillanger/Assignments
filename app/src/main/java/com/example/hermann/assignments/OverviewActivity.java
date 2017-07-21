package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

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

        createCourseButtons(courseList);

        sqLiteDatabase.close();
    }

    private void createCourseButtons(final List<Course> courseList) {
        TableLayout table = (TableLayout) findViewById(R.id.course_table);

        for(int i = 0; i < courseList.size(); i++){
            TableRow tablerow = new TableRow(context);
            tablerow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f));
            table.addView(tablerow);

            Button button = new Button(context);
            button.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT,
                    1.0f));
            button.setText(courseList.get(i).getName());
            final int index = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent direct = new Intent(OverviewActivity.this, CourseActivity.class);
                    direct.putExtra("courseId", courseList.get(index).getCId());
                    startActivity(direct);
                }
            });

            tablerow.addView(button);

            //TODO: Ide: Legg redirect knapp før for-loopen

        }
    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        databaseHelper.close();
        super.onDestroy();
    }
}
