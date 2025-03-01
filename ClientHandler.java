package ProjectFileCode;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class ClientHandler {

    public ArrayList<ClientHandler> ClientsConnected = new ArrayList<>();

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

        } catch (Exception e) {
           ShutdownEverything(socket , bufferedReader , bufferedWriter);
        }

       
    }

    
    //https://www.youtube.com/watch?v=xBNBIDFP84A&list=PLoW9ZoLJX39Xcdaa4Dn5WLREHblolbji4&index=3
    //now here u will handle the serversocket and the buffers
}
