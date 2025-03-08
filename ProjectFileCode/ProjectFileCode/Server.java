package ProjectFileCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public static void main(String[] args) throws IOException{
        
        System.out.println("Server is waiting for clients");
        ServerSocket ss = new ServerSocket(1111); // serversocket "listener" is made to listen on port 1111
        Server server = new Server(ss);// server have the listener to start functioing
        server.run(); 
    }

    public void run(){ //here where listener is accpeting responds and passes them to clienthandler
       try{
        while(!serverSocket.isClosed()){

            Socket socket = serverSocket.accept();
            System.out.println("new client joined"); //add repreasenting name on server.java output 
            ClientHandler clientHandler = new ClientHandler(socket);
            Thread thread = new Thread(clientHandler);
            thread.start();
        }
        }catch(IOException e){e.printStackTrace();}
        
    }
}
