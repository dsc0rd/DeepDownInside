package me.dsc0rd.bungeongame.objects.Items.weapons;

import org.newdawn.slick.Color;

public class Weaponry {

    public static final Weapon ultraSlowBulletWeaponTESTINGONLY = new ProjectileBasedWeapon("DBGun", WeaponTypeEnum.SemiAuto, 4, 1, 4, 10, 8000, 0.35, 1.5, 35000, 35000, 0.78, new Color(181, 166, 66));
    public static final Weapon pistol = new ProjectileBasedWeapon("P1", WeaponTypeEnum.SemiAuto, 4, 1, 4, 475, 48, 0.35, 1.5, 7, 35, 0.78, new Color(181, 166, 66));
    public static final Weapon autoRifle = new ProjectileBasedWeapon("AR1", WeaponTypeEnum.Auto, 14, 1, 2, 650, 80, 0.12, 2.2, 30, 180, 2.12, new Color(181, 166, 66));
    public static final Weapon shotgun = new ProjectileBasedWeapon("S1", WeaponTypeEnum.SemiAuto, 10, 4, 3, 550, 36, 4, 3, 5, 100, 7.2, new Color(181, 166, 66));
    public static final Weapon sawedOffShotgun = new ProjectileBasedWeapon("SO1", WeaponTypeEnum.SemiAuto, 6, 6, 1, 500, 24, 4, 3, 5, 100, 14.2, new Color(181, 166, 66));
    public static final Weapon minigun = new ProjectileBasedWeapon("MG1", WeaponTypeEnum.Auto, 12, 1, 2, 1000, 96, 0.04, 7, 1000, 10000, 0.15, new Color(181, 166, 66));
}
