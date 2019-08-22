package br.com.iguana.feirafacil.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sec_secao")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sec_id"))
        , @AttributeOverride(name = "dataCriacao", column = @Column(name = "sec_data_criacao"))
        , @AttributeOverride(name = "dataAlteracao", column = @Column(name = "sec_data_alteracao"))
})
public class Secao extends EntidadePersistivel {

    @Column(name = "sec_nome", nullable = false)
    private String nome;

}
