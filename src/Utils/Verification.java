package Utils;

import java.util.Objects;
import java.util.Set;

public class Verification {
    public static void isNegative(Double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Valor negativo");
        }
    }

    public static void isNegative(Integer x) {
        if (x < 0) {
            throw new IllegalArgumentException("Valor negativo");
        }
    }

    public static void isNull(Object o) {
        if (o == null) {
            throw new NullPointerException("Atributo nulo");
        }
    }

    public static void isEmpty(Object o) {
        if (o.equals("")) {
            throw new IllegalArgumentException("Atributo vacío");
        }
    }

    public static void isOutOfBounds(Integer x, Integer lowest, Integer highest) {
        if (x < lowest || x > highest) {
            throw new IllegalArgumentException("Opción no disponible");
        }
    }

    public static void contains(Set<Integer> set, Integer x) {
        if (set.contains(x)) {
            throw new IllegalArgumentException("Este valor ya existe");
        }
    }
}
