
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Player;

public class GameServer {
    public static ServerSocket server;
    public static DataOutputStream dataOut1;
    public static DataOutputStream dataOut2;
    private static Player player1;
    private static Player player2;
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Server starting");
        try {
            server = new ServerSocket(4952);    
            System.out.println("Server waiting");
            Socket s1 = server.accept();
            System.out.println("player 1 connected");
            Socket s2 = server.accept();
            System.out.println("player 2 connected");

            dataOut1 = new DataOutputStream(s1.getOutputStream());
            dataOut2 = new DataOutputStream(s2.getOutputStream());

            dataOut1.writeUTF("party_complete");
            dataOut2.writeUTF("party_complete");

            dataOut1.flush();
            dataOut2.flush();

            String message = "";
            while (!message.equals("stop")) {
                DataInputStream in1 = new DataInputStream(s1.getInputStream());
                DataInputStream in2 = new DataInputStream(s2.getInputStream());
                dataOut2.writeUTF(in1.readUTF());
                dataOut1.writeUTF(in2.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }
}