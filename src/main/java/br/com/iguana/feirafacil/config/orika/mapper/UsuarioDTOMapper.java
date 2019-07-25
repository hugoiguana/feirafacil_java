package br.com.iguana.feirafacil.config.orika.mapper;


import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.dto.UsuarioDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.MappingDirection;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UsuarioDTOMapper extends CustomMapper<UsuarioDTO, Usuario> {

    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioDTOMapper(MapperFactory mapperFactory, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        mapperFactory.classMap(UsuarioDTO.class, Usuario.class)
                .customize(this)
                .byDefault(MappingDirection.A_TO_B)
                .register();
    }

    @Override
    public void mapAtoB(UsuarioDTO dto, Usuario usuario, MappingContext context) {
        if (!Objects.isNull(dto.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
    }

    @Override
    public void mapBtoA(Usuario usuario, UsuarioDTO dto, MappingContext context) {
        super.mapBtoA(usuario, dto, context);
    }
}
