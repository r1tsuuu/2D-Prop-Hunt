
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static ServerSocket server;
    public static void main(String[] args){
        try {
            server = new ServerSocket(4952);    
            Socket s = server.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            String message = in.readUTF();
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }
}