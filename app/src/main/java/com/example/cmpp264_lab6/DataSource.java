/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code for data the database query operations select, update, insert, delete
 *
 * Author: Doug Cameron
 * Date: Sept, 2020
 */


package com.example.cmpp264_lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DataSource {

    //create a context
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;

    public DataSource(Context context) {


        this.context = context;
        //create helper and open database
        helper = new DBHelper(context);
        Log.d("doug tag", "context: " + context);
        //get the reference to the database
        db = helper.getWritableDatabase();
    }

   //get the agents from database
    public Agent getAgent(int agentId)
    {
        //build sql string for querying all agents
        String sql = "select * from Agents where AgentId=?";
        String [] args = { agentId + ""}; //string array called args
        Cursor cursor = db.rawQuery(sql, args);//cursor is like the result set,this will submit the sql to database putting the arg into the ?
        cursor.moveToNext();   //position cursor on the next/first row
        //create a agent using this row 0
        return new Agent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getInt(7));

    }

    public ArrayList<Agent> getAllAgents()
    {
        ArrayList<Agent> list = new ArrayList<>();
        // string array of columns name in the table
        String [] columns = { "AgentId", "AgtFirstName" , "AgtLastName", "AgtMiddleInitial" ,  "AgtBusPhone", "AgtEmail", "AgtPosition" , "AgencyId"};
        //open a cursor and submit query to the agents table with the columns specified in array
        Cursor cursor = db.query("Agents" , columns,null,null,null,null,null);

        while (cursor.moveToNext())//while there is data to read from cursor
        {
            list.add(new Agent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getInt(7)));
        }
        return list;//return the list of agents
    }
    //method to insert a new agent
    public long insertAgent(Agent agt)   //pass in the agent object
    {
        ContentValues cv = new ContentValues(); //like a key value pair
        cv.put("AgtFirstName", agt.getFirstName());//add column name and value to cv
        cv.put("AgtMiddleInitial", agt.getMiddleInitial());
        cv.put("AgtLastName", agt.getLastName());
        cv.put("AgtBusPhone", agt.getBusinessPhone());
        cv.put("AgtEmail", agt.getEmail());
        cv.put("AgtPosition", agt.getPosition());
        cv.put("AgencyId", agt.getAgencyId());
        return db.insert("Agents", null, cv);//preform insert operation

    }
    //method to update agent
    public long updateAgent(Agent agt)
    {
        ContentValues cv = new ContentValues(); //like a key value pair
        cv.put("AgtFirstName", agt.getFirstName());//add column name and value to cv
        cv.put("AgtMiddleInitial", agt.getMiddleInitial());
        cv.put("AgtLastName", agt.getLastName());
        cv.put("AgtBusPhone", agt.getBusinessPhone());
        cv.put("AgtEmail", agt.getEmail());
        cv.put("AgtPosition", agt.getPosition());
        cv.put("AgencyId", agt.getAgencyId());
        String [] args = {agt.getAgentId( ) + ""};
        String where = "AgentId = ?";
        return db.update("Agents", cv, where, args);//preform the update operation
    }
    //method to delete an agent
    public long deleteAgent(int agtId)
    {
        String [] args = { agtId + ""};
        String where = "AgentId = ?";
        return  db.delete("Agents", where, args);
    }
}
