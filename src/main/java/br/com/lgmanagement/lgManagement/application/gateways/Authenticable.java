package br.com.lgmanagement.lgManagement.application.gateways;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public abstract class Authenticable {

    public String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
