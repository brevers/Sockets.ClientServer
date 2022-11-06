/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Tamara
 */
public class ClientHandler extends Thread{
    private Socket client;
    private MessageParser parser;
    
    
    public ClientHandler(Socket client){
        this.client = client;
        parser = new MessageParser();
        System.out.println("Client connected"+client.getRemoteSocketAddress());
    }

    @Override
    public void run() {
        CheckForClientInAndOutStreams();
    }
    
    public void CheckForClientInAndOutStreams(){
        BufferedReader in = null;
        PrintWriter out = null;
        
        try{
            out = new PrintWriter(
                    client.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            
            //Print the string messages received from the client through the inputStream String line
            String line;
            while((line = in.readLine())!=null){
                System.out.println(line);
                String response = "";
                try {
                    response = parser.CheckForMessage(line);
                    System.out.println(response);
                } catch (IncorrectDateException ex) {
                    out.println("Incorrect date");
                    continue;
                } catch (IncorrectActionException ex) {
                    out.println("Incorrect action");
                    continue;
                }

                if(response.equals("stop")){
                    out.println("You have been disconnected");
                    client.close();
                }else{
                    out.println(response);
                }
            }
            
            
        }catch (IOException e) {
            System.out.println("Client disconnected "+ client.getRemoteSocketAddress());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("Nothing to close");

            }
        }

    }
    
}
