package com.example.daiya.sheba;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //user details table
    private static final String DATABASE_NAME = "login.db";
    private static final String USER_DETAILS = "user_details";
    private static final String USER_ID = "Id";
    private static final String USER_EMAIL = "Email";
    private static final String USER_NAME = "Name";
    private static final String PASSWORD = "Password";
    private static final String USER_PHONE = "Phone";
    private static final int DATABASE_VERSION_NO = 1;
    private static final String CREATE_USER_DETAILS_TABLE = "CREATE TABLE " + USER_DETAILS + "("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ USER_EMAIL + " VARCHAR(40)," + USER_NAME + " VARCHAR(40)," + PASSWORD + " VARCHAR(25)," + USER_PHONE + " VARCHAR(15));";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_DETAILS;
    private Context context;


    //car wash table
    private static final String CAR_WASH_DETAILS = "car_wash_details";
    private static final String CARWASH_ID = "Id";
    private static final String CARWASH_EMAIL = "Email";
    private static final String CARWASH_NAME = "Name";
    private static final String HOME = "Home";
    private static final String WORKSHOP = "Workshop";
    private static final String INTERIOR = "Interior";
    private static final String EXTERIOR = "Exterior";
    private static final String ENGINE = "Engine";
    private static final String CARWASH_PHONE = "Phone";
    private static final String CREATE_CARWASH_DETAILS_TABLE = "CREATE TABLE " + CAR_WASH_DETAILS + "("+CARWASH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ CARWASH_NAME + " VARCHAR(40),"+ CARWASH_EMAIL + " VARCHAR(40),"+ WORKSHOP + " VARCHAR(10),"+ INTERIOR + " VARCHAR(10),"+ EXTERIOR + " VARCHAR(10),"+ ENGINE + " VARCHAR(10)," + HOME + " VARCHAR(15)," + CARWASH_PHONE + " VARCHAR(15));";
    private static final String DROP_CARWASH_TABLE = "DROP TABLE IF EXISTS " + CAR_WASH_DETAILS;


    //car rent table
    private static final String CAR_RENT_DETAILS_TABLE = "car_Rent_details";
    private static final String CARRENT_ID = "Id";
    private static final String CARRENT_EMAIL = "Email";
    private static final String CARRENT_NAME = "Name";
    private static final String SEDAN = "Sedan";
    private static final String NOAH = "Noah";
    private static final String HIACE = "Hiace";
    private static final String CARRENT_PHONE = "Phone";
    private static final String CREATE_CARRENT_DETAILS_TABLE = "CREATE TABLE " + CAR_RENT_DETAILS_TABLE + "("+CARRENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ CARRENT_NAME + " VARCHAR(40),"+ CARRENT_EMAIL + " VARCHAR(40),"+ SEDAN + " VARCHAR(10),"+ NOAH + " VARCHAR(10),"+ HIACE + " VARCHAR(10),"+ CARRENT_PHONE + " VARCHAR(15));";
    private static final String DROP_CARRENT_TABLE = "DROP TABLE IF EXISTS " + CAR_RENT_DETAILS_TABLE;


    //driver table
    private static final String DRIVER_DETAILS_TABLE = "driver_details";
    private static final String DRIVER_ID = "Id";
    private static final String DRIVER_EMAIL = "Email";
    private static final String DRIVER_NAME = "Name";
    private static final String BEFORE = "Before";
    private static final String AFTER = "After";
    private static final String CATEGORY = "Category";
    private static final String DRIVER_PHONE = "Phone";
    private static final String CREATE_DRIVER_DETAILS_TABLE = "CREATE TABLE " + DRIVER_DETAILS_TABLE + "("+DRIVER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ CARRENT_NAME + " VARCHAR(40),"+ CARRENT_EMAIL + " VARCHAR(40),"+ SEDAN + " VARCHAR(10),"+ NOAH + " VARCHAR(10),"+ HIACE + " VARCHAR(10),"+ CARRENT_PHONE + " VARCHAR(15));";
    private static final String DROP_DRIVER_TABLE = "DROP TABLE IF EXISTS " + DRIVER_DETAILS_TABLE;

    //tutor table
    private static final String TUTOR_DETAILS_TABLE = "Tutor_details";
    private static final String TUTOR_ID = "Id";
    private static final String TUTOR_EMAIL = "Email";
    private static final String TUTOR_NAME = "Name";
    private static final String STUDY = "Study";
    private static final String EXPERIENCE = "Experience";
    private static final String CLASS = "Class";
    private static final String SUBJECT = "Subjects";
    private static final String FEES = "Fees";
    private static final String TUTOR_PHONE = "Phone";
    private static final String CREATE_TUTOR_DETAILS_TABLE = "CREATE TABLE " + TUTOR_DETAILS_TABLE + "("+TUTOR_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ TUTOR_NAME + " VARCHAR(40),"+ TUTOR_EMAIL + " VARCHAR(40),"+ STUDY + " VARCHAR(40),"+ EXPERIENCE + " VARCHAR(40),"+ CLASS + " VARCHAR(40),"+ SUBJECT + " VARCHAR(100)," + FEES + " VARCHAR(15)," + TUTOR_PHONE + " VARCHAR(15));";
    private static final String DROP_TUTOR_TABLE = "DROP TABLE IF EXISTS " + TUTOR_DETAILS_TABLE;


    //shifting table
    private static final String SHIFTING_DETAILS_TABLE = "Shifting_details";
    private static final String SHIFTING_ID = "Id";
    private static final String SHIFTING_EMAIL = "Email";
    private static final String SHIFTING_NAME = "Name";
    private static final String ONETO3 = "one";
    private static final String FOURTO5 = "four";
    private static final String SIXTO8 = "six";
    private static final String VAN = "Van";
    private static final String PICKUP = "Pickup";
    private static final String TRUCK = "Truck";
    private static final String SHIFTING_PHONE = "Phone";
    private static final String CREATE_SHIFTING_DETAILS_TABLE = "CREATE TABLE " + SHIFTING_DETAILS_TABLE + "("+SHIFTING_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ CARWASH_NAME + " VARCHAR(40),"+ CARWASH_EMAIL + " VARCHAR(40),"+ WORKSHOP + " VARCHAR(10),"+ INTERIOR + " VARCHAR(10),"+ TRUCK + " VARCHAR(10),"+ EXTERIOR + " VARCHAR(10),"+ ENGINE + " VARCHAR(10)," + HOME + " VARCHAR(15)," + CARWASH_PHONE + " VARCHAR(15));";
    private static final String DROP_SHIFTING_TABLE = "DROP TABLE IF EXISTS " + SHIFTING_DETAILS_TABLE;



    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION_NO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "On Create is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_USER_DETAILS_TABLE);
            db.execSQL(CREATE_CARWASH_DETAILS_TABLE);
            db.execSQL(CREATE_CARRENT_DETAILS_TABLE);
            db.execSQL(CREATE_DRIVER_DETAILS_TABLE);
            db.execSQL(CREATE_TUTOR_DETAILS_TABLE);
            db.execSQL(CREATE_SHIFTING_DETAILS_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Exception create: " + e, Toast.LENGTH_LONG).show();
           // context.deleteDatabase("login.db");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_CARWASH_TABLE);
            db.execSQL(DROP_CARRENT_TABLE);
            db.execSQL(DROP_DRIVER_TABLE);
            db.execSQL(DROP_TUTOR_TABLE);
            db.execSQL(DROP_SHIFTING_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Toast.makeText(context, "Exception upgrade: " + e, Toast.LENGTH_SHORT).show();
        }


    }
    //insert data to user details table
    public long insertDataUser(String name, String email, String password, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, name);
        contentValues.put(USER_EMAIL, email);
        contentValues.put(PASSWORD, password);
        contentValues.put(USER_PHONE, phone);
        long rowID = sqLiteDatabase.insert(USER_DETAILS, null, contentValues);
        return rowID;
    }

    //insert data to carwash table
    public long insertDataCarWash(String name, String email,String phone,String home,String work,String interior,String exterior,String engine) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARWASH_NAME, name);
        contentValues.put(CARWASH_EMAIL, email);
        contentValues.put(CARWASH_PHONE, phone);
        contentValues.put(HOME, home);
        contentValues.put(WORKSHOP, work);
        contentValues.put(INTERIOR, interior);
        contentValues.put(EXTERIOR, exterior);
        contentValues.put(ENGINE, engine);
        long rowID = sqLiteDatabase.insert(CAR_WASH_DETAILS, null, contentValues);
        return rowID;
    }


    //insert data to car rent table
    public long insertDataCarRent(String name, String email,String phone,String sedan,String noah,String hiace) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARRENT_NAME, name);
        contentValues.put(CARRENT_EMAIL, email);
        contentValues.put(CARRENT_PHONE, phone);
        contentValues.put(SEDAN, sedan);
        contentValues.put(NOAH, noah);
        contentValues.put(HIACE, hiace);
        long rowID = sqLiteDatabase.insert(CAR_RENT_DETAILS_TABLE, null, contentValues);
        return rowID;
    }

    //insert data to driver table
    public long insertDataDriver(String name, String email,String phone,String befor,String after,String category) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARRENT_NAME, name);
        contentValues.put(CARRENT_EMAIL, email);
        contentValues.put(CARRENT_PHONE, phone);
        contentValues.put(SEDAN, befor);
        contentValues.put(NOAH, after);
        contentValues.put(HIACE, category);
        long rowID = sqLiteDatabase.insert(DRIVER_DETAILS_TABLE, null, contentValues);
        return rowID;
    }

    //insert data to tutor table
    public long insertDataTutor(String name, String email,String phone,String study,String exp,String clasS,String subj,String fees) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TUTOR_NAME, name);
        contentValues.put(TUTOR_EMAIL, email);
        contentValues.put(TUTOR_PHONE, phone);
        contentValues.put(STUDY, study);
        contentValues.put(EXPERIENCE, exp);
        contentValues.put(CLASS, clasS);
        contentValues.put(SUBJECT, subj);
        contentValues.put(FEES, fees);
        long rowID = sqLiteDatabase.insert(TUTOR_DETAILS_TABLE, null, contentValues);
        return rowID;
    }


    //insert data to shifting table
    public long insertDataShifting(String name, String email,String phone,String van,String pickup,String truck,String first,String second,String third) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CARWASH_NAME, name);
        contentValues.put(CARWASH_EMAIL, email);
        contentValues.put(CARWASH_PHONE, phone);
        contentValues.put(WORKSHOP, van);
        contentValues.put(HOME, pickup);
        contentValues.put(TRUCK, truck);
        contentValues.put(INTERIOR, first);
        contentValues.put(EXTERIOR, second);
        contentValues.put(ENGINE, third);
        long rowID = sqLiteDatabase.insert(SHIFTING_DETAILS_TABLE, null, contentValues);
        return rowID;
    }


    //fetch data of user details table

    public Cursor getDatasofUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + USER_DETAILS,null);
        return data;
    }

    //fetch data of car wash table

    public Cursor getDatasofCarWash(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + CAR_WASH_DETAILS,null);
        return data;
    }

    //fetch data of car rent table

    public Cursor getDatasofCarRent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + CAR_RENT_DETAILS_TABLE,null);
        return data;
    }


    //fetch data of driver table

    public Cursor getDatasofDriver(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + DRIVER_DETAILS_TABLE,null);
        return data;
    }


    //fetch data of shifting table

    public Cursor getDatasofShifting(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + SHIFTING_DETAILS_TABLE,null);
        return data;
    }


    //fetch data of tutor table

    public Cursor getDatasofTutor(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TUTOR_DETAILS_TABLE,null);
        return data;
    }

}