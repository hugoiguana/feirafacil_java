package br.com.iguana.feirafacil.util;


import org.junit.Test;

import static br.com.iguana.feirafacil.util.NumericoUtil.eq;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumericoUtilTest {

    @Test
    public void eq_retornara_false_quando_um_dos_parametros_forem_nulos() {
        assertFalse(eq(null, 1L));
        assertFalse(eq(1L, null));
        assertFalse(eq(null, null));
    }

    @Test
    public void eq_retornara_true_quando_forem_iguais() {
        assertTrue(eq(1L, 1L));
    }

    @Test
    public void eq_retornara_false_quando_forem_diferentes() {
        assertFalse(eq(1L, 2L));
    }
}
