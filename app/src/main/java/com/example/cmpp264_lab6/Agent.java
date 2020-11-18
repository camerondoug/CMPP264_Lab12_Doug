/*
 * CMPP 264 Assignment 12
 * Purpose: This is the code for for defining the agent class
 *
 * Author: Doug Cameron
 * Date: Oct, 2020
 */

package com.example.cmpp264_lab6;

import java.io.Serializable;

public class Agent implements Serializable {

    //define the agent attributes
    private int agentId;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String businessPhone;
    private String email;
    private String position;
    private int agencyId;

    //constructor for agent
    public Agent(int agentId, String firstName, String lastName, String middleInitial, String businessPhone, String email, String position, int agencyId) {
        this.agentId = agentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.businessPhone = businessPhone;
        this.email = email;
        this.position = position;
        this.agencyId = agencyId;
    }

    public Agent(String firstName, String lastName, String businessPhone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.businessPhone = businessPhone;
        this.email = email;
    }

    //getters and setters for agent
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    @Override
    public String toString() {
        return firstName +" " + lastName;
    }
}
