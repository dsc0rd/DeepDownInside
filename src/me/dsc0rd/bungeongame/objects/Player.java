package me.dsc0rd.bungeongame.objects;

import me.dsc0rd.bungeongame.Main;
import me.dsc0rd.bungeongame.logic.MathUtils;
import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.Items.weapons.Weapon;
import me.dsc0rd.bungeongame.objects.Items.weapons.WeaponTypeEnum;
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
    private double angle = 0;
    private boolean isShooting = false;

    public Player(double x, double y, double z, double movementSpeed) {
        super(9, x, y, z, 20);
        this.components = new Hashtable<>();
        this.movementSpeed = movementSpeed;
        this.init();
    }

    public double getAngle() {
        return angle;
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
        if (Main.debug)
            this.equippedWeapons.add(Weaponry.ultraSlowBulletWeaponTESTINGONLY);
        this.equippedWeapons.add(Weaponry.pistol);
        this.equippedWeapons.add(Weaponry.shotgun);
        this.equippedWeapons.add(Weaponry.sawedOffShotgun);
        this.equippedWeapons.add(Weaponry.autoRifle);
        this.equippedWeapons.add(Weaponry.minigun);
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
        if (this.isShooting && getCurrentWeapon().getShotTimer() <= 0)
            shoot();
        super.update(dt);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.pink);
        graphics.drawOval((float) (this.position.getX() - (this.getRadius() / 2)), (float) (this.position.getY() - (this.getRadius() / 2)), (float) this.getRadius(), (float) this.getRadius());
        graphics.drawLine((float) this.position.getX(), (float) this.position.getY(), (float) (this.position.getX() + (this.getCurrentWeapon().getLength() * 4 * Math.cos(this.angle))), (float) (this.position.getY() + (this.getCurrentWeapon().getLength() * 4 * Math.sin(this.angle))));

        if (this.getCurrentWeapon().isReloading()) {
            graphics.setColor(Color.green);
            graphics.fillRect((float) (this.position.getX() - 12), (float) (this.position.getY() - (this.getRadius() / 2) - 12), (float) (24 * (this.getCurrentWeapon().getReloadTimer() / this.getCurrentWeapon().getReloadTime())), 6);
            graphics.setColor(Color.white);
            graphics.drawRect((float) (this.position.getX() - 12), (float) (this.position.getY() - (this.getRadius() / 2) - 12), 24, 6);
        }

    }

    public void acceptInput(int key, char c, int type) {
        switch (type) {
            case 0:
                switch (key) {
                    case Input.MOUSE_LEFT_BUTTON:
                        if (getCurrentWeapon().getWeaponType().equals(WeaponTypeEnum.Auto)) {
                            this.isShooting = true;
                        } else {
                            try {
                                shoot();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
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
                    case Input.MOUSE_LEFT_BUTTON:
                        this.isShooting = false;
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
        this.angle = MathUtils.getAngleOfLineBetweenTwoPoints(Main.width / 2, Main.height / 2, x, y);
    }

//    public void acceptMouseInput(int button, double x, double y, int ) throws CloneNotSupportedException {
////        switch (button) {
////            case 0://LEFT CLICK
////                shoot();
////                break;
////            case 1:
////                break;
////        }
//    }

    private void shoot() throws CloneNotSupportedException {
        getCurrentWeapon().shoot(this, angle);
        if (getCurrentWeapon().getWeaponType().equals(WeaponTypeEnum.Auto)) {
            isShooting = true;
        }
    }

    public Weapon getCurrentWeapon() {
        return equippedWeapons.get(equippedWeaponIndex);
    }

    public void nextWeapon() {
        equippedWeaponIndex = equippedWeaponIndex + 1 >= equippedWeapons.size() ? 0 : equippedWeaponIndex + 1;
        this.isShooting = false;
    }

}
