package me.dsc0rd.bungeongame.logic;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {


    public static double getDistanceBetweenTwoPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public static double getAngleOfLineBetweenTwoPoints(double x1, double y1, double x2, double y2) {
//        double xDiff = x2 > x1 ? x2 - x1 : x1 - x2, yDiff = y2 > y1 ? y2 - y1 : y1 - y2;
        double xDiff = x2 - x1, yDiff = y2 - y1;
        return Math.atan2(yDiff, xDiff);
    }

    public static double randomDouble(double start, double end) {
        return ThreadLocalRandom.current().nextDouble(start, end);
    }

}
