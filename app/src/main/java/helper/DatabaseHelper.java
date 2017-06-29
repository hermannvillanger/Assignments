package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Assignment;

/**
 * Created by Hermann on 25.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "AssignmentManager";

    //Table names
    private static final String TABLE_COURSE = "Course";
    private static final String TABLE_ASSIGNMENT = "Assignment";

    //Common column names
    private static final String C_ID = "COURSE_ID";

    //Course column names
    private static final String C_NAME = "NAME";
    private static final String C_CODE = "COURSE_CODE";

    //Assignment column names
    private static final String A_ID = "ASSIGNMENT_ID";
    private static final String A_TYPE = "DELIVERY_TYPE";
    private static final String A_COMPLETED = "COMPLETED";
    private static final String A_WEEKDAY = "WEEKDAY";
    private static final String A_TIME = "DELIVERY_TIME";
    private static final String A_NEXT = "NEXT_DELIVERY";

//Table create statements
    //Create course
    private static final String CREATE_TABLE_COURSE =
            "CREATE TABLE " + TABLE_COURSE +
            "(" + C_ID + " INTEGER AUTOINCREMENT NOT NULL, " +
            C_NAME + " TEXT, " +
            C_CODE + " TEXT, " +
            "PRIMARY KEY( " + C_ID + " ))";

    //Create assignment
    private static final String CREATE_TABLE_ASSIGNMENT =
            "CREATE TABLE " + TABLE_ASSIGNMENT +
            "(" + C_ID + " INTEGER NOT NULL, " +
            A_ID + " INTEGER AUTOINCREMENT NOT NULL, " +
            A_TYPE + " TEXT, " +
            A_COMPLETED + " INTEGER, " +
            A_WEEKDAY + " TEXT, " +
            A_TIME + " TEXT, " +
            A_NEXT + " TEXT, " +
            "PRIMARY KEY( " + A_ID + " ))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//---------------------'Course' table methods---------------------//
    //Create course
    public long createCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(C_ID, course.getCId());
        values.put(C_CODE, course.getCCode());
        values.put(C_NAME, course.getName());
        //Insert row
        long course_id = db.insert(TABLE_COURSE, null, values);

        return course_id;
    }
    //Get one course
    public Course getCourse(long course_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_COURSE + "WHERE " + C_ID + " = " + course_id;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null){
            c.moveToFirst();
        }
        Course cr = new Course();
        cr.setCId(c.getInt(c.getColumnIndex(C_ID)));
        cr.setCCode(c.getString(c.getColumnIndex(C_CODE)));
        cr.setName(c.getString(c.getColumnIndex(C_NAME)));

        return cr;
    }
    //Get all courses
    public List<Course> getAllCourses(){
        List<Course> courses = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //Looping through all rows and adding to list
        if(c.moveToFirst()){
            do{
                Course cr = new Course();
                cr.setCId(c.getInt(c.getColumnIndex(C_ID)));
                cr.setCCode(c.getString(c.getColumnIndex(C_CODE)));
                cr.setName(c.getString(c.getColumnIndex(C_NAME)));

                courses.add(cr);
            }
            while (c.moveToNext());
        }

        return courses;
    }
    //Update course
    public int updateCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C_CODE, course.getCCode());
        values.put(C_NAME, course.getName());
        //updating row

        return db.update(TABLE_COURSE, values, C_ID + " = ?",
                new String[] {String.valueOf(course.getCId())});
    }
    //Delete course, also deletes all assignments to a course
    public void deleteCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();

        //Get all Assignments
        List<Assignment> assignments = getAllAssignmentsByCourse(course.getCId());

        //Delete all assignments
        for(Assignment assignment : assignments){
            deleteAssignment(assignment.getAssignment_Id());
        }
        //Delete course
        db.delete(TABLE_COURSE, C_ID + " = ?",
                new String[] {String.valueOf(course.getCId())});
    }

    //---------------------'Assignment' table methods---------------------//
    //Create assignment
    public long createAssignment(Assignment assignment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(C_ID, assignment.getCourse_Id());
        values.put(A_ID, assignment.getAssignment_Id());
        values.put(A_TYPE, assignment.getDelivery_Type());
        values.put(A_COMPLETED, assignment.getCompleted());
        values.put(A_WEEKDAY, assignment.getWeekday());
        values.put(A_TIME, assignment.getDelivery_Time());
        values.put(A_NEXT, assignment.getNext_Delivery());

        //Insert row
        long assignment_id = db.insert(TABLE_ASSIGNMENT, null, values);

        return assignment_id;
    }
    //Get one assignment
    public Assignment getAssignment(long assignment_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT + "WHERE " +
                A_ID + " = " + assignment_id ;
        Log.e(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null){
            c.moveToFirst();
        }
        Assignment asg = new Assignment();

        asg.setCourse_Id(c.getInt(c.getColumnIndex(C_ID)));
        asg.setAssignment_Id(c.getInt(c.getColumnIndex(A_ID)));
        asg.setDelivery_Type(c.getString(c.getColumnIndex(A_TYPE)));
        asg.setCompleted(c.getInt(c.getColumnIndex(A_COMPLETED)));
        asg.setWeekday(c.getString(c.getColumnIndex(A_WEEKDAY)));
        asg.setDelivery_Time(c.getString(c.getColumnIndex(A_TIME)));
        asg.setNext_Delivery(c.getString(c.getColumnIndex(A_NEXT)));

        return asg;
    }
    //Get all assignment
    public List<Assignment> getAllAssignments(){
        List<Assignment> assignments = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //Looping through all rows and adding to list
        if(c.moveToFirst()){
            do{
                Assignment asg = new Assignment();

                asg.setCourse_Id(c.getInt(c.getColumnIndex(C_ID)));
                asg.setAssignment_Id(c.getInt(c.getColumnIndex(A_ID)));
                asg.setDelivery_Type(c.getString(c.getColumnIndex(A_TYPE)));
                asg.setCompleted(c.getInt(c.getColumnIndex(A_COMPLETED)));
                asg.setWeekday(c.getString(c.getColumnIndex(A_WEEKDAY)));
                asg.setDelivery_Time(c.getString(c.getColumnIndex(A_TIME)));
                asg.setNext_Delivery(c.getString(c.getColumnIndex(A_NEXT)));

                assignments.add(asg);
            }
            while (c.moveToNext());
        }
        return assignments;
    }
    //Get all assignments by course id
    public List<Assignment> getAllAssignmentsByCourse(long course_id){
        List<Assignment> assignments = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ASSIGNMENT + " WHERE " +
                C_ID + " = " + course_id;

        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if(c.moveToFirst()){
            do{
                Assignment asg = new Assignment();

                asg.setCourse_Id(c.getInt(c.getColumnIndex(C_ID)));
                asg.setAssignment_Id(c.getInt(c.getColumnIndex(A_ID)));
                asg.setDelivery_Type(c.getString(c.getColumnIndex(A_TYPE)));
                asg.setCompleted(c.getInt(c.getColumnIndex(A_COMPLETED)));
                asg.setWeekday(c.getString(c.getColumnIndex(A_WEEKDAY)));
                asg.setDelivery_Time(c.getString(c.getColumnIndex(A_TIME)));
                asg.setNext_Delivery(c.getString(c.getColumnIndex(A_NEXT)));

                assignments.add(asg);
            }
            while (c.moveToNext());
        }
        return assignments;
    }
    //Update assignment
    public int updateAssignment(Assignment assignment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(A_TYPE, assignment.getDelivery_Type());
        values.put(A_COMPLETED, assignment.getCompleted());
        values.put(A_WEEKDAY, assignment.getWeekday());
        values.put(A_TIME, assignment.getDelivery_Time());
        values.put(A_NEXT, assignment.getNext_Delivery());
        //updating row

        return db.update(TABLE_ASSIGNMENT, values, A_ID + " = ?",
                new String[] {String.valueOf(assignment.getAssignment_Id())});
    }
    //Delete assignment
    public void deleteAssignment(long assignment_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSIGNMENT, A_ID + " = ?", new String[] {String.valueOf(assignment_id)});
    }

    //Close database connection
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen()){
            db.close();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older tables
        db.execSQL("DROP TABLES IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLES IF EXISTS " + TABLE_ASSIGNMENT);

        // create new tables
        onCreate(db);
    }

}
