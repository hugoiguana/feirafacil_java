package br.com.iguana.feirafacil.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupermercadoDto implements Serializable {

    @NotEmpty(message = "Campo obrigatório")
    private String nome;

}
