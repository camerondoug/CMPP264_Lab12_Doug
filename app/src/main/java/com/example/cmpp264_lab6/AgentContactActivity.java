/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code behind for the agent contact information page
 * and displays the form and handles the update operation
 * Author: Doug Cameron
 * Date: Sept, 2020
 */

package com.example.cmpp264_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgentContactActivity extends AppCompatActivity {

    //declare all the necessary controls.
    EditText etFirstname, etLastName, etBusphone, etEmail;
    String mode;
    DataSource dataSource;
    Agent agent;
    Button btnSave, btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_contact);

        //bind the controls to the xml view
        dataSource = new DataSource(this);
        etFirstname = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBusphone = findViewById(R.id.etBusPhone);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();

        mode = intent.getStringExtra("mode"); //get the intent from main activity with the extra "mode" value
        if (mode.equals("update"))
        {
            //this will get the intent that was named "agent" in the MainActivity
            agent = (Agent) intent.getSerializableExtra("agent");
            etFirstname.setText(agent.getFirstName());  //set the text fields using the agent object
            etLastName.setText(agent.getLastName());
            etBusphone.setText(agent.getBusinessPhone());
            etEmail.setText(agent.getEmail());

        }

        btnSave.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("doug", "on create successful");
                if(mode.equals("update"))
                {
                    //create the new agent object for updating
                    Agent a = new Agent(agent.getAgentId(), etFirstname.getText().toString(),etLastName.getText().toString() ,agent.getMiddleInitial(), etBusphone.getText().toString() ,etEmail.getText().toString(),agent.getPosition(),agent.getAgencyId());
                    if (dataSource.updateAgent(a) == -1) //make the call to the update method in datasource
                    {
                        Log.d("doug", "update failed");
                    }
                    else
                    {
                        Log.d("doug", "update successful");
                    }
                }
            }
        }));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dataSource.deleteAgent(agent.getAgentId()) == -1) // call the method for deleting from the datasource
                {
                    Log.d("doug", "delete failed");
                }
                else
                {
                    Log.d("doug", "delete successful");
                }
            }
        });

        //method to go back to the main activity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mode", "insert");
                startActivity(intent);
            }
        });

    }
}