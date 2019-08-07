package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.AbstractTest;
import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.domain.enums.Perfil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;

public class UsuarioControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void insert_usuario_inserido_com_sucesso() throws Exception {
        String uri = "/usuarios";
        Usuario u = Usuario.builder().nome("iguana").email("hugo.iguanaa@gmail.com").senha("12345hI@").build();
        u.addPerfil(Perfil.USUARIO);
        MvcResult mvcResult = super.post(uri, u);
        assertEquals(201, mvcResult.getResponse().getStatus());
    }

}
