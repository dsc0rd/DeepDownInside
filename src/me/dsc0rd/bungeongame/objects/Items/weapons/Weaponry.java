package me.dsc0rd.bungeongame.objects.Items.weapons;

import org.newdawn.slick.Color;

public class Weaponry {

    public static final Weapon pistol = new SemiAutomaticWeapon(WeaponTypeEnum.SemiAuto, 4, 1, 4, 360, 48, 0.35, 1.5, 7, 35, 0.78, new Color(181, 166, 66));
    public static final Weapon autoRifle = new AutomaticWeapon(WeaponTypeEnum.Auto, 14, 1, 2, 520, 80, 0.12, 2.2, 30, 900, 1.42, new Color(181, 166, 66));


}
