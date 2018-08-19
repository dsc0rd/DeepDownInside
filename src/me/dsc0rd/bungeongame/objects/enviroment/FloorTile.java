package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.MovableObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile implements Tile {

    private Vector3 dimensions, position;
    private boolean isPathable;

    public FloorTile(int width, int height, int x, int y) {
        this.dimensions = new Vector3(width, height, 0);
        this.position = new Vector3(x, y, 0);
        this.isPathable = true;
    }

    @Override
    public int getWidth() {
        return (int) this.dimensions.getX();
    }

    @Override
    public void setWidth(int width) {
        this.dimensions.setX(width);
    }

    @Override
    public int getHeight() {
        return (int) this.dimensions.getY();
    }

    @Override
    public void setHeight(int height) {
        this.dimensions.setY(height);
    }

    @Override
    public Vector3 getDimensions() {
        return this.dimensions;
    }

    @Override
    public void setDimensions(Vector3 v) {
        this.dimensions = v;
    }

    @Override
    public Vector3 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector3 v) {
        this.position = v;
    }

    @Override
    public boolean getPathable() {
        return this.isPathable;
    }

    @Override
    public void setPathable(boolean isPathable) {
        this.isPathable = isPathable;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect((float) this.getPosition().getX()-(getWidth()/2), (float) this.getPosition().getY()-(getHeight()/2), getWidth(), getHeight());
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean isColliding(MovableObject object) {
        return false;
    }

    @Override
    public boolean isColliding(MovableObject object, Vector3 tile) {
        return false;
    }
}
