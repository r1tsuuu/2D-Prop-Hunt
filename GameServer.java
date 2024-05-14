/**
	The GameServer class is the server the player clients connect to
    when playing Prop Hunt. It provides basic networking functionality
    and waits sends and receives by turn.

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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static ServerSocket server;
    private static DataOutputStream dataOut1; // hIDER
    private static DataOutputStream dataOut2; // SEEKER
    private static DataInputStream in1;
    private static DataInputStream in2;
    private static Socket s1;
    private static Socket s2;
    private static final float gameDuration = 120;
    private static final float gracePeriod = 10;
    private static int timeLeft;

    /**
     * Main class starts the server and repeats the game until one player decides to
     * quit. After the game, switches the hider and the seeker.
     */
    public static void main(String[] args) {
        System.out.println("Server starting");
        waitConnection();
        boolean playing = true;
        while (playing) {
            playGame();
            playing = replay();
            if (playing) {
                var intemp = in1;
                in1 = in2;
                in2 = intemp;

                var outtemp = dataOut1;
                dataOut1 = dataOut2;
                dataOut2 = outtemp;
            } else {
                endGame();
            }
        }
    }

    /**
     * playGame starts a round of hide and seek.
     */
    private static void playGame() {
        try {
            preGame();
            String message = "";
            long max = System.currentTimeMillis() + (long) (gameDuration * 1000);
            timeLeft = (int) gameDuration;
            while (System.currentTimeMillis() < max) {
                message = in1.readUTF();
                if (message.equals("winSeeker")) {
                    seekerWin();
                    return;
                }
                if (timeLeft - 1 > (max - System.currentTimeMillis()) / 1000f) {
                    timeLeft--;
                    dataOut1.writeUTF("t " + (timeLeft));
                    dataOut2.writeUTF("t " + (timeLeft));
                }

                dataOut2.writeUTF(message);
                dataOut1.writeUTF(in2.readUTF());
            }
            hiderWin();

        } catch (IOException e) {
            System.out.println("Server Error");
            System.exit(0);
        }
    }

    /**
     * waitConnection waits for a clients to connect in port 4952
     */
    private static void waitConnection() {
        try {
            server = new ServerSocket(4952);
            System.out.println("Server waiting");
            s1 = server.accept();
            System.out.println("Player 1 connected");
            s2 = server.accept();
            System.out.println("Player 2 connected");

            dataOut1 = new DataOutputStream(s1.getOutputStream());
            dataOut2 = new DataOutputStream(s2.getOutputStream());
            in1 = new DataInputStream(s1.getInputStream());
            in2 = new DataInputStream(s2.getInputStream());
            System.out.println("Players complete");

        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    /**
     * Sets the hiders and the seekers.
     * Gives grace period for the chosen hider to hide.
     * After the grace period, the seeker is gievn the go signal to go.
     */
    private static void preGame() {
        try {
            dataOut1.writeUTF("hider");
            dataOut2.writeUTF("wait");
            dataOut1.flush();
            dataOut2.flush();
            long max = System.currentTimeMillis() + (long) (gracePeriod * 1000);
            timeLeft = (int) gracePeriod;
            while (System.currentTimeMillis() < max) {
                if (timeLeft - 1 > (max - System.currentTimeMillis()) / 1000f) {
                    timeLeft--;
                    dataOut1.writeUTF("t " + timeLeft);
                }
            }
            dataOut2.writeUTF("seeker");
        } catch (IOException e) {
            System.out.println("Grace Period Server Error");
        }
    }

    /**
     * Called by playGame when the seeker wins
     */
    private static void seekerWin() {
        try {
            dataOut1.writeUTF("defeat");
            dataOut2.writeUTF("victory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by playGame when the seeker wins
     */
    private static void hiderWin() {
        try {
            dataOut1.writeUTF("victory");
            dataOut2.writeUTF("defeat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by mainLoop when at least 1 player decides to stop playing
     */
    private static void endGame() {
        try {
            dataOut1.writeUTF("STOP");
            dataOut2.writeUTF("STOP");
            dataOut1.flush();
            dataOut2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * After the game, the server waits for the clients to send whether they
     * want a new round or not.
     * If the players want a new round. The roles are switched and the hider
     * becomes the next seeker and the seeker becomes the next hider.
     * @return play a new round.
     */
    private static boolean replay() {
        try {
            String replay1 = in1.readUTF();
            String replay2 = in2.readUTF();

            var ans1 = replay1.split(" ");
            var ans2 = replay2.split(" ");

            while (!ans1[0].equals("replay")) {
                dataOut1.writeUTF("");
                replay1 = in1.readUTF();
                ans1 = replay1.split(" ");
            }
            while (!ans2[0].equals("replay")) {
                dataOut1.writeUTF("");
                replay2 = in2.readUTF();
                ans2 = replay2.split(" ");
            }

            return ans1[1].equals("true") && ans2[1].equals("true");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}