package estruturas;

import models.Cliente;

public class ArvoreBinariaBusca {
    private NoABB raiz;

    private int tamanho;

    public boolean inserir(Cliente cliente) {
        if (cliente == null || buscar(cliente.getCpfCnpj()) != null) return false;
        raiz = inserir(raiz, cliente);
        tamanho++;
        return true;
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
        if (removido[0] != null) tamanho--;
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
        imprimir(no.getCliente());
        listarEmOrdem(no.getDireita());
    }

    public void listarPreOrdem() {
        if (raiz == null) {
            System.out.println("  (arvore vazia)");
            return;
        }
        listarPreOrdem(raiz);
    }

    private void listarPreOrdem(NoABB no) {
        if (no == null) return;
        imprimir(no.getCliente());
        listarPreOrdem(no.getEsquerda());
        listarPreOrdem(no.getDireita());
    }

    public void listarPosOrdem() {
        if (raiz == null) {
            System.out.println("  (arvore vazia)");
            return;
        }
        listarPosOrdem(raiz);
    }

    private void listarPosOrdem(NoABB no) {
        if (no == null) return;
        listarPosOrdem(no.getEsquerda());
        listarPosOrdem(no.getDireita());
        imprimir(no.getCliente());
    }

    private void imprimir(Cliente c) {
        System.out.println("  " + c.getNome() + " | CPF/CNPJ: " + c.getCpfCnpj() + " | Plano: " + c.getPlano());
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}