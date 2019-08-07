package br.com.iguana.feirafacil.util;

import org.junit.Test;

import static br.com.iguana.feirafacil.util.Senha.isNotValid;
import static br.com.iguana.feirafacil.util.Senha.isValid;
import static org.junit.Assert.assertTrue;

public class SenhaTest {

    @Test
    public void isValid_a_senha_eh_valida() {
        assertTrue(isValid("12345Aa@"));
        assertTrue(isValid("123b#Aa@"));
        assertTrue(isValid("1ayu5Aa@"));
        assertTrue(isValid("1ayu5Aa@12jh"));
    }

    @Test
    public void isNotValid_a_senha_eh_invalida() {
        assertTrue(isNotValid("12345"));
        assertTrue(isNotValid("12345678"));
        assertTrue(isNotValid("123456aa"));
        assertTrue(isNotValid("abcdefgh"));
        assertTrue(isNotValid("1bcdefg@"));
        assertTrue(isNotValid("1bcdefAg"));
    }

}
