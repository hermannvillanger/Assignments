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

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assignment);
        context = getBaseContext();

        Weekday = findViewById(R.id.weekday);
        DeliveryTime = findViewById(R.id.delivery_time);
        DeliveryType = findViewById(R.id.delivery_type);
        NextDelivery = findViewById(R.id.next_delivery);

        saveAssignment = (Button) findViewById(R.id.saveAssignment);
        saveAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAssignment(view);
                Intent direct = new Intent(AddAssignmentActivity.this, CourseActivity.class);
                startActivity(direct);
            }
        });

    }

    public void addAssignment(View view){
        String weekday = Weekday.getText().toString();
        String deliveryTime = DeliveryTime.getText().toString();
        String deliveryType = DeliveryType.getText().toString();
        String nextDelivery = NextDelivery.getText().toString();
        Assignment assignment = new Assignment(null, null, deliveryType, 0, weekday, deliveryTime, nextDelivery);
//TODO må hente course id fra course assignment er knyttet til. 0 pga false ie ikke levert. Første nullverdi

        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        databaseHelper.createAssignment(assignment, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Course Saved", Toast.LENGTH_SHORT).show();
        databaseHelper.close();
    }
}
