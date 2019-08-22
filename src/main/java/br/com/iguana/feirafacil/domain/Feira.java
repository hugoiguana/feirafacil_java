package br.com.iguana.feirafacil.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fei_feira")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "fei_id"))
        , @AttributeOverride(name = "dataCriacao", column = @Column(name = "fei_data_criacao"))
        , @AttributeOverride(name = "dataAlteracao", column = @Column(name = "fei_data_alteracao"))
})
public class Feira extends EntidadePersistivel {

    @Column(name = "fei_data", nullable = false)
    private LocalDate data;

    @Column(name = "fei_descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usu_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sup_id", nullable = false)
    private Supermercado supermercado;

}
