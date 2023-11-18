package br.ucb.poo.adocao.model;

public class Usuario extends BaseEntity {
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha); 
    }

}