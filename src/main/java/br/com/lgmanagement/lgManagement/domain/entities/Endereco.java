package br.com.lgmanagement.lgManagement.domain.entities;

public class Endereco {

    private String rua;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco() {
    }

    public Endereco(String rua, String bairro, String numero, String complemento) {
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void updateEndereco(String rua, String bairro, String numero, String complemento) {
        if (rua != null) {
            this.rua = rua;
        }

        if (bairro != null) {
            this.bairro = bairro;
        }

        if (numero != null) {
            this.numero = numero;
        }

        if (complemento != null) {
            this.complemento = complemento;
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
