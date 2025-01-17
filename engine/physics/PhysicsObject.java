package engine.physics;
public interface PhysicsObject {
    public void physicsProcess(float delta);
    public void collided(PhysicsObject other, String direction, float offset);
}
