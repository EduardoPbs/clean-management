package br.com.lgmanagement.lgManagement.domain.entities.usuario;

public class Usuario {

    private String email;
    private String password;
    private Boolean isAdmin;

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
        this.isAdmin = false;
    }

    public void updateUsuario(String email, String password) {
        if (email != null) {
            this.email = email;
        }

        if (password != null) {
            this.password = password;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_admin=" + isAdmin +
                '}';
    }
}
