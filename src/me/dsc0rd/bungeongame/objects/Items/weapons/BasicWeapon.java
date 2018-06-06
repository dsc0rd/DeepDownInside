package me.dsc0rd.bungeongame.objects.Items.weapons;

import me.dsc0rd.bungeongame.logic.EventEnum;
import me.dsc0rd.bungeongame.logic.MathUtils;
import me.dsc0rd.bungeongame.logic.Vector3;
import me.dsc0rd.bungeongame.objects.MovableObject;
import me.dsc0rd.bungeongame.objects.Unit;
import me.dsc0rd.bungeongame.states.GameplayState;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BasicWeapon implements Weapon {


    private WeaponTypeEnum weaponType;
    private double projectileAmount;
    private double length, damage, bulletSpeed, bulletLifetime, timeBetweenShots, reloadTime, clipAmmo, clipMaxAmmo, ammo, maxAmmo;
    private double spread;

    private Color bulletColor;

    private double shotTimer = 0, reloadTimer = 0;
    boolean reloading = false;


    public BasicWeapon(WeaponTypeEnum type, double weaponLength, double projectileAmount, double damage, double bulletSpeed, double bulletLifetime, double timeBetweenShots, double reloadTime, double clipMaxAmmo, double maxAmmo, double spread, Color bulletColor) {
        this.weaponType = type;
        this.length = weaponLength;
        this.projectileAmount = projectileAmount;
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.bulletLifetime = bulletLifetime;
        this.timeBetweenShots = timeBetweenShots;
        this.reloadTime = reloadTime;
        this.clipMaxAmmo = clipMaxAmmo;
        this.clipAmmo = this.clipMaxAmmo;
        this.maxAmmo = maxAmmo;
        this.ammo = this.maxAmmo;
        this.spread = spread;
        this.bulletColor = bulletColor;
    }

    @Override
    public WeaponTypeEnum getWeaponType() {
        return this.weaponType;
    }

    @Override
    public void shoot(Unit owner, double angle) throws CloneNotSupportedException {

        if (clipAmmo < 1) {
            reload();
            return;
        }
        if (shotTimer > 0 || reloading)
            return;
        for (int i = 0; i < projectileAmount; i++) {
            double spreadAngle = Math.PI - (Math.PI - Math.toRadians(MathUtils.randomDouble(-spread, spread)));
            new Bullet(owner, this.damage, bulletSpeed)
                    .translate(
                            (this.length + (owner.getRadius() / 2)) * Math.cos(angle + spreadAngle), (this.length + (owner.getRadius() / 2)) * Math.sin(angle + spreadAngle),
                            bulletLifetime / 10
                    )
                    .accelerate(bulletSpeed, (angle + spreadAngle))
                    .accelerate(0, 0, -0.001);
        }
        this.clipAmmo--;
        this.shotTimer = timeBetweenShots;
    }


    public void update(float dt) {
        if (this.shotTimer > 0)
            this.shotTimer -= dt;

        if (this.reloadTimer > 0)
            this.reloadTimer -= dt;
        if (this.reloadTimer < 0) {
            this.ammo = this.ammo - (this.clipMaxAmmo - this.clipAmmo);
            this.clipAmmo = this.ammo > this.clipMaxAmmo ? this.clipMaxAmmo : this.ammo;
            this.ammo = this.ammo > 0 ? this.ammo : 0;
            this.reloadTimer = 0;
            this.reloading = false;
        }
    }

    @Override
    public void reload() {

        if (this.ammo < 1) {
            return;
        }
        if (this.reloadTimer > 0)
            return;
        this.reloadTimer = this.reloadTime;
        this.shotTimer = this.reloadTimer;
        this.reloading = true;
        // WAIT FOR A PERIOD OF TIME THEN DO
    }

    public String getName() {
        return "SemiAutomaticWeapon";
    }


    public double getProjectileAmount() {
        return projectileAmount;
    }

    public void setProjectileAmount(double projectileAmount) {
        this.projectileAmount = projectileAmount;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(double bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public double getBulletLifetime() {
        return bulletLifetime;
    }

    public void setBulletLifetime(double bulletLifetime) {
        this.bulletLifetime = bulletLifetime;
    }

    public double getTimeBetweenShots() {
        return timeBetweenShots;
    }

    public void setTimeBetweenShots(double timeBetweenShots) {
        this.timeBetweenShots = timeBetweenShots;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(double reloadTime) {
        this.reloadTime = reloadTime;
    }

    public double getClipAmmo() {
        return clipAmmo;
    }

    public void setClipAmmo(double clipAmmo) {
        this.clipAmmo = clipAmmo;
    }

    public double getClipMaxAmmo() {
        return clipMaxAmmo;
    }

    public void setClipMaxAmmo(double clipMaxAmmo) {
        this.clipMaxAmmo = clipMaxAmmo;
    }

    public double getAmmo() {
        return ammo;
    }

    public void setAmmo(double ammo) {
        this.ammo = ammo;
    }

    public double getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(double maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public double getSpread() {
        return spread;
    }

    public void setSpread(double spread) {
        this.spread = spread;
    }

    @Override
    public double getReloadTimer() {
        return this.reloadTimer;
    }

    @Override
    public double getShotTimer() {
        return this.shotTimer;
    }

    public class Bullet implements MovableObject {

        double damage;

        protected Vector3 position;
        private Vector3 acceleration;
        private Vector3 velocity;
        Unit owner;
        double speedMultiplier = 1, bulletSpeed;
        double radius = 6;

        public Bullet(Unit u, double damage, double bulletSpeed) throws CloneNotSupportedException {
            this.owner = u;
            this.damage = damage;
            this.position = u.position.clone();
            this.acceleration = new Vector3();
            this.velocity = new Vector3();
            this.bulletSpeed = bulletSpeed;
            GameplayState.bullets.add(Bullet.this);
        }

        public void update(float dt) throws CloneNotSupportedException {
            this.velocity.add(this.acceleration.scale(speedMultiplier, speedMultiplier, 1).scale(dt, dt, 1));
            this.position.add(this.velocity.max(bulletSpeed));
            if (this.position.getZ() < 0.01) {

                this.destroy();
            }
        }

        public void render(Graphics g) {
            g.setColor(bulletColor);
            g.fillOval((float) (this.position.getX() - (this.getRadius() / 2)), (float) (this.position.getY() - (this.getRadius() / 2)), (float) this.getRadius(), (float) this.getRadius());
        }


        public Bullet accelerate(double power, double angle) {
            this.acceleration.add(new Vector3(power * Math.cos(angle), power * Math.sin(angle), 0));
            return this;
        }

        public Bullet accelerate(double x, double y, double z) {
            this.acceleration.add(new Vector3(x, y, z));
            return this;
        }

        public Bullet translate(double x, double y) {
            this.position.add(new Vector3(x, y, 0));
            return this;
        }

        public Bullet translate(double x, double y, double z) {
            this.position.add(new Vector3(x, y, z));
            return this;
        }

        public void performSpecial() throws CloneNotSupportedException {

        }

        public void destroy() {
            GameplayState.bullets.remove(this);
        }

        public boolean owner(Unit u) {
            return owner.equals(u);
        }


        @Override
        public Vector3 getPosition() {
            return this.position;
        }

        @Override
        public Vector3 getAcceleration() {
            return this.acceleration;
        }

        @Override
        public Vector3 getVelocity() {
            return this.velocity;
        }

        @Override
        public boolean isColliding(MovableObject o) {
            return o.getPosition().inRadius(this.position, this.radius, o.getRadius());
        }

        @Override
        public double getRadius() {
            return this.radius;
        }

        public double getDamage() {
            return this.damage;
        }

        public void triggerEvent(EventEnum event) {
            switch (event) {
                case onDeath:
                    try {
                        this.performSpecial();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    this.destroy();
                    break;
                case onDamageDealt:
                    break;
                default:
                    break;
            }
        }

        public void triggerEvents(EventEnum[] events) {
            for (EventEnum e : events) {
                triggerEvent(e);
            }
        }

        public Unit getOwner() {
            return this.owner;
        }

        public Bullet reposition(double x, double y, double z) {
            this.position.add(new Vector3(x - this.position.getX(), y - this.position.getY(), z - this.position.getZ()));
            return this;
        }

        public Bullet reposition(Vector3 target) {
            return this.reposition(target.getX(), target.getY(), target.getZ());
        }
    }

}
