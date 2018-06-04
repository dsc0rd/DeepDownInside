package me.dsc0rd.bungeongame.objects;

import me.dsc0rd.bungeongame.logic.Vector3;

public interface MovableObject {


    Vector3 getPosition();

    Vector3 getAcceleration();

    Vector3 getVelocity();

    boolean isColliding(MovableObject o);

    double getRadius();
}
