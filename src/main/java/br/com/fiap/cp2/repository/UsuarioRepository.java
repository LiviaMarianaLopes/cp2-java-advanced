package br.com.fiap.cp2.repository;

import br.com.fiap.cp2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    UserDetails findByRg(String rg);
}
