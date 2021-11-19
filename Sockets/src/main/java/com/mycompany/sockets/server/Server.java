/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Tamara
 */
public class Server {
    public static final int PORT = 12345;
    private static ServerSocket serverSocket;
    public static boolean listening;
    
    public static void main(String[] args) {
        InitializeServer();
        RunServer();
    }
    
    
    public Server(){
        this.listening = false;
        this.serverSocket = null;
    }
    
    public static void InitializeServer(){
        listening = true;
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException e){
            System.out.println("Unable to attach to port "+PORT);
        }
    }
    
    public static void RunServer(){
        while(listening){
            Socket client = null;
            try{
                client = serverSocket.accept();
                new ClientHandler(client).start();
            }catch(IOException e){
                System.out.print("Unable to accept client connection");     
            }
        }
    }
}
