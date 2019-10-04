package com.e.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contacts_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Contact.CREATE_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
    public void deletetable(String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ table);
    }
    public boolean isEmpty(String TableName){
        SQLiteDatabase database = this.getReadableDatabase();
        int NoOfRows = (int) DatabaseUtils.queryNumEntries(database,TableName);
        if (NoOfRows == 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean isTableExists(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isExist = false;
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }

    public void insertContact(Contact contactmodel) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contact.COLUMN_NO, contactmodel.getNo());
        values.put(Contact.COLUMN_NAME, contactmodel.getName());
        values.put(Contact.COLUMN_PHONENUMBER, contactmodel.getPhoneNumber());
        values.put(Contact.COLUMN_IDNUMBER, contactmodel.getIdnumber());
        // insert row
        long id = db.insert(Contact.TABLE_NAME, null, values);

        // close db connection
        db.close();
        Log.d("Ket qua", "addStudent Successfuly");
        // return newly inserted row id

    }

    public Contact getContact(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Contact.TABLE_NAME,
                new String[]{Contact.COLUMN_ID, Contact.COLUMN_NO, Contact.COLUMN_NAME, Contact.COLUMN_PHONENUMBER,Contact.COLUMN_IDNUMBER},
                Contact.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
                Contact contactitem= new Contact(
                cursor.getInt(cursor.getColumnIndex(Contact.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Contact.COLUMN_PHONENUMBER)),
                cursor.getString(cursor.getColumnIndex(Contact.COLUMN_NO)),
                cursor.getString(cursor.getColumnIndex(Contact.COLUMN_IDNUMBER)));

        // close the db connection
        cursor.close();

        return contactitem;
    }

    public List<Contact> getAllContacts() {
        List<Contact> listcontact = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Contact.TABLE_NAME + " ORDER BY "   +Contact.COLUMN_NO + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contactmodel = new Contact();
                contactmodel.setId(cursor.getInt(cursor.getColumnIndex(contactmodel.COLUMN_ID)));
                contactmodel.setIdnumber(cursor.getString(cursor.getColumnIndex(contactmodel.COLUMN_IDNUMBER)));
                contactmodel.setName(cursor.getString(cursor.getColumnIndex(contactmodel.COLUMN_NAME)));
                contactmodel.setNo(cursor.getString(cursor.getColumnIndex(contactmodel.COLUMN_NO)));
                contactmodel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(contactmodel.COLUMN_PHONENUMBER)));

                listcontact.add(contactmodel);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return  list
        return listcontact;
    }


    public int getConactcount() {
        String countQuery = "SELECT  * FROM " + Contact.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateSong(Contact contactnodel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.COLUMN_NO, contactnodel.getNo());
        values.put(Contact.COLUMN_NAME, contactnodel.getName());
        values.put(Contact.COLUMN_PHONENUMBER, contactnodel.getPhoneNumber());
        values.put(Contact.COLUMN_IDNUMBER, contactnodel.getIdnumber());
        // updating row
        return db.update(Contact.TABLE_NAME, values, Contact.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactnodel.getId())});
    }

    public void deleteNote(Contact contactnodel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactnodel.getId())});
        db.close();
    }
}

