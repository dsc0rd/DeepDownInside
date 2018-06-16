package me.dsc0rd.bungeongame.objects.enviroment;

import org.newdawn.slick.Graphics;

public class Room {

    public Tile[][] tiles;

    public int width, height;

    public Room(int width, int height) {
        tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        generateRoom();
    }

    public void generateRoom() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = x == 0 || y == 0 || x == width - 1 || y == height - 1 ? new WallTile(40, 40) : new FloorTile(40, 40);
            }
        }
    }

    public void update(float dt) {

    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.pushTransform();
                g.translate(x * 40, y * 40);
                tiles[x][y].render(g);
                g.popTransform();
            }
        }
    }

}