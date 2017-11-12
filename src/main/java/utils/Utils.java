package utils;

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
}
