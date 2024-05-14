/**
	The NetworkThread class is responsible for server to client communication.
    It notifies every NetworkInObject whenever a new message from the server is received
    while it sends a reference to the DataOutputStream from the socket to each NetworkOutObject
    to use to send data.

    @author Alinus Abuke (230073)	
    @author Neil Aldous Biason (230940)
    @version 13 May 2024

    We have not discussed the Java language code in our program 
    with anyone other than our instructor or the teaching assistants 
    assigned to this course.

    We have not used Java language code obtained from another student, 
    or any other unauthorized source, either modified or unmodified.

    If any Java language code or documentation used in our program 
    was obtained from another source, such as a textbook or website, 
    that has been clearly noted with a proper citation in the comments 
    of our program.
**/
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

    /**
     * Creates the network thread from the given address, port, and the canvas that
     * contains
     * the network objects
     * 
     * @param address address of the server
     * @param port    number
     * @param canvas  GameCanvas with the network objects
     */
    public NetworkThread(String address, int port, GameCanvas canvas) {
        this.canvas = canvas;
        this.address = address;
        this.port = port;
    }

    /**
     * Attempts to establish connection between the server and the client.
     */
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

    /**
     * Starts the NetworkThread, repeatedly sending and receiving messages from the
     * server continously until told by the server to STOP.
     */
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
                if (input.equals("STOP"))
                    break;
                canvas.networkNotified(input);
                for (NetworkInObject networkInObject : networkInObjects) {
                    networkInObject.receive(input);
                }

            } catch (IOException e) {
                System.out.println("IO Exception");
                break;
            }
        }
        System.exit(0);
    }

    /**
     * Sets the canva that contains the network objects to process.
     */
    public void setCanvas(GameCanvas canvas) {
        this.canvas = canvas;
    }
}
