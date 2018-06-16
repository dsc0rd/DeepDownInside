package me.dsc0rd.bungeongame.objects.enviroment;

import org.newdawn.slick.Graphics;

public class Level {

    public int width, height;
    Room[][] rooms;

    public Level(int width, int height, Room[][] rooms) {
        this.rooms = rooms;
        this.width = width;
        this.height = height;
    }

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        rooms = new Room[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rooms[x][y] = new Room(3, 9);
            }
        }
        generateDoors();
    }

    public void generateDoors() {
        for (Room[] r : rooms) {
            for (Room rr : r) {
                rr.tiles[(rr.width / 2)][0] = new DoorTile(40, 40);
                rr.tiles[(rr.width / 2)][rr.height - 1] = new DoorTile(40, 40);
                rr.tiles[0][(rr.height / 2)] = new DoorTile(40, 40);
                rr.tiles[rr.width - 1][(rr.height / 2)] = new DoorTile(40, 40);
            }
        }
    }


    public void update(float dt) {

    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.pushTransform();
                g.translate(x * (rooms[x][y].width * 40), y * (rooms[x][y].height * 40));
                rooms[x][y].render(g);
                g.popTransform();
            }
        }
    }
}
