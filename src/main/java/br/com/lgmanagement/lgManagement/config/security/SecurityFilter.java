package br.com.lgmanagement.lgManagement.config.security;

import br.com.lgmanagement.lgManagement.config.jwt.TokenService;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Order(1)
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails usuario = usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}