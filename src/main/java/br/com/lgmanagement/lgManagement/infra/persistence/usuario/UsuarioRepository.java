package br.com.lgmanagement.lgManagement.infra.persistence.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    UserDetails findByEmail(String email);
}
