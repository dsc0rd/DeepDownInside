package me.dsc0rd.bungeongame.objects.Items.weapons;

import me.dsc0rd.bungeongame.objects.Unit;

public interface Weapon {


    WeaponTypeEnum getWeaponType();

    void shoot(Unit owner, double angle) throws CloneNotSupportedException;

    void reload();

    String getName();

    void update(float dt);

    double getProjectileAmount();

    void setProjectileAmount(double projectileAmount);

    double getLength();

    void setLength(double length);

    double getDamage();

    void setDamage(double damage);

    double getBulletSpeed();

    void setBulletSpeed(double bulletSpeed);

    double getBulletLifetime();

    void setBulletLifetime(double bulletLifetime);

    double getTimeBetweenShots();

    void setTimeBetweenShots(double timeBetweenShots);

    double getReloadTime();

    void setReloadTime(double reloadTime);

    double getClipAmmo();

    void setClipAmmo(double clipAmmo);

    double getClipMaxAmmo();

    void setClipMaxAmmo(double clipMaxAmmo);

    double getAmmo();

    void setAmmo(double ammo);

    double getMaxAmmo();

    void setMaxAmmo(double maxAmmo);

    double getSpread();

    void setSpread(double spread);

    double getReloadTimer();

    double getShotTimer();

    boolean isReloading();


}
