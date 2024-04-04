
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    public static void main(String[] args){
        try {
            System.out.println("Starting Server");
            ServerSocket server = new ServerSocket(4952);    
            System.out.println("Creating Socket");
            Socket s = server.accept();
            System.out.println("Accepting Input");
            DataInputStream in = new DataInputStream(s.getInputStream());
            String message = in.readUTF();
            System.out.println(message);
        } catch (IOException e) {

        }
    }
}