package br.com.iguana.feirafacil.config.orika.mapper;


import br.com.iguana.feirafacil.domain.Supermercado;
import br.com.iguana.feirafacil.dto.SupermercadoDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.MappingDirection;
import org.springframework.stereotype.Component;

@Component
public class SupermercadoDTOMapper extends CustomMapper<SupermercadoDto, Supermercado> {

    public SupermercadoDTOMapper(MapperFactory mapperFactory) {
        mapperFactory.classMap(SupermercadoDto.class, Supermercado.class)
                .customize(this)
                .byDefault(MappingDirection.A_TO_B)
                .register();
    }

}
