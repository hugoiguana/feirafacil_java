package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<Usuario> obterUsuarios() {
        return repository.findAll();
    }

    @Transactional
    public Usuario salvar(Usuario u) {
        return repository.save(u);
    }

}
