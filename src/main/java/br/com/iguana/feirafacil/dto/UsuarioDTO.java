package br.com.iguana.feirafacil.dto;

import br.com.iguana.feirafacil.services.validation.UsuarioInsert;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UsuarioInsert
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Campo obrigatório")
    private String nome;

    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Campo obrigatório")
    private String senha;
}
