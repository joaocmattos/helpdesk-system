package estruturas;

import models.Cliente;

public class NoABB {
    private Cliente cliente;
    private NoABB esquerda;
    private NoABB direita;

    public NoABB(Cliente cliente) {
        this.cliente = cliente;
        this.esquerda = null;
        this.direita = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public NoABB getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoABB esquerda) {
        this.esquerda = esquerda;
    }

    public NoABB getDireita() {
        return direita;
    }

    public void setDireita(NoABB direita) {
        this.direita = direita;
    }
}
