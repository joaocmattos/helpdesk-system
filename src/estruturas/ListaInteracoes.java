package estruturas;

import models.Interacao;

public class ListaInteracoes {
    private NoLista inicio;
    private NoLista fim;
    private int tamanho;
    private int proximoId;

    public ListaInteracoes() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
        this.proximoId = 1;
    }

    public void adicionar(Interacao interacao) {
        interacao.setId(proximoId);
        proximoId++;

        NoLista novo = new NoLista(interacao);

        if (estaVazia()) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        tamanho++;
    }

    public Interacao buscar(int id) {
        NoLista atual = inicio;

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

        NoLista anterior = inicio;
        NoLista atual = inicio.getProximo();

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

    public void listar() {
        if (estaVazia()) {
            System.out.println("Nenhuma interação registrada.");
            return;
        }

        NoLista atual = inicio;
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