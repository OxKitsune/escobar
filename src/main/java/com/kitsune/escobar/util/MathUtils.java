package com.kitsune.escobar.util;

import org.bukkit.util.Vector;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author kitsune
 * @version 1.1
 * A math utils class to make complicated particle effects by simplifying several trigonometric functions
 */

public class MathUtils {


    private static Random random = new Random();

    /**
     * Rotates {@link Vector} around the x-axis by <code>theta</code> degrees
     * @param vector The vector to rotate
     * @param theta The angle to rotate by
     * @return The rotated {@link Vector}
     */
    public static Vector rotateAroundX (Vector vector, double theta){
        double y = cos(theta) * vector.getY() - sin(theta) * vector.getZ();
        double z = sin(theta) * vector.getY() + cos(theta) * vector.getZ();

        return vector.clone().setY(y).setZ(z);
    }

    /**
     * Rotates {@link Vector} around the y-axis by <code>theta</code> degrees
     * @param vector The vector to rotate
     * @param theta The angle to rotate by
     * @return The rotated {@link Vector}
     */
    public static Vector rotateAroundY (Vector vector, double theta){
        double x = cos(theta) * vector.getX() + sin(theta) * vector.getZ();
        double z = -sin(theta) * vector.getX() + cos(theta) * vector.getZ();

        return vector.clone().setX(x).setZ(z);
    }

    /**
     * Rotates {@link Vector} around the z-axis by <code>theta</code> degrees
     * @param vector The vector to rotate
     * @param theta The angle to rotate by
     * @return The rotated {@link Vector}
     */
    public static Vector rotateAroundZ (Vector vector, double theta){
        double x = cos(theta) * vector.getX() - sin(theta) * vector.getY();
        double y = sin(theta) * vector.getX() + cos(theta) * vector.getY();

        return vector.clone().setX(x).setY(y);
    }


    /**
     * Compares two doubles using the given precision.
     * @param d1        the first double
     * @param d2        the second double
     * @param precision the precision (higher precision means more accurate comparison)
     * @return          whether the given doubles are equal using the given precision, this is used to accommodate for
     *                  small inaccuracies that occur when working with doubles
     */
    public static boolean doubleEquals(double d1, double d2, int precision){
        return Math.abs(d1 - d2) < Math.pow(1, -precision);
    }

    /**
     * Returns a pseudo-random {@link Integer} between two bounds.
     * @param min the minimum value
     * @param max the maximum value (inclusive)
     * @return    a pseudo-random {@link Integer} between the minimum and maximum value
     */
    public static int randomInt (int min, int max) {
        max++;
        return random.nextInt(max - min) + min;
    }

    /**
     * Returns a pseudo-random {@link Float} between two bounds.
     * @param min the minimum value
     * @param max the maximum value (exclusive)
     * @return    a pseudo-random {@link Float} between the minimum and maximum value
     */
    public static float randomFloat (float min, float max){
        return (random.nextFloat() * (max - min)) + min;
    }

    /**
     * Returns a pseudo-random {@link Double} between two bounds.
     * @param min the minimum value
     * @param max the maximum value (exclusive)
     * @return    a pseudo-random {@link Double} between the minimum and maximum value
     */
    public static double randomDouble(double min, double max) {
        return (random.nextDouble() * (max - min)) + min;
    }
}
