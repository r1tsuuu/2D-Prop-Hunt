package engine.physics;
public interface PhysicsObject {
    public void physicsProcess(float delta);
    public void checkCollision(PhysicsObject other);
}
