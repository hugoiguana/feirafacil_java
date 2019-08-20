package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.repositories.UsuarioRepository;
import br.com.iguana.feirafacil.services.exception.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.iguana.feirafacil.util.NumericoUtil.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServicesTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Before
    public void before() {
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void obterUsuarios() {
        List<Usuario> usuariosEsperados = new ArrayList<>();
        usuariosEsperados.add(Usuario.builder().nome("hugo").build());
        usuariosEsperados.add(Usuario.builder().nome("iguana").build());
        when(repository.findAll()).thenReturn(usuariosEsperados);
        List<Usuario> usuariosRetornados = usuarioService.obterUsuarios();
        assertTrue(usuariosRetornados.size() == 2);
        assertEquals(usuariosRetornados.get(0).getNome(), "hugo");
        assertEquals(usuariosRetornados.get(1).getNome(), "iguana");
    }

    @Test
    public void obter_retornara_usuario_quando_encontrado() {
        Usuario usuarioEsperado = Usuario.builder().nome("hugo").build();
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(usuarioEsperado));
        Usuario usuarioRetornado = usuarioService.obter(1l);
        assertEquals(usuarioEsperado.getNome(), usuarioRetornado.getNome());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void obter_lancara_excecao_quando_nao_encontrado() {
        when(repository.findById(any(Long.class))).thenReturn(Optional.empty());
        usuarioService.obter(1l);
    }

    @Test
    public void create() {
        Usuario usuario = Usuario.builder().nome("hugo").senha("123").build();
        String senhaCodificada = "asasasasadef";
        Usuario usuarioEsperado = Usuario.builder().nome("hugo").senha(senhaCodificada).build();
        usuarioEsperado.setId(1l);
        when(passwordEncoder.encode(usuario.getSenha())).thenReturn(senhaCodificada);
        when(repository.save(usuario)).thenReturn(usuarioEsperado);
        Usuario usuarioRetornado = usuarioService.create(usuario);
        assertTrue(eq(usuarioEsperado.getId(), usuarioRetornado.getId()));
        assertTrue(usuarioEsperado.getSenha().equals(usuarioRetornado.getSenha()));
    }
}
