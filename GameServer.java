
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class GameServer {
    private static ServerSocket server;
    private static DataOutputStream dataOut1; //hIDER
    private static DataOutputStream dataOut2; //SEEKER
    private static DataInputStream in1;
    private static DataInputStream in2;
    private static Socket s1;
    private static Socket s2;
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Server starting");
        try {
            waitConnection();
            preGame();
            String message = "";
            while (!message.equals("stop")) {
                dataOut2.writeUTF(in1.readUTF());
                dataOut1.writeUTF(in2.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    private static void waitConnection() {
        try {
            server = new ServerSocket(4952);    
            System.out.println("Server waiting");
            s1 = server.accept();
            System.out.println("player 1 connected");
            s2 = server.accept();
            System.out.println("player 2 connected");

            dataOut1 = new DataOutputStream(s1.getOutputStream());
            dataOut2 = new DataOutputStream(s2.getOutputStream());
            in1 = new DataInputStream(s1.getInputStream());
            in2 = new DataInputStream(s2.getInputStream());
            System.out.println("Players complete");
            dataOut1.writeUTF("party_complete");
            dataOut2.writeUTF("party_complete");
            dataOut1.flush();
            dataOut2.flush();
            
        } catch (IOException e) {
            System.out.println("wait ereror");
        }
    }

    // set the hiders and the seekers
    private static void preGame() {
        try {
            dataOut1.writeUTF("hider");
            dataOut2.writeUTF("wait");
            dataOut1.flush();
            dataOut2.flush();
            Thread.sleep(10000);
            dataOut2.writeUTF("seeker");
        } catch (IOException e) {
            System.out.println("preGame server error");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}