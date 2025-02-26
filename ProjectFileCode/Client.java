package ProjectFileCode;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
    private final String name;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;

   
    public Client(String username ,Socket socket){
     try{    
        this.socket = socket;
        this.name = username;

        //socket will contain the two I\O bufferdstreams
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        }catch(IOException e)
        {shutdowneverything();}
     }
    
    public static void main(String[] args) {
        Scanner inp = new Scanner (System.in);

        System.out.println("write your name :");
        String username = inp.nextLine();
        
        //name validation algorithm "requiers clienthandler to search in clients array"
        try{
            Socket socket = new Socket("localhost" , 1234);
            Client client = new Client(username , socket);
        }catch(IOException e){
            // client socket will be shutteddown with it's buffers
        }
        
       
    }
}
