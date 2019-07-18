package br.com.iguana.feirafacil.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AutenticacaoUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
}