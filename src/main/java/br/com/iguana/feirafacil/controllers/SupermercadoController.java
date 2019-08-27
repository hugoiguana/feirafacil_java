package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.dto.SupermercadoDto;
import br.com.iguana.feirafacil.services.SupermercadoService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/supermercado")
public class SupermercadoController {

    @Autowired
    private SupermercadoService supermercadoService;

    @Autowired
    private MapperFacade mapper;

    @PostMapping
    public ResponseEntity<Supermercado> insert(@Valid @RequestBody SupermercadoDto dto) {
        Supermercado supermercado = mapper.map(dto, Supermercado.class);
        supermercado = supermercadoService.insert(supermercado);
        return ResponseEntity.ok().body(supermercado);
    }
}

