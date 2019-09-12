package br.com.iguana.feirafacil.repositories;

import br.com.iguana.feirafacil.domain.Supermercado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {

    Page<Supermercado> findByNomeContaining(String nome, Pageable pageRequest);
}
