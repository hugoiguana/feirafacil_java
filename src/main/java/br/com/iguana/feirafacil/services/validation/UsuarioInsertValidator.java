package br.com.iguana.feirafacil.services.validation;

import br.com.iguana.feirafacil.controllers.exception.FieldMessage;
import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.dto.UsuarioDTO;
import br.com.iguana.feirafacil.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void initialize(UsuarioInsert ann) {
    }

    @Override
    public boolean isValid(UsuarioDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        Usuario u = usuarioService.getRepository().findByEmail(dto.getEmail());
        if (u != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
