package engine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import engine.GameCanvas;

public class NetworkThread extends Thread {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private ArrayList<NetworkOutObject> networkOutObjects;
    private ArrayList<NetworkInObject> networkInObjects;
    private GameCanvas canvas;
    private int port;
    private String address;

    public NetworkThread(String address, int port, GameCanvas canvas) {
        this.canvas = canvas;
        networkOutObjects = new ArrayList<NetworkOutObject>();
        networkInObjects = new ArrayList<NetworkInObject>();
        this.address = address;
        this.port = port;
    }

    public void establishConnection(){
        try {
            socket = new Socket(address, port);
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
            System.out.println("Server connected");
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    public void add(NetworkOutObject object) {
        networkOutObjects.add(object);
    }

    public void add(NetworkInObject object) {
        networkInObjects.add(object);
    }

    public void run() {
        while (socket != null) {
            try {
                for (NetworkOutObject networkOutObject : networkOutObjects) {
                    networkOutObject.send(dataOut);
                    dataOut.flush();
                }
                var input = dataIn.readUTF();
                canvas.networkNotified(input);
                for (NetworkInObject networkInObject : networkInObjects) {
                    networkInObject.receive(input);
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
