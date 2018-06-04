package me.dsc0rd.bungeongame.objects.Items.weapons;


import me.dsc0rd.bungeongame.objects.Unit;

public class SemiAutomaticWeapon extends BasicWeapon {
    public SemiAutomaticWeapon(double weaponLength, double projectileAmount, double damage, double bulletSpeed, double bulletLifetime, double timeBetweenShots, double reloadTime, double clipMaxAmmo, double maxAmmo, double spread) {
        super(weaponLength, projectileAmount, damage, bulletSpeed, bulletLifetime, timeBetweenShots, reloadTime, clipMaxAmmo, maxAmmo, spread);
    }


    @Override
    public void shoot(Unit owner, double angle) throws CloneNotSupportedException {
        super.shoot(owner, angle);
    }


}
