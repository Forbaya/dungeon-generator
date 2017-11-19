package utils;

/**
 * Constants for all classes to use.
 */
public class Constants {
    public static int TILE_SIZE = 16;
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = SCREEN_WIDTH / 16 * 9;
    public static int CIRCLE_RADIUS = TILE_SIZE * 16;
    public static int CELL_MIN_TILES = 2;
    public static int CELL_MAX_TILES = 5;
    public static int CIRCLE_CENTER_X = Constants.SCREEN_WIDTH / 2 - Constants.SCREEN_WIDTH / 2 % Constants.TILE_SIZE;
    public static int CIRCLE_CENTER_Y = Constants.SCREEN_HEIGHT / 2 - Constants.SCREEN_HEIGHT / 2 % Constants.TILE_SIZE;
}
