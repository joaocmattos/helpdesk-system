package estruturas;

import models.Ticket;

public class NoListaTicket {
    private Ticket dado;
    private NoListaTicket proximo;

    public NoListaTicket(Ticket dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public Ticket getDado() {
        return dado;
    }

    public NoListaTicket getProximo() {
        return proximo;
    }

    public void setProximo(NoListaTicket proximo) {
        this.proximo = proximo;
    }
}