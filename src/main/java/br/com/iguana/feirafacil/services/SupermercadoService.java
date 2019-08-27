package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.repositories.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupermercadoService {

    @Autowired
    private SupermercadoRepository repository;

    @Transactional
    public Supermercado insert(Supermercado supermercado) {
        return repository.save(supermercado);
    }
}
