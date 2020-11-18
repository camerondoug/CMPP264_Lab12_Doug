/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code for database helper code
 *where the database connect is set
 * Author: Doug Cameron
 * Date: Sept, 2020
 */

package com.example.cmpp264_lab6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int version = 3;
    private static final String name = "TravelExpertsSqlLite.db";

    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    //a table can be created with data. This was done at the beginning or the testing
    //but later I uploaded the full database
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("doug", "creating database");
        //fill this in later
        String sql = "CREATE TABLE Agents (" +
                "`AgentId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "`AgtFirstName` VARCHAR(20)," +
                "`AgtMiddleInitial` VARCHAR(5)," +
                "`AgtLastName` VARCHAR(20)," +
                "`AgtBusPhone` VARCHAR(20)," +
                "`AgtEmail` VARCHAR(50)," +
                "`AgtPosition` VARCHAR(20)," +
                "`AgencyId` INTEGER"+
                ")";
        //db.execSQL(sql);
        sql = "insert into Agents values (1,'Joe', 'Smith', 'I', '404-555-5555', 'joesmith@hotmail.ca', 'Agent', 1)";
        String sql2 = "insert into Agents values (2,'John', 'Doe', 'I', '404-555-5555', 'joesmith@hotmail.ca', 'Agent', 1)";
        //db.execSQL(sql);
        //db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("doug", "upgrading database, old version: " + oldVersion + " newversion: " + newVersion);
        //db.execSQL("drop table Agents");
        //onCreate(db);
    }
}
