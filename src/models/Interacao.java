package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Interacao {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int id;
    private String autor;
    private String mensagem;
    private LocalDateTime dataHora;

    public Interacao(String autor, String mensagem) {
        this.autor = autor;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "#" + id + " [" + dataHora.format(FORMATO) + "] " + autor + ": " + mensagem;
    }
}