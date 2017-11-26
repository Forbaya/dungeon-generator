package utils;

/**
 * A tuple.
 *
 * @param <T1> Type of first element
 * @param <T2> Type of second element
 */
public class Tuple<T1, T2> {
    public T1 x;
    public T2 y;

    public Tuple(T1 x, T2 y) {
        this.x = x;
        this.y = y;
    }
}
