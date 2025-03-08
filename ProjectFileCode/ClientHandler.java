package ProjectFileCode;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> ClientsConnected = new ArrayList<>();

    private String username ;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public ClientHandler(Socket socket){
        try {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
       
        this.username = bufferedReader.readLine(); // username i'll be saved when sendMessage() is called for first time
        ClientsConnected.add(this);

        broadcast("Server: "+username+" has joined the chat");

        } catch (Exception e) {
           // CONSIDER LATER WHAT EXCEPTION SHIULD BE HERE
        }
    }

    public void run(){
        String msg ;

        try {
            while(socket.isConnected()){
            msg = bufferedReader.readLine(); 

            broadcast(msg);

        }
        } catch (IOException e) {
            // CONSIDER LATER WHAT EXCEPTION SHIULD BE HERE
        }
    }

    public void broadcast(String msg){
        try {
            for(ClientHandler clientHandler : ClientsConnected){
            
           
                clientHandler.bufferedWriter.write(msg);
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush(); 
                // .flush(); forces the buffer to write everything stored in the buffer instantly
            
                
        }
        } catch (Exception e) {
           // CONSIDER LATER WHAT EXCEPTION SHIULD BE HERE
        }
        
        
    }

    
    //https://www.youtube.com/watch?v=xBNBIDFP84A&list=PLoW9ZoLJX39Xcdaa4Dn5WLREHblolbji4&index=3
    //now here u will handle the serversocket and the buffers
}
