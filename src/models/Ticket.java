package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import estruturas.ListaInteracoes;

public class Ticket {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int id;
    private Cliente cliente;
    private String descricao;
    private StatusEnum status;
    private LocalDateTime dataDeAbertura;
    private ListaInteracoes interacoes;

    public Ticket(int id, Cliente cliente, String descricao) {
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.status = StatusEnum.ABERTO;
        this.dataDeAbertura = LocalDateTime.now();
        this.interacoes = new ListaInteracoes();
    }

    public void iniciarAtendimento() {
        this.status = StatusEnum.EM_ATENDIMENTO;
    }

    public void fechar() {
        this.status = StatusEnum.FECHADO;
    }

    public void adicionarInteracao(String autor, String mensagem) {
        interacoes.adicionar(new Interacao(autor, mensagem));
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataDeAbertura() {
        return dataDeAbertura;
    }

    public ListaInteracoes getInteracoes() {
        return interacoes;
    }

    @Override
    public String toString() {
        return "Ticket #" + id + " | " + cliente.getNome() + " | " + status
                + " | aberto em " + dataDeAbertura.format(FORMATO) + " | " + descricao;
    }
}