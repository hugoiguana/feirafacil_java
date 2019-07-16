package br.com.iguana.feirafacil.config.security.carga;

import br.com.iguana.feirafacil.domain.Usuario;
import br.com.iguana.feirafacil.repositories.UsuarioRepository;
import br.com.iguana.feirafacil.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Profile("dev")
@Configuration
public class CargaInicialDev implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        insertUsuarios();
    }

    private void insertUsuarios() {
        Usuario u1 = new Usuario();
        u1.setId(1l);
        u1.setNome("Hugo Iguana");
        u1.setEmail("hugo.iguanaa@gmail.com");

        Usuario u2 = new Usuario();
        u2.setId(2l);
        u2.setNome("Aline Lucia");
        u2.setEmail("aline-4000@hotmail.com");
        usuarioRepository.saveAll(Arrays.asList(u1, u2));
    }

}
