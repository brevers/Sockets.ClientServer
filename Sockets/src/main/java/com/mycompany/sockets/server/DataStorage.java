/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockets.server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tamara
 */

//Singleton
public class DataStorage {
    
    private List<ToDoItem> items;
    private static DataStorage instance;
    
    public DataStorage(){
       items = Collections.synchronizedList (new ArrayList<ToDoItem>());
    }
    
    public synchronized String add(ToDoItem item){
        items.add(item);
        return item.toString();
    }
    
    public synchronized String list(LocalDate date){
       return "On "+ date.format(MessageParser.formatter)+" This is your list: " + items.stream().filter(item->item.getDate().isEqual(date)).map(item->item.toString()).collect(Collectors.joining(";"));
    }
    
    public static DataStorage getInstance(){
        if(instance == null){
            instance = new DataStorage();
        }
        
        return instance;
    }
    
    
    
}
