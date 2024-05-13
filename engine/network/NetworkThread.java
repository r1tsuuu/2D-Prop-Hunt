package engine.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import engine.GameCanvas;

public class NetworkThread extends Thread {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;

    private GameCanvas canvas;
    private int port;
    private String address;

    public NetworkThread(String address, int port, GameCanvas canvas) {
        this.canvas = canvas;
        this.address = address;
        this.port = port;
    }

    public void establishConnection() {
        try {
            socket = new Socket(address, port);
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
            System.out.println("Server connected");
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    public void run() {
        while (socket != null) {
            var networkOutObjects = canvas.getNetworkOutObjects();
            var networkInObjects = canvas.getNetworkInObjects();
            try {
                for (int i = 0; i < networkOutObjects.size(); i++) {
                    var networkOutObject = networkOutObjects.get(i);
                    networkOutObject.send(dataOut);
                }
                dataOut.flush();
                var input = dataIn.readUTF();
                if (input.equals("STOP")) break;
                canvas.networkNotified(input);
                for (NetworkInObject networkInObject : networkInObjects) {
                    networkInObject.receive(input);
                }

            } catch (IOException e) {
                System.out.println(e);
                break;
            }
        }
        System.exit(0);
    }

    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }
}
