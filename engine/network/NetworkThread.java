package engine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkThread extends Thread {
    
    Socket socket;
    DataInputStream dataIn;
    DataOutputStream dataOut;


    public NetworkThread(String address, int port) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    public void run() {
        while (socket != null) {

        }
    }

    public void send(Object data) {
    }

    public void receive() {

    }
}
