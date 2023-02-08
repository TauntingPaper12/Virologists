package projlab.util;

import java.util.Random;

/**
 * Átírt véletlenszámgeneráló osztály.
 */
public class CustomRandom extends Random {
    /**
     * Megmondja, hogy véletlenszerűen kell-e generáljon számokat.
     */
    private static boolean randomness = true;

    /**
     * Visszaadja, hogy véletlenszerűen kell-e generáljon számokat.
     *
     * @return randomness
     */
    public static boolean isRandomness() {
        return randomness;
    }

    /**
     * Beállítja, hogy véletlenszerűen kell-e generáljon számokat.
     *
     * @param randomness véletlenszerűen kell-e generáljon számokat
     */
    public static void setRandomness(boolean randomness) {
        CustomRandom.randomness = randomness;
    }

    /**
     * Generál egy véletlenszerű számot.
     *
     * @param bound felsőhatár
     * @param lower ha nem véletlenszerű a generálás, akkor alsó határt adjon-e vissza, vagy felsőt
     * @return a szám
     */
    public int nextInt(int bound, boolean lower) {
        if (randomness) {
            return super.nextInt(bound);
        }

        if (lower) {
            return 0;
        } else {
            return bound - 1;
        }
    }
}
