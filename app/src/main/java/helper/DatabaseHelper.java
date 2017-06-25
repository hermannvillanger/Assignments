package helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            A_TYPE + " TEXT, " +
            A_COMPLETED + " BOOLEAN, " +
            A_WEEKDAY + " TEXT NOT NULL, " +
            A_TIME + " TEXT, " +
            A_NEXT + " TEXT, " +
            "PRIMARY KEY( " + C_ID + "," + A_WEEKDAY + " ))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

//TODO Oppretting av Course og Assignment



}
