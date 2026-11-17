package estruturas;

import models.Cliente;

public class ArvoreBinariaBusca {
    private NoABB raiz;

    public void inserir(Cliente cliente) {
        raiz = inserir(raiz, cliente);
    }

    private NoABB inserir(NoABB no, Cliente cliente) {
        if (no == null) return new NoABB(cliente);
        int cmp = cliente.getCpfCnpj().compareTo(no.getCliente().getCpfCnpj());
        if (cmp < 0) no.setEsquerda(inserir(no.getEsquerda(), cliente));
        else if (cmp > 0) no.setDireita(inserir(no.getDireita(), cliente));
        return no;
    }

    public Cliente buscar(String cpfCnpj) {
        String chave = Cliente.normalizar(cpfCnpj);
        NoABB no = raiz;
        while (no != null) {
            int cmp = chave.compareTo(no.getCliente().getCpfCnpj());
            if (cmp < 0) no = no.getEsquerda();
            else if (cmp > 0) no = no.getDireita();
            else return no.getCliente();
        }
        return null;
    }

    public Cliente remover(String cpfCnpj) {
        String chave = Cliente.normalizar(cpfCnpj);
        Cliente[] removido = new Cliente[1];
        raiz = remover(raiz, chave, removido);
        return removido[0];
    }

    private NoABB remover(NoABB no, String chave, Cliente[] removido) {
        if (no == null) return null;

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

    public void listarEmOrdem() {
        if (raiz == null) {
            System.out.println("  (arvore vazia)");
            return;
        }
        listarEmOrdem(raiz);
    }

    private void listarEmOrdem(NoABB no) {
        if (no == null) return;
        listarEmOrdem(no.getEsquerda());
        Cliente c = no.getCliente();
        System.out.println("  " + c.getNome() + " | CPF/CNPJ: " + c.getCpfCnpj() + " | Plano: " + c.getPlano());
        listarEmOrdem(no.getDireita());
    }
}
