package me.dsc0rd.bungeongame.objects;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.states.GameplayState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Unit implements MovableObject {

    public Vector3 position;
    Vector3 acceleration;
    Vector3 velocity;
    int health;
    double speedMultiplier = 1;

    double movementSpeed = 18;

    double radius = 20;


    public Unit(int health, double x, double y, double z, double radius) {
        this.health = health;
        this.position = new Vector3(x, y, z);
        this.acceleration = new Vector3();
        this.velocity = new Vector3();
        this.radius = radius;
        GameplayState.units.add(this);
        if (Main.debug) {
            System.out.println("Added " + this.getClass() + " to unit list");
        }
    }


    public void update(float dt) throws CloneNotSupportedException {

        this.velocity.add(this.acceleration.scale(speedMultiplier, speedMultiplier, 1).scale(dt, dt, 1));
        this.position.add(this.velocity.max(movementSpeed));
//        this.velocity.scale(0.99);

        if (this.health <= 0) {
            this.destroy();
        }

    }


    public void destroy() {
        GameplayState.units.remove(this);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.drawRoundRect((float) this.position.getX(), (float) this.position.getY(), 20, 20, 4);
    }

    @Override
    public Vector3 getPosition() {
        return this.position;
    }

    @Override
    public Vector3 getAcceleration() {
        return this.acceleration;
    }

    @Override
    public Vector3 getVelocity() {
        return this.velocity;
    }

    @Override
    public boolean isColliding(MovableObject o) {
        return o.getPosition().inRadius(this.position, this.radius, o.getRadius());
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    public void damage(double value, MovableObject source) {
        this.health -= value;
        System.out.println(this.toString() + " was damaged by " + source);

    }
//
//    public void verticalHalt() {
//        this.velocity.set(this.velocity.getX(), 0, this.velocity.getZ());
//    }
//
//    public void horizontalHalt() {
//        this.velocity.set(0, this.velocity.getY(), this.velocity.getZ());
//    }
}
