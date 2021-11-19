/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Tamara
 */


public class Client {
    //connection
    public static final int PORT = 12345;
    public static String HOST = "";
    private static Socket clientSocket;
    public static boolean connected = true;
    
    public BufferedReader in;
    public PrintWriter out;
    
    
    //run main
    public static void main(String[] args) {
        Client client = new Client();
        client.ConnectToServer();
    }
    
    public void ConnectToServer(){  
        //Find the address of the local host
        try {
            HOST = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            System.out.println("Host not found");
        }
        
        //Try to connect to th3 address
        try{
            clientSocket = new Socket(HOST, PORT);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            Scanner scanner = new Scanner(System.in);
            String message;
            while(connected){
                System.out.println("\n-To add a new item do:    add;date;name;location  \n"
                        + "-To list all items do:      list;date\n"
                        + "   Date format is: XX MONTH Year. (e.g.= 20 December 2021)\n"
                        + "-To disconnect do: stop\n"
                        + "-------------------------------------------------\n");
                
                message = scanner.nextLine();
                SendMessage(message);
                if(message.equalsIgnoreCase("stop")){
                    break;
                }
            }
            
        }catch(IOException e){
            System.out.println("Unable to connect to server");
        }
    }
    
    public void SendMessage(String message) throws IOException{
        out.println(message);
        out.flush();
        
        System.out.println("\nServer response: "+in.readLine());
    }
}
