package utils;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Utils {

    /**
     * Global random variable.
     */
    public static Random random = new Random();

    /**
     * Snaps a given number into the grid.
     *
     * @param x number to be snapped into grid
     * @return x snapped to the grid
     */
    public static int snapIntoGrid(double x) {
        return (int)Math.floor((x + Constants.TILE_SIZE - 1) / Constants.TILE_SIZE) * Constants.TILE_SIZE;
    }

    /**
     * Checks if two rectangles collide, meaning that they overlap in both x- and y-axes.
     *
     * @param a Rectangle a
     * @param b Rectangle b
     * @return true if Rectangles a and b overlap in both x- and y-axes
     */
    public static boolean checkCollision(Rectangle a, Rectangle b) {
        double aLeft = a.getX();
        double aRight = aLeft + a.getWidth();
        double aTop = a.getY();
        double aBottom = aTop + a.getHeight();

        double bLeft = b.getX();
        double bRight = bLeft + b.getWidth();
        double bTop = b.getY();
        double bBottom = bTop + b.getHeight();

        return aBottom - bTop > 1 && aTop - bBottom < -1 && aRight - bLeft > 1 && aLeft - bRight < -1;
    }

    public static boolean checkNextToEachOther(Rectangle a, Rectangle b) {
        double aLeft = a.getX();
        double aRight = aLeft + a.getWidth();
        double aTop = a.getY();
        double aBottom = aTop + a.getHeight();

        double bLeft = b.getX();
        double bRight = bLeft + b.getWidth();
        double bTop = b.getY();
        double bBottom = bTop + b.getHeight();

        return (aRight == bLeft && aBottom > bTop && aTop < bBottom) || (aLeft == bRight && aBottom > bTop && aTop < bBottom) ||
                (aBottom == bTop && bRight > aLeft && bLeft < aRight) || (aTop == bBottom && bRight > aLeft && bLeft < aRight);
    }
}
