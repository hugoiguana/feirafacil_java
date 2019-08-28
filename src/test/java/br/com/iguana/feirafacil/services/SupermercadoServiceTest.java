package br.com.iguana.feirafacil.services;


import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.repositories.SupermercadoRepository;
import br.com.iguana.feirafacil.services.exception.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupermercadoServiceTest {

    @Mock
    private SupermercadoRepository repository;

    @InjectMocks
    private SupermercadoService service;

    private Supermercado supermercado;

    @Before
    public void before() {
        supermercado = Supermercado.builder().build();
        supermercado.setId(1l);
    }

    @Test
    public void insert_retornara_supermercado() {
        doReturn(supermercado).when(repository).save(any());
        Supermercado supermercadoRetornado = service.insert(supermercado);
        assertTrue(supermercadoRetornado.getId().equals(1l));
    }

    @Test
    public void obter_retornara_supermercado_quando_encontrado() {
        doReturn(Optional.of(supermercado)).when(repository).findById(any());
        Supermercado supermercadoRetornado = service.obter(1l);
        assertTrue(supermercadoRetornado.getId().equals(1l));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void obter_retornara_excecao_quando_supermercado_nao_for_encontrado() {
        doThrow(ObjectNotFoundException.class).when(repository).findById(any());
        service.obter(1l);
    }


}
