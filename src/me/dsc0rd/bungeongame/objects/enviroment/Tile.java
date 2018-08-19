package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.MovableObject;
import org.newdawn.slick.Graphics;

public interface Tile {

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    Vector3 getDimensions();

    void setDimensions(Vector3 v);

    Vector3 getPosition();

    void setPosition(Vector3 v);

    boolean getPathable();

    void setPathable(boolean isPathable);

    void render(Graphics g);

    void update(float dt);

    boolean isColliding(MovableObject object);

    boolean isColliding(MovableObject object, Vector3 tile);
}
