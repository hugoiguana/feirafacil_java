package br.com.iguana.feirafacil.util;

import static java.util.Objects.nonNull;

public class NumericoUtil {

    private static final int NUM0 = 0;
    private static final int NUM1 = 1;

    public static boolean eq(Long x, Long y) {
        return nonNull(x) && nonNull(y) && x.compareTo(y) == NUM0;
    }
}
