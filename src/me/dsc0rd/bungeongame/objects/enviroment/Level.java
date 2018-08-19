package me.dsc0rd.bungeongame.objects.enviroment;

import org.newdawn.slick.Graphics;

public class Level {

    private final int roomW = 25, roomH = 13;
    public int width, height;
    public Room[][] rooms;

    public Level(int width, int height, Room[][] rooms) {
        this.rooms = rooms;
        this.width = width;
        this.height = height;
    }

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        rooms = new Room[width][height];
        float offsetX = 0, offsetY = 0;
        for (int x = 0; x < width; x++) {
            offsetY = 0;
            for (int y = 0; y < height; y++) {
                rooms[x][y] = new Room(roomW, roomH, x, y, offsetX, offsetY);
                offsetY += roomH * 120;
            }
            offsetX += roomW * 120;

        }
    }

    public void generateDoors() {
        for (Room[] r : rooms) {
            for (Room rr : r) {
                rr.tiles[(rr.width / 2)][0] = new DoorTile(rr.tileDim, rr.tileDim, (rr.width / 2) * rr.tileDim, 0);
                rr.tiles[(rr.width / 2)][rr.height - 1] = new DoorTile(rr.tileDim, rr.tileDim, (rr.width / 2) * rr.tileDim, (rr.height - 1) * rr.tileDim);
                rr.tiles[0][(rr.height / 2)] = new DoorTile(rr.tileDim, rr.tileDim, 0, (rr.height / 2) * rr.tileDim);
                rr.tiles[rr.width - 1][(rr.height / 2)] = new DoorTile(rr.tileDim, rr.tileDim, (rr.width - 1) * rr.tileDim, (rr.height / 2) * rr.tileDim);
            }
        }
    }


    public void update(float dt) {
        for (Room[] r : rooms)
            for (Room rr : r)
                rr.update(dt);
    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rooms[x][y].render(g);
            }
        }
    }

}
