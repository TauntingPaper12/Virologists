package projlab.util;

import java.util.Map;
import java.util.Objects;

public class Util {
    /**
     * Visszaadja az értékhez tartozó első kulcsot.
     *
     * @param map   amiben keresni kell
     * @param value keresendő érték
     * @param <T>   kulcs típusa
     * @param <E>   érték típusa
     * @return kulcs
     */
    public static <T, E> T getKeyByValue(java.util.Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
