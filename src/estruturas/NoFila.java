package estruturas;

import models.Ticket;

public class NoFila {
    private Ticket dado;
    private NoFila proximo;

    public NoFila(Ticket dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public Ticket getDado() {
        return dado;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}