/**
	The NetworkInObject is one of the base types of the Game.
    It is implemented by objects who wants to receive messages
    through the network.

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

public interface NetworkInObject {
    /**
     * Notifier for when there is a new message from the server.
     * 
     * @param input message from the server.
     */
    public void receive(String input);
}
