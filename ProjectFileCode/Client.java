package ProjectFileCode;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class Client {
    private String name;
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
        
        }catch(IOException e){
            ShutdownEverything();
        }
     }
    
    public static void main(String[] args)throws IOException {
        Scanner inp = new Scanner (System.in);

        System.out.println("write your name :");
        String username = inp.nextLine();
        
        //name validation algorithm "requiers clienthandler to search in clients array"
        
            Socket socket = new Socket("localhost" , 1111);
            Client client = new Client(username , socket);

            client.Listentomsg();
            client.sendmessage();
        
    }
    
    public void Listentomsg(){

        new Thread(new Runnable(){ //creates a new thread for listening 
            public void run(){
                String msg ;
        
                while(socket.isConnected()){ // if not connected it will not recive any msg
                    try {
                        msg = bufferedReader.readLine();
                        System.out.println(msg);
                    } 
                    catch (IOException e) {
                        // CONSIDER LATER WHAT EXCEPTION SHIULD BE HERE
                    }
                }
            }
        }).start();
    }

    public void sendmessage(){
        Scanner inp = new Scanner(System.in);
        String msgToSend ;

       try {
        bufferedWriter.write(name);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        while(socket.isConnected()){
            msgToSend = inp.nextLine();

            bufferedWriter.write(name+": "+msgToSend); 
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
        }


       } catch (Exception e) {
        // CONSIDER LATER WHAT EXCEPTION SHIULD BE HERE
       }
    }
    
    

    public void ShutdownEverything(){
        System.out.println("holyshit system is down"); //u gotta change this later LOL
    }
    
}
