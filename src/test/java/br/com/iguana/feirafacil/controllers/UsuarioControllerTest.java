package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.AbstractTest;
import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.dto.UsuarioDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsuarioControllerTest extends AbstractTest {

    private Usuario usuarioQualquer2;

    public UsuarioControllerTest() {
        usuarioQualquer2 = Usuario.builder().nome("aline").email("aline-400@gmail.com").senha("12345hI@").build();
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
        usuarioService.getRepository().deleteAll();
    }

    @Test
    public void obter_retornara_status_OK_200_quando_passado_id_valido() throws Exception {
        usuarioService.create(usuarioQualquer);
        String uri = "/usuarios/" + usuarioQualquer.getId();
        MvcResult mvcResult = super.get(uri);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        UsuarioDTO dto = super.mapFromJson(content, UsuarioDTO.class);
        assertTrue(dto.getNome().equals("iguana"));
    }

    @Test
    public void obter_retornara_status_404_not_found_quando_passado_id_invalido() throws Exception {
        String uri = "/usuarios/20";
        MvcResult mvcResult = super.get(uri);
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void todos_retornara_status_200_ok() throws Exception {
        String uri = "/usuarios/todos";
        usuarioService.create(usuarioQualquer);
        usuarioService.create(usuarioQualquer2);
        MvcResult mvcResult = super.get(uri);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        UsuarioDTO[] retorno = super.mapFromJson(content, UsuarioDTO[].class);
        assertTrue(retorno.length > 0);
    }

    @Test
    public void insert_retornara_status_201_ok_quando_passado_usuario_valido() throws Exception {
        String uri = "/usuarios";
        UsuarioDTO dto = mapper.map(usuarioQualquer, UsuarioDTO.class);
        MvcResult mvcResult = super.post(uri, dto);
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void insert_retornara_status_400_bad_request_quando_passado_usuario_com_email_invalido() throws Exception {
        String uri = "/usuarios";
        UsuarioDTO dto = UsuarioDTO.builder().nome("aline").email("aline-400@@@@gmail.com").senha("12345hI@").build();
        MvcResult mvcResult = super.post(uri, dto);
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("\"msg\":\"Erro de validação\""));
        assertTrue(content.contains("\"errors\":[{\"fieldName\":\"email\",\"message\":\"Email inválido\"}]"));
    }

    @Test
    public void insert_retornara_status_400_bad_request_quando_passado_usuario_com_campos_obrigatorios_vazios() throws Exception {
        String uri = "/usuarios";
        UsuarioDTO dto = UsuarioDTO.builder().nome("").email("").senha("").build();
        MvcResult mvcResult = super.post(uri, dto);
        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        //{"status":400,"msg":"Erro de validação","timeStamp":1565226504410,"errors":[{"fieldName":"senha","message":"Campo obrigatório"},{"fieldName":"senha","message":"Senha não inválida. A senha deve ter no mínimo 8 caracteres com um dígito numérico, uma letra minúscula, uma maiúscula e um caractere especial."},{"fieldName":"nome","message":"Campo obrigatório"},{"fieldName":"email","message":"Campo obrigatório"}]}
        assertTrue(content.contains("\"msg\":\"Erro de validação\""));
        assertTrue(content.contains("\"fieldName\":\"nome\",\"message\":\"Campo obrigatório\""));
        assertTrue(content.contains("\"fieldName\":\"email\",\"message\":\"Campo obrigatório\""));
        assertTrue(content.contains("\"fieldName\":\"senha\",\"message\":\"Campo obrigatório\"}"));
    }

}

