
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
    private static final float gameDuration = 120;
    private static final float gracePeriod = 10;
    private static int timeLeft;
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Server starting");
        waitConnection();
        boolean playing = true;
        while (playing){
            System.out.println("starting the damn game");
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

    private static void playGame() {
        try {
            preGame();
            String message = "";
            long max = System.currentTimeMillis() + (long)(gameDuration * 1000);
            timeLeft = (int)gameDuration;
            while (System.currentTimeMillis() < max) {
                message = in1.readUTF();
                if (message.equals("winSeeker"))
                {
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
            
        } catch (IOException e) {
            System.out.println("Server Error");
        }
    }

    // set the hiders and the seekers
    private static void preGame() {
        try {
            dataOut1.writeUTF("hider");
            //dataOut2.writeUTF("wait");
            dataOut1.flush();
            dataOut2.flush();
            long max = System.currentTimeMillis() + (long)(gracePeriod * 1000);
            timeLeft = (int) gracePeriod;
            System.out.println("STARTING GRACE PERIOD");
            while (System.currentTimeMillis() < max) {
                // in1.readUTF();
                System.out.println(timeLeft);
                if (timeLeft - 1 > (max - System.currentTimeMillis()) / 1000f) {
                    timeLeft--;
                    System.out.println(timeLeft);
                    dataOut1.writeUTF("t " + timeLeft);
                }
            }
            System.out.println("ENDING GRACE PERIOD");
            dataOut2.writeUTF("seeker");
        } catch (IOException e) {
            System.out.println("preGame server error");
        }
    }

    private static void seekerWin() {
        System.out.println("seeker win");
        try {
            dataOut1.writeUTF("defeat");
            dataOut2.writeUTF("victory");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //endGame();
    }

    private static void hiderWin() {
        System.out.println("hider win");
        try {
            dataOut1.writeUTF("victory");
            dataOut2.writeUTF("defeat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // endGame();
    }

    private static void endGame() {
        try {
            dataOut1.writeUTF("STOP");
            dataOut2.writeUTF("STOP");
            dataOut1.flush();
            dataOut2.flush();
            System.out.println("Sent stop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean replay() {
        try {
            String replay1 = in1.readUTF();
            String replay2 = in2.readUTF();
            System.out.println(replay1);
            System.out.println(replay2);

            var ans1 = replay1.split(" ");
            var ans2 = replay2.split(" ");
            
            System.out.println(replay1);
            System.out.println(replay2);

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
        System.out.println("should be in here");
        return false;
    }
}