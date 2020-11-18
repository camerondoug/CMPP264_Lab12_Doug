/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code for main activity that opens when the app launches
 *A list of agents will be displayed in a listview from which one can be selected
 * Author: Doug Cameron
 * Date: Sept, 2020
 */

package com.example.cmpp264_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lvAgents;
    //ArrayList<Agent> agents;
    DataSource dataSource; //declare a new data source
    Button btnAdd;
    ArrayAdapter<Agent> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DataSource(this);//create a new datasource using code in datasource file
        btnAdd = findViewById(R.id.btnAdd);

        lvAgents = findViewById((R.id.lvAgents)); //the listview for the agents


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgentInsertActivity.class);
                intent.putExtra("mode", "insert");
                startActivity(intent);
            }
        });
        //set up to and from arrays for simple adapter
/*        String [] from = {"fname", "lname", "midInitial"}; //these are the hash map items

        int [] to = {R.id.tvFirstName, R.id.tvLastName, R.id.tvMiddleInitial};//reference to controls in the listview layout
        //"this" is pointing to the main activity
        SimpleAdapter adapter = new SimpleAdapter(this, loadData(), R.layout.listview_layout, from, to);
        //display the adapter data in the listview
        lvAgents.setAdapter(adapter);
*/
        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //point at the item that recieved the click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create an intent to start the info activity
                Intent intent = new Intent(getApplicationContext(),AgentContactActivity.class);

                //get the course object from the position that was clicked
                //Agent agent = agents.get(position);

                //put data object into the intent and send to the info activity
                //seriallzable must select in the putextra
                intent.putExtra("mode", "update");
                intent.putExtra("agent", adapter.getItem(position));
                startActivity(intent);
            }
        });
        //load all products from the products table
        loadData();
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    //method to get the agents data. I was experimenting with hash maps
    private void loadData() {
        //hash map with strings for key and values

/*        agents = new ArrayList<>();
        agents.add( new Agent(1,"Tom","Smith", "I", "403-555-5555", "tomsmith@hotmail.com", "Senior Agent", 1));
        agents.add( new Agent(1,"Hank","Hill", "J", "403-555-6677", "hankhill@hotmail.ca", "Junior Agent", 1));

        //set up arraylist to map each course to the listview item
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (Agent a : agents){
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("fname", a.getFirstName());
        newMap.put("lname", a.getLastName());
        newMap.put("midInitial", a.getMiddleInitial());
        data.add(newMap);
        }
        return data;*/
        //set the adapter to an array of agents retrieved using the getAllAgents method
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, dataSource.getAllAgents());
        //bind the adapter to the listview
        lvAgents.setAdapter(adapter);

    }
}