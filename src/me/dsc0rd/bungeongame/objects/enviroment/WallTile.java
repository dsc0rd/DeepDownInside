package me.dsc0rd.bungeongame.objects.enviroment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WallTile extends FloorTile {

    private boolean isSecretDoor;
    private DirectionEnum doorDirection;

    public WallTile(int width, int height) {
        super(width, height);
        this.setPathable(false);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public DirectionEnum getDoorDirection() {
        return doorDirection;
    }

    public void setDoorDirection(DirectionEnum doorDirection) {
        this.doorDirection = doorDirection;
    }

    public boolean isSecretDoor() {
        return isSecretDoor;
    }

    public void setSecretDoor(boolean secretDoor) {
        isSecretDoor = secretDoor;
    }
}
