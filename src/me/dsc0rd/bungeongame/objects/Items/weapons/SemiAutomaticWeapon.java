package me.dsc0rd.bungeongame.objects.Items.weapons;


import me.dsc0rd.bungeongame.objects.Unit;
import org.newdawn.slick.Color;

public class SemiAutomaticWeapon extends BasicWeapon {
    public SemiAutomaticWeapon(WeaponTypeEnum type, double weaponLength, double projectileAmount, double damage, double bulletSpeed, double bulletLifetime, double timeBetweenShots, double reloadTime, double clipMaxAmmo, double maxAmmo, double spread, Color bulletColor) {
        super(type, weaponLength, projectileAmount, damage, bulletSpeed, bulletLifetime, timeBetweenShots, reloadTime, clipMaxAmmo, maxAmmo, spread, bulletColor);
    }


    @Override
    public void shoot(Unit owner, double angle) throws CloneNotSupportedException {
        super.shoot(owner, angle);
    }

    @Override
    public String getName() {
        return "P1";
    }
}
