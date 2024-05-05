
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static ServerSocket server;
    public static void main(String[] args){
        System.out.println("Server starting");
        try {
            server = new ServerSocket(4952);    
            System.out.println("Server waiting");
            Socket s = server.accept();
            System.out.println("client connected");
            String message = "";
            while (!message.equals("stop")) {
                DataInputStream in = new DataInputStream(s.getInputStream());
                message = in.readUTF();
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }
}