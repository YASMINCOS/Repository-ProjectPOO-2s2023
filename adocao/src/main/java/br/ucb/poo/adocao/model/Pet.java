package br.ucb.poo.adocao.model;

public class Pet extends BaseEntity {
    private int id;
    private String nome;
    private String raca;
    private boolean disponivel;

    public Pet(int id, String nome, String raca, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.disponivel = disponivel;
    }

    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return this.raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}