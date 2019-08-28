package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.repositories.SupermercadoRepository;
import br.com.iguana.feirafacil.services.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class SupermercadoService {

    @Autowired
    private SupermercadoRepository repository;

    @Transactional
    public Supermercado insert(Supermercado supermercado) {
        return repository.save(supermercado);
    }

    @Transactional(readOnly = true)
    public Supermercado obter(Long id) {
        return repository.findById(id).orElseThrow(() -> {
                    log.error("Supermercado com id " + id + " não encontrado");
                    return new ObjectNotFoundException("Supermercado com id " + id + " não encontrado");
                }
        );
    }
}
