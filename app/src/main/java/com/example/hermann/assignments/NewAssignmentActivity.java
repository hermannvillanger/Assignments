package com.example.hermann.assignments;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import helper.DatabaseHelper;
import model.Assignment;

/**
 * Created by Hermann on 30.06.2017.
 */

public class NewAssignmentActivity extends Activity {

    EditText Weekday, DeliveryTime, DeliveryType, NextDelivery;
    Context context = this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addassignment);
        Weekday = (EditText) findViewById(R.id.weekday);
        DeliveryTime = (EditText) findViewById(R.id.delivery_time);
        DeliveryType = (EditText) findViewById(R.id.delivery_type);
        NextDelivery = (EditText) findViewById(R.id.next_delivery);

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
