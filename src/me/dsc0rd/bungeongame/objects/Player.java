package me.dsc0rd.bungeongame.objects;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.MathUtils;
import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.Items.weapons.BasicWeapon;
import me.dsc0rd.bungeongame.objects.Items.weapons.Weapon;
import me.dsc0rd.bungeongame.objects.Items.weapons.Weaponry;
import me.dsc0rd.bungeongame.states.GameplayState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.Hashtable;

public class Player extends Unit {
    private boolean wasInitialized = false;
    private Hashtable<Vector3, Boolean> components;
    private Vector3
            stopComponent = new Vector3(),
            upComponent = new Vector3(0, -1, 0),
            downComponent = new Vector3(0, 1, 0),
            leftComponent = new Vector3(-1, 0, 0),
            rightComponent = new Vector3(1, 0, 0);

    private ArrayList<Weapon> equippedWeapons = new ArrayList<>();

    private int equippedWeaponIndex = 0;

    public double getAngle() {
        return angle;
    }

    private double angle = 0;

    public Player(double x, double y, double z, double movementSpeed) {
        super(6, x, y, z, 20);
        this.components = new Hashtable<>();
        this.movementSpeed = movementSpeed;
        this.init();
    }

    public void init() {
        for (Vector3 v : this.components.keySet()) {
            v.scale(this.movementSpeed).max(this.movementSpeed);
        }
        if (Main.debug) {
            System.out.println(this.upComponent.toString());
            System.out.println(this.downComponent.toString());
            System.out.println(this.leftComponent.toString());
            System.out.println(this.rightComponent.toString());
        }
        if (this.wasInitialized) {
            return;
        }
        GameplayState.units.add(this);
        this.equippedWeapons.add(Weaponry.pistol);
        this.components.put(this.upComponent, false);
        this.components.put(this.downComponent, false);
        this.components.put(this.leftComponent, false);
        this.components.put(this.rightComponent, false);

        //prevent reinitialization
        this.wasInitialized = true;
    }

    @Override
    public void update(float dt) throws CloneNotSupportedException {
        this.getCurrentWeapon().update(dt);
        updateFaceAngle(GameplayState.gc.getInput().getMouseX(), GameplayState.gc.getInput().getMouseY());
        for (Vector3 vector : this.components.keySet()) {
            if (this.components.get(vector)) {
                this.acceleration.add(vector);
            }
            if (!this.components.get(this.upComponent)) {
                if (this.velocity.getY() < 0) {
                    this.velocity.setY(0);
                }
            }
            if (!this.components.get(this.downComponent)) {
                if (this.velocity.getY() > 0) {
                    this.velocity.setY(0);
                }
            }
            if (!this.components.get(this.leftComponent)) {
                if (this.velocity.getX() < 0) {
                    this.velocity.setX(0);
                }
            }
            if (!this.components.get(this.rightComponent)) {
                if (this.velocity.getX() > 0) {
                    this.velocity.setX(0);
                }
            }

        }
        super.update(dt);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawOval((float) (this.position.getX() - (this.radius / 2)), (float) (this.position.getY() - (this.radius / 2)), (float) this.radius, (float) this.radius);
        graphics.setColor(Color.red);
        graphics.drawLine((float) this.position.getX(), (float) this.position.getY(), (float) (this.position.getX() + (this.getCurrentWeapon().getLength() * 4 * Math.cos(this.angle))), (float) (this.position.getY() + (this.getCurrentWeapon().getLength() * 4 * Math.sin(this.angle))));

    }

    public void acceptInput(int key, char c, int type) {
        switch (type) {
            case 0:
                switch (key) {
                    case Input.KEY_W:
                        this.components.replace(this.upComponent, true);
                        this.components.replace(this.downComponent, false);
                        if (Main.debug)
                            System.out.println("upComponent=" + this.components.get(this.upComponent));
                        break;
                    case Input.KEY_S:
                        this.components.replace(this.upComponent, false);
                        this.components.replace(this.downComponent, true);
                        if (Main.debug)
                            System.out.println("downComponent=" + this.components.get(this.downComponent));
                        break;
                    case Input.KEY_A:
                        this.components.replace(this.leftComponent, true);
                        this.components.replace(this.rightComponent, false);
                        if (Main.debug)
                            System.out.println("leftComponent=" + this.components.get(this.leftComponent));
                        break;
                    case Input.KEY_D:
                        this.components.replace(this.leftComponent, false);
                        this.components.replace(this.rightComponent, true);
                        if (Main.debug)
                            System.out.println("rightComponent=" + this.components.get(this.rightComponent));
                        break;
                    case Input.KEY_R:
                        getCurrentWeapon().reload();
                        break;
                    case Input.KEY_LSHIFT:
                        this.nextWeapon();
                        break;
                }
                break;
            case 1:
                switch (key) {
                    case Input.KEY_W:
                        this.components.replace(this.upComponent, false);
                        if (Main.debug)
                            System.out.println("upComponent=" + this.components.get(upComponent));
                        break;
                    case Input.KEY_S:
                        this.components.replace(this.downComponent, false);
                        if (Main.debug)
                            System.out.println("downComponent=" + this.components.get(this.downComponent));
                        break;
                    case Input.KEY_A:
                        this.components.replace(this.leftComponent, false);
                        if (Main.debug)
                            System.out.println("leftComponent=" + this.components.get(this.leftComponent));
                        break;
                    case Input.KEY_D:
                        this.components.replace(this.rightComponent, false);
                        if (Main.debug)
                            System.out.println("rightComponent=" + this.components.get(this.rightComponent));
                        break;
                    case Input.KEY_ADD:
                        if (Main.debug)
                            this.init();
                        break;
                }
                break;
            default:
                System.out.println("ERROR: SHOULD NOT HAPPEN EVER");
                System.out.println("BUT IT HAPPENED, LUL");
                break;
        }
    }


    public void updateFaceAngle(double x, double y) {
//        this.angle = MathUtils.getAngleOfLineBetweenTwoPoints(this.position.getX(), this.position.getY(), x, y);
        this.angle = MathUtils.getAngleOfLineBetweenTwoPoints(Main.width / 2, Main.height / 2, x, y);
    }

    public void acceptMouseInput(int button, double x, double y, int clickCount) throws CloneNotSupportedException {
        switch (button) {
            case 0://LEFT CLICK
                shoot();
                break;
            case 1:
                break;
        }
    }

    private void shoot() throws CloneNotSupportedException {
        getCurrentWeapon().shoot(this, angle);
    }

    public Weapon getCurrentWeapon() {
        return equippedWeapons.get(equippedWeaponIndex);
    }

    public void nextWeapon() {
        equippedWeaponIndex = equippedWeaponIndex + 1 >= equippedWeapons.size() ? 0 : equippedWeaponIndex + 1;
    }

}
