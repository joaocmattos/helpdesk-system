package estruturas;

import models.Ticket;

public class ListaTickets {
    private NoListaTicket inicio;
    private NoListaTicket fim;
    private int tamanho;

    public ListaTickets() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void adicionar(Ticket ticket) {
        NoListaTicket novo = new NoListaTicket(ticket);

        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        tamanho++;
    }

    public Ticket buscar(int id) {
        NoListaTicket atual = inicio;

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
            inicio = inicio.getProximo();
            if (inicio == null) {
                fim = null;
            }
            tamanho--;
            return true;
        }

        NoListaTicket anterior = inicio;
        NoListaTicket atual = inicio.getProximo();

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

    public boolean existeDoCliente(String cpfCnpj) {
        NoListaTicket atual = inicio;

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
            System.out.println("Nenhum ticket nesta lista.");
            return;
        }

        NoListaTicket atual = inicio;
        while (atual != null) {
            System.out.println(atual.getDado());
            atual = atual.getProximo();
        }
    }

    public boolean estaVazia() {
        return inicio == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}