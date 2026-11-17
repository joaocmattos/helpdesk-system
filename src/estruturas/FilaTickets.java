package estruturas;

import models.Ticket;

public class FilaTickets {
    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaTickets() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void enfileirar(Ticket ticket) {
        NoFila novo = new NoFila(ticket);

        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        tamanho++;
    }

    public Ticket desenfileirar() {
        if (estaVazia()) {
            return null;
        }

        Ticket ticket = inicio.getDado();
        inicio = inicio.getProximo();

        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return ticket;
    }

    public Ticket buscar(int id) {
        NoFila atual = inicio;

        while (atual != null) {
            if (atual.getDado().getId() == id) {
                return atual.getDado();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public boolean remover(int id) {
        if (estaVazia()) {
            return false;
        }

        if (inicio.getDado().getId() == id) {
            desenfileirar();
            return true;
        }

        NoFila anterior = inicio;
        NoFila atual = inicio.getProximo();

        while (atual != null) {
            if (atual.getDado().getId() == id) {
                anterior.setProximo(atual.getProximo());
                if (atual == fim) {
                    fim = anterior;
                }
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
        return false;
    }

    public Ticket verProximo() {
        if (estaVazia()) {
            return null;
        }
        return inicio.getDado();
    }

    public boolean existeDoCliente(String cpfCnpj) {
        NoFila atual = inicio;

        while (atual != null) {
            if (atual.getDado().getCliente().getCpfCnpj().equals(cpfCnpj)) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nenhum ticket aguardando atendimento.");
            return;
        }

        int posicao = 1;
        NoFila atual = inicio;
        while (atual != null) {
            System.out.println(posicao + "º - " + atual.getDado());
            atual = atual.getProximo();
            posicao++;
        }
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}