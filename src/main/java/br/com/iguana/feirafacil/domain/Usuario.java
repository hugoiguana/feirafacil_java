package br.com.iguana.feirafacil.domain;

import br.com.iguana.feirafacil.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "usu_id"))
        , @AttributeOverride(name = "dataCriacao", column = @Column(name = "usu_data_criacao"))
        , @AttributeOverride(name = "dataAlteracao", column = @Column(name = "usu_data_alteracao"))
})
public class Usuario extends EntidadePersistivel {

    @Column(name = "usu_nome")
    private String nome;

    @Column(name = "usu_email")
    private String email;

    @Column(name = "usu_senha")
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "usu_id"))
    @Column(name = "per_nome")
    private Set<Integer> perfis = new HashSet<>();

    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

    public List<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toList());
    }

    public void addPerfil(Perfil perfil) {
        if (perfil != null) {
            perfis.add(perfil.getCodigo());
        }
    }
}
