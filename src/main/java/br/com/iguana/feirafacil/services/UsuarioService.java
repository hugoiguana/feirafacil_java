package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.repositories.UsuarioRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    @Getter
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Usuario> obterUsuarios() {
        return repository.findAll();
    }

    @Transactional
    public Usuario create(Usuario u) {
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return repository.save(u);
    }

}
