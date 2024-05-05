package engine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkThread extends Thread {

    private static Socket socket;
    private static DataInputStream dataIn;
    private static DataOutputStream dataOut;
    private static ArrayList<NetworkOutObject> networkOutObjects;

    public NetworkThread(String address, int port) {
        networkOutObjects = new ArrayList<NetworkOutObject>();
        try {
            socket = new Socket(address, port);
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
            System.out.println("Server connected");
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    public static void add(NetworkOutObject object) {
        networkOutObjects.add(object);
    }

    public void run() {
        while (socket != null) {
            try {
                for (NetworkOutObject networkOutObject : networkOutObjects) {
                    networkOutObject.send(dataOut);
                    dataOut.flush();
                }
                Thread.sleep(1);

            } catch (IOException e) {
                System.out.println("Server Error");
            } catch (InterruptedException e) {
                System.out.println("Thread Error");
            }
        }
    }
}
