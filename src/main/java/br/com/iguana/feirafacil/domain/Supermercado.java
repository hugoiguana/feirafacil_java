package br.com.iguana.feirafacil.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sup_supermercado")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sup_id"))
        , @AttributeOverride(name = "dataCriacao", column = @Column(name = "sup_data_criacao"))
        , @AttributeOverride(name = "dataAlteracao", column = @Column(name = "sup_data_alteracao"))
})
public class Supermercado extends EntidadePersistivel {

    @Column(name = "sup_nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "supermercado")
    private Set<Feira> feiras = new HashSet<>();

}
