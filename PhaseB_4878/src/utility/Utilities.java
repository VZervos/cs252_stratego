package utility;

public class Utilities {

    /**
     * Returns a random number in [min, max)
     *
     * @param min Min value
     * @param max Max value (excluded)
     * @return A random in [min, max)
     * @post Number returned belongs to [min, max)
     */
    public static int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Checks if the position (y,x) is contained in array a
     *
     * @param a Array to be checked
     * @param y Y
     * @param x X
     * @return True if it is contained, false if not
     * @post Whether (y,x) is contained in array a is returned
     */
    public static boolean isContained(int[][] a, int y, int x) {
        for (int[] ints : a) {
            if (ints[0] == y && ints[1] == x)
                return true;
        }
        return false;
    }
}
