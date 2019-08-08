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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Usuario u = null;
        try {
            u = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            log.error("Usuário com id " + id + " não encontrado", e);
            throw new ObjectNotFoundException("Usuário com id " + id + " não encontrado");
        }
        return u;
    }

    @Transactional
    public Usuario create(Usuario u) {
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        return repository.save(u);
    }
}
