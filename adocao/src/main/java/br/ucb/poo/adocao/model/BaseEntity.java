package br.ucb.poo.adocao.model;

public class BaseEntity {
    private int id;
    private static int nextId = 1;

    public BaseEntity() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}