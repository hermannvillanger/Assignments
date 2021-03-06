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
import model.Assignment;

/**
 * Created by Hermann on 30.06.2017.
 */

public class AddAssignmentActivity extends Activity {

    EditText Weekday, DeliveryTime, DeliveryType, NextDelivery;
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button saveAssignment;
    Integer courseId;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        context = AddAssignmentActivity.this;
        Intent intent = getIntent();
        courseId = intent.getIntExtra("courseId",0);

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        Weekday = findViewById(R.id.weekday);
        DeliveryTime = findViewById(R.id.delivery_time);
        DeliveryType = findViewById(R.id.delivery_type);
        NextDelivery = findViewById(R.id.next_delivery);

        saveAssignment = (Button) findViewById(R.id.saveAssignment);
        saveAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAssignment(view);
                sqLiteDatabase.close();
                databaseHelper.close();
                Intent direct = new Intent(AddAssignmentActivity.this, CourseActivity.class);
                direct.putExtra("courseId",courseId);
                startActivity(direct);
            }
        });

    }

    public void addAssignment(View view){
        String weekday = Weekday.getText().toString();
        String deliveryTime = DeliveryTime.getText().toString();
        String deliveryType = DeliveryType.getText().toString();
        String nextDelivery = NextDelivery.getText().toString();
        Assignment assignment = new Assignment(courseId, null, deliveryType, 0, weekday, deliveryTime, nextDelivery);

        boolean saved = databaseHelper.createAssignment(assignment, sqLiteDatabase);
        if(saved){
            Toast("Assignment saved");
        }
        else{
            Toast("Something went wrong");
        }
    }

    private void Toast(String message){
        Toast.makeText(context ,message ,Toast.LENGTH_SHORT).show();
    }

}
