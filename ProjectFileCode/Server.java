package ProjectFileCode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException{
        
        System.out.println("Server is waiting for clients");
        ServerSocket ss = new ServerSocket(1111);
        Socket socket = ss.accept();
        System.out.println("a new client joined the server !");
    }
}
