package me.dsc0rd.bungeongame.objects.Items.weapons;

import org.newdawn.slick.Color;

public class AutomaticWeapon extends BasicWeapon {


    public AutomaticWeapon(WeaponTypeEnum type, double weaponLength, double projectileAmount, double damage, double bulletSpeed, double bulletLifetime, double timeBetweenShots, double reloadTime, double clipMaxAmmo, double maxAmmo, double spread, Color bulletColor) {
        super(type, weaponLength, projectileAmount, damage, bulletSpeed, bulletLifetime, timeBetweenShots, reloadTime, clipMaxAmmo, maxAmmo, spread, bulletColor);
    }

    @Override
    public String getName() {
        return "AR_1";
    }
}
