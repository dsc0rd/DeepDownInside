package me.dsc0rd.bungeongame.objects.Items.weapons;

import org.newdawn.slick.Color;

public class Weaponry {

    public static final Weapon pistol = new SemiAutomaticWeapon("P1", WeaponTypeEnum.SemiAuto, 4, 1, 4, 360, 48, 0.35, 1.5, 7, 35, 0.78, new Color(181, 166, 66));
    public static final Weapon autoRifle = new AutomaticWeapon("AR1", WeaponTypeEnum.Auto, 14, 1, 2, 520, 80, 0.12, 2.2, 30, 180, 2.12, new Color(181, 166, 66));
    public static final Weapon shotgun = new SemiAutomaticWeapon("S1", WeaponTypeEnum.SemiAuto, 10, 4, 3, 375, 36, 4, 3, 5, 100, 7.2, new Color(181, 166, 66));
    public static final Weapon sawedOffShotgun = new SemiAutomaticWeapon("SO1", WeaponTypeEnum.SemiAuto, 6, 6, 1, 390, 24, 4, 3, 5, 100, 14.2, new Color(181, 166, 66));

}
