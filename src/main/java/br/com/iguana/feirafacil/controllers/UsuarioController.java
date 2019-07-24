package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.domain.enums.Perfil;
import br.com.iguana.feirafacil.dto.UsuarioDTO;
import br.com.iguana.feirafacil.services.UsuarioService;
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

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Usuario>> todos() {
        List<Usuario> usuarios = service.obterUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build();
        usuario.addPerfil(Perfil.USUARIO);
        service.create(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
