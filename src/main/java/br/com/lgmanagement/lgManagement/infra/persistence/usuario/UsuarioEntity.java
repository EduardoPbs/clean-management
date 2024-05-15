package br.com.lgmanagement.lgManagement.infra.persistence.usuario;

import br.com.lgmanagement.lgManagement.application.gateways.Authenticable;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "usuarios")
@Entity
@EqualsAndHashCode(of = "id")
public class UsuarioEntity extends Authenticable implements UserDetails {

    @Id
    private String id;

    @Column(unique = true)
    private String email;

    private String password;
    private Boolean is_admin;

    @OneToOne(mappedBy = "usuarioEntity")
    private FuncionarioEntity funcionarioEntity;

    public UsuarioEntity() {
    }

    public UsuarioEntity(String email, String password, Boolean is_admin) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
    }

    public UsuarioEntity(String id, String email, String password, Boolean is_admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
    }

    public void updateUsuario(String email, String password) {
        if (email != null) {
            this.email = email;
        }

        if (password != null) {
            this.password = password;
        }
    }

    public void toAdmin() {
        this.is_admin = true;
    }

    public void toUser() {
        this.is_admin = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return is_admin;
    }

    public FuncionarioEntity getFuncionarioEntity() {
        return funcionarioEntity;
    }

    public void setFuncionarioEntity(FuncionarioEntity funcionarioEntity) {
        this.funcionarioEntity = funcionarioEntity;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_admin=" + is_admin +
                ", funcionarioEntity=" + funcionarioEntity +
                '}';
    }
}
