package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.Vector3;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile implements Tile {

    private Vector3 dimensions;
    private boolean isPathable;

    public FloorTile(int width, int height) {
        this.dimensions = new Vector3(width, height, 0);
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
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void update(float dt) {

    }
}
