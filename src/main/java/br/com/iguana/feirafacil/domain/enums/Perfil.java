package br.com.iguana.feirafacil.domain.enums;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    USUARIO(2, "ROLE_USUARIO");

    private Integer codigo;
    private String role;

    private Perfil(Integer codigo, String role) {
        this.codigo = codigo;
        this.role = role;
    }

    public static Perfil toEnum(Integer cod) {
        for (Perfil p : Perfil.values()) {
            if (cod.equals(p.getCodigo())) {
                return p;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + cod);
    }
}
