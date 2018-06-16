package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.Vector3;
import org.newdawn.slick.Graphics;

public interface Tile {

    int getWidth();

    int getHeight();

    Vector3 getDimensions();

    boolean getPathable();

    void setDimensions(Vector3 v);

    void setWidth(int width);

    void setHeight(int height);

    void setPathable(boolean isPathable);

    void render(Graphics g);

    void update(float dt);
}
