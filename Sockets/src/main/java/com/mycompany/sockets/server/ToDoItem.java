/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockets.server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Tamara
 */
public class ToDoItem {
    
    LocalDate date;
    String name;
    String location;

    public ToDoItem(LocalDate date,String name,  String location) {
        this.date = date;
        this.name = name;
        this.location = location;
    }
    
  //setters and getters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public String toString() {
        return " List: "+name+" "+location+", ";
    }

}
