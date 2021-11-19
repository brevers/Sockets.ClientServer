/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockets.server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author Tamara
 */
public class MessageParser {
        
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.UK);
    
     public MessageParser(){    
     }
     
    public String CheckForMessage(String message) throws IncorrectDateException, IncorrectActionException{
        String[] splitMessage = message.split(";");
        int length = splitMessage.length;
                
        //Check for exit command
        if(length == 1){
            if(splitMessage[0].equalsIgnoreCase("stop")){
                return "stop";
            }else{
                throw new IncorrectActionException();
            }
        }
        
        //Check for list command
        if(length == 2){
            if(splitMessage[0].equalsIgnoreCase("list")){
               LocalDate date =  toDate(splitMessage[1]);
               if(date!=null){
                   return DataStorage.getInstance().list(date);
               }else{
                   throw new IncorrectDateException();
               }
                
            }else{
                throw new IncorrectActionException();
            }
        }
        
        //Check for add command
        if(length == 4){
            if(splitMessage[0].equalsIgnoreCase("add")){
               LocalDate date =  toDate(splitMessage[1]);
                    
               if(date!=null){
                   ToDoItem item = new ToDoItem(date,splitMessage[2], splitMessage[3]);
                   return DataStorage.getInstance().add(item);
               }else{
                   throw new IncorrectDateException();
               }
            }else{
                throw new IncorrectActionException();
            }
        }
        
        throw new IncorrectActionException();
    } 
    
   
    private LocalDate toDate(String dateString){
        try{
            return LocalDate.parse(dateString,formatter);
        }catch(DateTimeParseException e){
            return null;
        } 
    }
    
}
