package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.config.orika.mapper.UsuarioDTOMapper;
import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.domain.enums.Perfil;
import br.com.iguana.feirafacil.dto.UsuarioDTO;
import br.com.iguana.feirafacil.services.UsuarioService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private UsuarioDTOMapper usuarioDTOMapper;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> obter(@PathVariable Long id) {
        Usuario usuario = service.obter(id);
        UsuarioDTO dto = mapper.map(usuario, UsuarioDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<UsuarioDTO>> todos() {
        List<Usuario> usuarios = service.obterUsuarios();
        List<UsuarioDTO> usuariosDto = mapper.mapAsList(usuarios, UsuarioDTO.class);
        return ResponseEntity.ok().body(usuariosDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.addPerfil(Perfil.USUARIO);
        service.create(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
