package estruturas;

import models.Interacao;

public class NoLista {
    private Interacao dado;
    private NoLista proximo;

    public NoLista(Interacao dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public Interacao getDado() {
        return dado;
    }

    public NoLista getProximo() {
        return proximo;
    }

    public void setProximo(NoLista proximo) {
        this.proximo = proximo;
    }
}