package me.dsc0rd.bungeongame.states;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.EventEnum;
import me.dsc0rd.bungeongame.objects.Camera;
import me.dsc0rd.bungeongame.objects.Items.weapons.BasicWeapon;
import me.dsc0rd.bungeongame.objects.Player;
import me.dsc0rd.bungeongame.objects.Unit;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.concurrent.CopyOnWriteArrayList;

public class GameplayState extends BasicGameState {
    public static CopyOnWriteArrayList<BasicWeapon.Bullet> bullets = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Unit> units = new CopyOnWriteArrayList<>();
    public static GameContainer gc;
    boolean paused = false;
    String dt = "";
    private Player player = new Player(500, 400, 0.1, 1.6);
    private Unit dummy = new Unit(80, 200, 400, 0.1, 20);
    private Camera camera = new Camera(player);
    private int ID;


    public GameplayState(int ID) {
        this.ID = ID;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        gc = container;
        container.getGraphics().setAntiAlias(false);
        player.init();
//        player.updateFaceAngle(container.getInput().getMouseX(), container.getInput().getMouseY());
        bullets.clear();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.clear();
        g.pushTransform();
        g.translate(camera.offsetX, camera.offsetY);
        for (Unit u : units) {

            u.render(g);
        }
        for (BasicWeapon.Bullet b : bullets) {
            b.render(g);
        }
        g.popTransform();

        renderInterface(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        camera.update((float) delta / 1000, container.getInput());
        dt = String.valueOf((float) delta / 1000);
        if (paused)
            return;
        try {
            for (Unit u : units) {
                u.update((float) delta / 1000);
            }
            for (BasicWeapon.Bullet b : bullets) {
                b.update((float) delta / 1000);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        checkCollisions();

    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ESCAPE:
                paused = !paused;
                break;
        }
        player.acceptInput(key, c, 0);

    }

    @Override
    public void keyReleased(int key, char c) {
        player.acceptInput(key, c, 1);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        try {
            player.acceptMouseInput(button, x, y, clickCount);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        player.updateFaceAngle(newx, newy);
    }

    public void checkCollisions() {
        for (BasicWeapon.Bullet b : bullets) {
            for (Unit u : units) {
                if (b.owner(u)) {
                    continue;
                }
                if (b.isColliding(u)) {
                    u.damage(b.getDamage(), b);
                    b.triggerEvent(EventEnum.onDeath);
                }
            }
        }
    }


    public void checkCollision(BasicWeapon.Bullet b) {
    }

    public void checkCollision(Unit u) {

    }

    public void renderInterface(Graphics g) {
        if (Main.debug) {
            g.setColor(Color.white);
            g.drawString(dt, 600, 20);
            g.drawString("" + player.getAngle(), 800, 20);
            g.drawString("" + player.getPosition().getX(), 800, 40);
            g.drawString("" + player.getPosition().getY(), 800, 60);
            g.drawString("" + player.getVelocity().getX(), 800, 80);
            g.drawString("" + player.getVelocity().getY(), 800, 100);
        }
        g.setColor(Color.cyan);
        g.drawString(player.getCurrentWeapon().getName(), 1800, 700);
        g.drawString(player.getCurrentWeapon().getClipAmmo() + "/" + player.getCurrentWeapon().getClipMaxAmmo(), 1800, 720);
        g.drawString(player.getCurrentWeapon().getAmmo() + "/" + player.getCurrentWeapon().getMaxAmmo(), 1800, 740);
        g.drawString(player.getCurrentWeapon().getReloadTimer() + "/" + player.getCurrentWeapon().getReloadTime(), 1800, 760);
    }
}
