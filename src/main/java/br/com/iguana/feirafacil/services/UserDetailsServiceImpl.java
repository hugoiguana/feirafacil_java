package br.com.iguana.feirafacil.services;

import br.com.iguana.feirafacil.config.security.UserSecurity;
import br.com.iguana.feirafacil.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = service.getRepository().findByEmail(email);
        if (u == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSecurity(u.getId(), u.getEmail(), u.getSenha(), u.getPerfis());
    }
}
