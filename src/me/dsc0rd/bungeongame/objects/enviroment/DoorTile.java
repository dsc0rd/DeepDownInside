package me.dsc0rd.bungeongame.objects.enviroment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DoorTile extends FloorTile {

    private DirectionEnum doorDirection;

    public DoorTile(int width, int height, int x, int y) {
        super(width, height, x, y);
        this.setPathable(true);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.decode("#680000"));
        g.fillRect((float) this.getPosition().getX()-(getWidth()/2), (float) this.getPosition().getY()-(getHeight()/2), getWidth(), getHeight());
    }

    public DirectionEnum getDoorDirection() {
        return doorDirection;
    }

    public void setDoorDirection(DirectionEnum doorDirection) {
        this.doorDirection = doorDirection;
    }

}
