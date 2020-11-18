/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code behind for the agent insert information page
 *
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

public class AgentInsertActivity extends AppCompatActivity {

    //declare all the necessary controls.
    EditText etFirstname, etLastName, etMidddleInitial, etBusphone, etEmail, etPosition,etAgencyId;
    String mode;
    DataSource dataSource;
    Agent agent;
    Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_insert);

        //bind the controls to the xml view
        dataSource = new DataSource(this);
        etFirstname = findViewById(R.id.etFirstName);
        etMidddleInitial = findViewById(R.id.etMiddleInitial);
        etLastName = findViewById(R.id.etLastName);
        etBusphone = findViewById(R.id.etBusPhone);
        etEmail = findViewById(R.id.etEmail);
        etPosition = findViewById(R.id.etPosition);
        etAgencyId = findViewById(R.id.etAgencyId);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");//get the intent from main activity with the extra "mode" value
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("doug", "on create successful");

                if(mode.equals("insert"))
                {
                    //create the new agent
                    Agent a = new Agent(0, etFirstname.getText().toString(),etLastName.getText().toString(),etMidddleInitial.getText().toString(),etBusphone.getText().toString(),etEmail.getText().toString(),etPosition.getText().toString(),Integer.parseInt(etAgencyId.getText().toString()));

                    long theResult = dataSource.insertAgent(a);//call the method for the insert operation
                    if (theResult == -1)
                    {
                        Log.d("doug", "insert failed");
                    }
                    else
                    {
                        Log.d("doug", "insert successful");
                    }
                }
            }
        });

        //button for returning to the main activity
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