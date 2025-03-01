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
        ServerSocket ss = new ServerSocket(1111);
        Server server = new Server(ss);
        server.run();
    }

    public void run(){
       try{while(!serverSocket.isClosed()){

            Socket socket = serverSocket.accept();
            System.out.println("new client joined");
            ClientHandler clientHandler = new ClientHandler(socket);
            Thread thread = new Thread(clientHandler);
        }
        }catch(IOException e){e.printStackTrace();}
        
    }
}
