package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.MathUtils;
import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.MovableObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WallTile extends FloorTile {
    private Color tileColor = Color.decode("#1376B5");
    private boolean isSecretDoor, wasDamaged = false;
    private DirectionEnum doorDirection;

    public WallTile(int width, int height, int x, int y) {
        super(width, height, x, y);
        this.setPathable(false);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        if (this.wasDamaged) {
            this.setPathable(true);
            //TODO SWITCH SPRITE
            this.tileColor = Color.pink;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.tileColor);
        g.fillRect((float) this.getPosition().getX()-(getWidth()/2), (float) this.getPosition().getY()-(getHeight()/2), getWidth(), getHeight());

    }

    public DirectionEnum getDoorDirection() {
        return this.doorDirection;
    }

    public void setDoorDirection(DirectionEnum doorDirection) {
        this.doorDirection = doorDirection;
    }

    public boolean isSecretDoor() {
        return this.isSecretDoor;
    }

    public void setSecretDoor(boolean secretDoor) {
        this.isSecretDoor = secretDoor;
    }

    @Override
    public boolean isColliding(MovableObject object, Vector3 tile) {
        return true;
    }

    @Override
    public boolean isColliding(MovableObject object) {
        return MathUtils.doubleBetweenValues(object.getPosition().getX(), this.getPosition().getX(), this.getPosition().getX() + this.getDimensions().getX()) && MathUtils.doubleBetweenValues(object.getPosition().getY(), this.getPosition().getY(), this.getPosition().getY() + this.getDimensions().getY());
    }
}
