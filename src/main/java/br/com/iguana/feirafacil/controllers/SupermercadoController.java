package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.dto.Ordenacao;
import br.com.iguana.feirafacil.dto.SupermercadoDto;
import br.com.iguana.feirafacil.services.SupermercadoService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.iguana.feirafacil.dto.Ordenacao.*;

@RestController
@RequestMapping(value = "/supermercado")
public class SupermercadoController {

    @Autowired
    private SupermercadoService service;

    @Autowired
    private MapperFacade mapper;

    @PostMapping
    public ResponseEntity<Supermercado> insert(@Valid @RequestBody SupermercadoDto dto) {
        Supermercado supermercado = mapper.map(dto, Supermercado.class);
        supermercado = service.insert(supermercado);
        return ResponseEntity.ok().body(supermercado);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SupermercadoDto> obter(@PathVariable Long id) {
        Supermercado supermercado = service.obter(id);
        SupermercadoDto dto = mapper.map(supermercado, SupermercadoDto.class);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<SupermercadoDto>> consultar(
            //@RequestParam(value = PROPERTY, defaultValue = "") String nome,
            @RequestBody SupermercadoDto dto,
            @RequestParam(value = PAGE, defaultValue = PAGE_VALUE) Integer page,
            @RequestParam(value = LINES_PER_PAGE, defaultValue = LINES_PER_PAGE_VALUE) Integer linesPerPage,
            @RequestParam(value = ORDER_BY, defaultValue = ORDER_BY_VALUE) String orderBy,
            @RequestParam(value = DIRECTION, defaultValue = ASC) String direction) {
        Page<Supermercado> pageSupermercado = service.consultar(dto.getNome(), page, linesPerPage, orderBy, direction);
        Page<SupermercadoDto> pageDto = pageSupermercado.map(s -> mapper.map(s, SupermercadoDto.class));
        return ResponseEntity.ok().body(pageDto);
    }
}

