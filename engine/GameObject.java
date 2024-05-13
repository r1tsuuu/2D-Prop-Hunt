/**
 * Base class for (almost) all objects in the game.
 * Has name for debugging and positional data.
 */
package engine;

import math.Vector2;

public class GameObject {

    private String name;
    private Vector2 position;

    /**
     * Instantiates a new GameObject with no name and default position.
     */
    public GameObject() {
        this("", Vector2.ZERO);
    }

    /**
     * Instantiates a new GameObject with name and default position.
     * 
     * @param name name
     */
    public GameObject(String name) {
        this(name, Vector2.ZERO);
    }

    /**
     * Instantiates a new GameObject with given position and no name.
     * 
     * @param position position
     */
    public GameObject(Vector2 position) {
        this("", position);
    }

    /**
     * Instantiates a new GameObject with given name and position.
     * 
     * @param name     name
     * @param position position
     */
    public GameObject(String name, Vector2 position) {
        this.name = name;
        this.position = position;
    }

    /**
     * Returns the name
     * 
     * @return name;
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position
     * 
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Sets the position to the new position
     * 
     * @param position new position
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
