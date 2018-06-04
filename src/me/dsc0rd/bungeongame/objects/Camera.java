package me.dsc0rd.bungeongame.objects;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.Vector3;
import org.newdawn.slick.Input;

public class Camera {


    public Player target;

    public float viewportX = Main.width, viewportY = Main.height, offsetX = 0, offsetY = 0, maxOffsetX = 320, maxOffsetY = 180;
    Vector3 mousePosition = new Vector3();

    public Camera(Player target) {
        this.target = target;
    }

    public void update(float dt, Input i) {
        float newOffsetX = (float) (viewportX / 2 - target.position.getX());
        float newOffsetY = (float) (viewportY / 2 - target.position.getY());

//        if (Math.abs(newOffsetX) > maxOffsetX) {
//            newOffsetX = maxOffsetX * (newOffsetX / (Math.abs(newOffsetX)));
//        }
//        if (Math.abs(newOffsetY) > maxOffsetY) {
//            newOffsetY = maxOffsetY * (newOffsetY / (Math.abs(newOffsetY)));
//        }

        offsetX = newOffsetX;
        offsetY = newOffsetY;

        mousePosition.set(i.getMouseX(), i.getMouseY(), 0);
    }
}
