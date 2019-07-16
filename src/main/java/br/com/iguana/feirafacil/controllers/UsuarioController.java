package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Usuario>> todos() {
        List<Usuario> usuarios = service.obterUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }
}
