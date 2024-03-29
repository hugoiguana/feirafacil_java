package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.repositories.UsuarioRepository;
import br.com.iguana.feirafacil.services.exception.ObjectNotFoundException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
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

    @Transactional(readOnly = true)
    public Usuario obter(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("Usuário com id " + id + " não encontrado");
            return new ObjectNotFoundException("Usuário com id " + id + " não encontrado");
        });
    }

    @Transactional
    public Usuario create(Usuario u) {
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return repository.save(u);
    }
}
