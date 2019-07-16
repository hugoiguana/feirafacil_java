package br.com.iguana.feirafacil.controllers;

import br.com.iguana.feirafacil.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {


    @GetMapping(value = "/todos")
    public ResponseEntity<List<Usuario>> todos() {

        Usuario u1 = new Usuario();
        u1.setId(1l);
        u1.setNome("Hugo Iguana");
        u1.setEmail("hugo.iguanaa@gmail.com");

        Usuario u2 = new Usuario();
        u2.setId(1l);
        u2.setNome("Aline Lucia");
        u2.setEmail("aline-4000@hotmail.com");

        return ResponseEntity.ok().body(Arrays.asList(u1, u2));
    }
}
