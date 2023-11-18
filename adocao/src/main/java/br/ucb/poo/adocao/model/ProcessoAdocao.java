package br.ucb.poo.adocao.model;

public class ProcessoAdocao {
    private int id;
    private int usuarioId;
    private int petId;
    private String status;

    public ProcessoAdocao(){}
    
    public ProcessoAdocao(int id, int usuarioId, int petId, String status) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.petId = petId;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
