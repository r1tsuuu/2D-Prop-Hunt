/**
	The NetworkOutObject class is a base type of the game.
    It is implemented by objects who want to send data to 
    the server.

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

import java.io.DataOutputStream;

public interface NetworkOutObject {
    /**
     * Gives the object a reference to the DataOutputStream to the server
     * to use to their liking.
     * 
     * @param dataOut the DataOutputSTream to the server.
     */
    public void send(DataOutputStream dataOut);
}
