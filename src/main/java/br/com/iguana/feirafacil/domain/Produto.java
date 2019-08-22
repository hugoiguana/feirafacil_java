package br.com.iguana.feirafacil.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pro_produto")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "pro_id"))
        , @AttributeOverride(name = "dataCriacao", column = @Column(name = "pro_data_criacao"))
        , @AttributeOverride(name = "dataAlteracao", column = @Column(name = "pro_data_alteracao"))
})
public class Produto extends EntidadePersistivel {

    @Column(name = "pro_nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "sec_id", nullable = false)
    private Secao secao;
}
