package me.dsc0rd.bungeongame.objects.enviroment;

import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.Items.weapons.ProjectileBasedWeapon;
import me.dsc0rd.bungeongame.objects.MovableObject;
import me.dsc0rd.bungeongame.objects.Unit;
import me.dsc0rd.bungeongame.states.GameplayState;
import org.newdawn.slick.Graphics;

public class Room {

    public Tile[][] tiles;
    public Vector3 position;
    public static int tileDim = 120;

    public int width, height;
    public float pixelWidth, pixelHeight;
    public float offsetX;
    public float offsetY;

    public Room(int width, int height, int x, int y, float offsetX, float offsetY) {
        tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
        this.position = new Vector3(x, y, 0);
        this.pixelWidth = this.width * tileDim;
        this.pixelHeight = this.height * tileDim;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        generateRoom();
    }

    public void generateRoom() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = x == 0 || y == 0 || x == width - 1 || y == height - 1
                        ? new WallTile(tileDim, tileDim, (int) (offsetX + (x * tileDim)), (int) (offsetY + (y * tileDim)))
                        : new FloorTile(tileDim, tileDim, (int) (offsetX + (x * tileDim)), (int) (offsetY + (y * tileDim)));
            }
        }
        tiles[(width / 2)][0] = new DoorTile(tileDim, tileDim, (int) (offsetX + ((width / 2) * tileDim)), (int) offsetY);
        tiles[(width / 2)][height - 1] = new DoorTile(tileDim, tileDim, (int) (offsetX + ((width / 2) * tileDim)), (int) (offsetY + ((height - 1) * tileDim)));
        tiles[0][(height / 2)] = new DoorTile(tileDim, tileDim, (int) offsetX, (int) (offsetY + ((height / 2) * tileDim)));
        tiles[width - 1][(height / 2)] = new DoorTile(tileDim, tileDim, (int) (offsetX + ((width - 1) * tileDim)), (int) (offsetY + ((height / 2) * tileDim)));
    }

    public void update(float dt) {
        for (Tile[] t : tiles)
            for (Tile tt : t) {
                if (!tt.getPathable()) {
                    for (Unit u : GameplayState.units) {
                        if (isColliding(u, tt)) {
                            u.getVelocity().scale(0, 0, 1);
                        }
                    }
                    for (ProjectileBasedWeapon.Projectile p : GameplayState.bullets) {
                        if (isColliding(p, tt)) {
                            p.getVelocity().scale(0, 0, 0);
                        }
                    }
                }
            }
    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y].render(g);
            }
        }
    }


    boolean isColliding(MovableObject mo, Tile t) {
        double distX, distY;
        distX = Math.abs(mo.getPosition().getX() - t.getPosition().getX());
        distY = Math.abs(mo.getPosition().getY() - t.getPosition().getY());
        if (((distX > tileDim / 2 + (mo.getRadius() / 2)) || (distY > tileDim / 2 + (mo.getRadius() / 2)))) {
            return false;
        }

        if (distX <= tileDim / 2 || distY <= tileDim / 2) {
            return true;
        }

        double cornerDist_sq = Math.pow(distX - (tileDim / 2), 2) + Math.pow(distY - (tileDim / 2), 2);
        return (cornerDist_sq <= Math.pow(mo.getRadius(), 2));
    }


}