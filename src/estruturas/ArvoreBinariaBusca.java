package estruturas;

import models.Cliente;

public class ArvoreBinariaBusca {
    private NoABB raiz;
     
    public Cliente remover(String cpfCnpj) {
        String chave = Cliente.normalizar(cpfCnpj);
        Cliente[] removido = new Cliente[1];
        raiz = remover(raiz, chave, removido);
        return removido[0];
    }

    private NoABB remover(NoABB no, String chave, Cliente[] removido) {
        if (no == null) return null; // não encontrado

        int cmp = chave.compareTo(no.getCliente().getCpfCnpj());
        if (cmp < 0) {
            no.setEsquerda(remover(no.getEsquerda(), chave, removido));
            return no;
        }
        if (cmp > 0) {
            no.setDireita(remover(no.getDireita(), chave, removido));
            return no;
        }

        removido[0] = no.getCliente();

        if (no.getEsquerda() == null && no.getDireita() == null) return null;

       
        if (no.getEsquerda() == null) return no.getDireita();
        if (no.getDireita() == null) return no.getEsquerda();

       
        NoABB sucessor = no.getDireita();
        while (sucessor.getEsquerda() != null) sucessor = sucessor.getEsquerda();
        no.setCliente(sucessor.getCliente());
        no.setDireita(remover(no.getDireita(), sucessor.getCliente().getCpfCnpj(), new Cliente[1]));
        return no;
    }
}
