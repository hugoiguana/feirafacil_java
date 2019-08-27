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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SupermercadoControllerTest {

    @Mock
    private SupermercadoService service;

    @Mock
    private MapperFacade mapper;

    @InjectMocks
    private SupermercadoController controller;

    @Test
    public void insert_retornara_status_201_ok__quando_supermercado_for_criado() throws Exception {
        SupermercadoDto dto = SupermercadoDto.builder().nome("Supermercado Iguana").build();
        Supermercado supermercado = Supermercado.builder().nome("Supermercado Iguana").build();
        when(mapper.map(dto, Supermercado.class)).thenReturn(supermercado);
        when(service.insert(any())).thenReturn(supermercado);
        ResponseEntity<Supermercado> response = controller.insert(dto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(response.getBody().getNome(), supermercado.getNome());
    }


}
