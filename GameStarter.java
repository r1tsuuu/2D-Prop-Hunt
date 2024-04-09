/*
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
*/

import engine.GameFrame;

public class GameStarter {
    public static void main(String[] args) {
        /*
         * try {
         * Socket s = new Socket("localhost", 4952);
         * DataOutputStream out = new DataOutputStream(s.getOutputStream());
         * out.writeUTF("This is the Message");
         * out.flush();
         * } catch (IOException e) {
         * 
         * }
         */
        new GameFrame("Prop Hunt");
    }
}
