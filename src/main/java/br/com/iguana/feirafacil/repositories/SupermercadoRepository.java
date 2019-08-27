package br.com.iguana.feirafacil.repositories;

import br.com.iguana.feirafacil.domain.Supermercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {

}
