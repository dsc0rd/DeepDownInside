package me.dsc0rd.bungeongame.states;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.EventEnum;
import me.dsc0rd.bungeongame.media.ResourceManager;
import me.dsc0rd.bungeongame.objects.Camera;
import me.dsc0rd.bungeongame.objects.Items.weapons.ProjectileBasedWeapon;
import me.dsc0rd.bungeongame.objects.Player;
import me.dsc0rd.bungeongame.objects.Unit;
import me.dsc0rd.bungeongame.objects.enviroment.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameplayState extends BasicGameState {
    public static CopyOnWriteArrayList<ProjectileBasedWeapon.Projectile> bullets = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Unit> units = new CopyOnWriteArrayList<>();
    public static GameContainer gc;
    boolean paused = false;
    String dt = "";
    private Level level;
    private Player player = new Player(500, 400, 0.1, 2);
    private Camera camera = new Camera(player);
    private int ID;
    private Unit enemy;

    public GameplayState(int ID) {
        this.ID = ID;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        try {
            Main.resourceManager = new ResourceManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gc = container;
        container.getGraphics().setAntiAlias(false);
        player.init();
        bullets.clear();
        enemy = new Unit(2000, 480, 480, 1, 16);

        level = new Level(12, 14);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.clear();
        g.pushTransform();
        g.translate(camera.offsetX, camera.offsetY);
        level.render(g);
        for (Unit u : units) {
            u.render(g);
        }
        for (ProjectileBasedWeapon.Projectile b : bullets) {
            b.render(g);
        }
        g.popTransform();
        renderInterface(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        checkCollisions();
        level.update((float) delta / 1000);
        camera.update((float) delta / 1000, container.getInput());
        dt = String.valueOf((float) delta / 1000);
        if (paused)
            return;
        try {
            for (Unit u : units) {
                u.update((float) delta / 1000);
            }
            for (ProjectileBasedWeapon.Projectile b : bullets) {
                b.update((float) delta / 1000);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_ESCAPE:
                paused = !paused;
                break;
            case Input.KEY_F1:
                Main.debug = !Main.debug;
                break;
            case Input.KEY_P:
                player.damage(1, player);
                break;
            case Input.KEY_O:
                player.damage(-1, player);
        }
        player.acceptInput(key, c, 0);
    }

    @Override
    public void keyReleased(int key, char c) {
        player.acceptInput(key, c, 1);
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if (!paused)
            player.acceptInput(button, '.', 0);
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        if (!paused)
            player.acceptInput(button, '.', 1);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        player.updateFaceAngle(newx, newy);
    }

    public void checkCollisions() {
        for (Unit u : units) {
            for (ProjectileBasedWeapon.Projectile b : bullets) {
                if (b.owner(u)) {
                    break;
                }
                if (b.isColliding(u)) {
                    u.damage(b.getDamage(), b);
                    b.triggerEvent(EventEnum.onDeath);
                }
            }
        }
    }


    public void renderInterface(Graphics g) {
        if (Main.debug) {
            g.setColor(Color.white);
            g.drawString(dt, 600, 20);
            g.drawString("" + Math.abs(180 - Math.toDegrees(player.getAngle())), 800, 20);
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
        g.fillRect(40, 40, 180, 30);
        g.setColor(Color.white);
//        if (paused) {
//            g.fillRect(0,0,);
        if (player.getHealth() <= 2) {
            if (player.getHealth() <= 0)
                return;
            g.texture(new Rectangle(40, 40, 30, 30), player.getHealth() != 1 ? Main.resourceManager.halfHeartImage : Main.resourceManager.fullHeartImage, true);
        } else {
            if (player.getHealth() % 2 == 0) {
                for (int i = 0; i < player.getHealth() / 2; i++) {
                    g.texture(new Rectangle(40 + (40 * i), 40, 30, 30), Main.resourceManager.fullHeartImage, true);
                }
            } else {
                int j = 0;
                for (int i = 0; i < (player.getHealth() - 1) / 2; i++) {
                    j++;
                    g.setColor(Color.white);
                    g.texture(new Rectangle(40 + (40 * i), 40, 30, 30), Main.resourceManager.fullHeartImage, true);
                }
                g.texture(new Rectangle(40 + (40 * j + 1), 40, 30, 30), Main.resourceManager.halfHeartImage, true);
            }
        }


    }
}

