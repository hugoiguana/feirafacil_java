package br.com.iguana.feirafacil.controllers;


import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.dto.SupermercadoDto;
import br.com.iguana.feirafacil.services.SupermercadoService;
import ma.glasnost.orika.MapperFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SupermercadoControllerTest {

    @Mock
    private SupermercadoService service;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    private SupermercadoController controller;

    @Test
    public void insert_retornara_status_201_ok__quando_supermercado_for_criado() {
        SupermercadoDto dto = SupermercadoDto.builder().nome("Supermercado Iguana").build();
        Supermercado supermercado = Supermercado.builder().nome("Supermercado Iguana").build();
        when(mapper.map(dto, Supermercado.class)).thenReturn(supermercado);
        when(service.insert(any())).thenReturn(supermercado);
        ResponseEntity<Supermercado> response = controller.insert(dto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(response.getBody().getNome(), supermercado.getNome());
    }

    @Test
    public void obter_retornara_status_201_ok_quando_um_supermercado_for_encontrado() {
        SupermercadoDto dto = SupermercadoDto.builder().id(1l).nome("Supermercado Iguana").build();
        Supermercado supermercado = Supermercado.builder().nome("Supermercado Iguana").build();
        when(service.obter(dto.getId())).thenReturn(supermercado);
        when(mapper.map(supermercado, SupermercadoDto.class)).thenReturn(dto);
        ResponseEntity<SupermercadoDto> response = controller.obter(dto.getId());
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(response.getBody().getNome(), dto.getNome());
    }

    @Test
    public void obter_retornara_status_404_not_found_quando_um_supermercado_nao_for_encontrado() {
        SupermercadoController c = mock(SupermercadoController.class);
        doReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).when(c).obter(any());
        ResponseEntity response = c.obter(1l);
        assertEquals(response.getStatusCode().value(), HttpStatus.NOT_FOUND.value());
    }

}
