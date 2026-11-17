package sistema;

import estruturas.ArvoreBinariaBusca;
import models.Cliente;

public class Main {
    public static void main(String[] args) {

        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();

        Cliente c1 = new Cliente("500.000.000-01", "Carlos",  "(11) 91111-1111", "Gold");
        Cliente c2 = new Cliente("300.000.000-02", "Ana",     "(11) 92222-2222", "Silver");
        Cliente c3 = new Cliente("700.000.000-03", "Bruno",   "(11) 93333-3333", "Gold");
        Cliente c4 = new Cliente("400.000.000-04", "Diana",   "(11) 94444-4444", "Basic");
        Cliente c5 = new Cliente("600.000.000-05", "Eduardo", "(11) 95555-5555", "Silver");
        Cliente c6 = new Cliente("800.000.000-06", "Fernanda","(11) 96666-6666", "Gold");

        abb.inserir(c1);
        abb.inserir(c2);
        abb.inserir(c3);
        abb.inserir(c4);
        abb.inserir(c5);
        abb.inserir(c6);

        System.out.println("=== ARVORE ANTES DAS REMOCOES (in-ordem) ===");
        abb.listarEmOrdem();

        System.out.println("\n--- Removendo no FOLHA: Diana (400.000.000-04) ---");
        Cliente removido = abb.remover("400.000.000-04");
        System.out.println("  Removido: " + (removido != null ? removido.getNome() : "nao encontrado"));
        abb.listarEmOrdem();

        System.out.println("\n--- Removendo Eduardo (600.000.000-05) para deixar Bruno com 1 filho ---");
        removido = abb.remover("600.000.000-05");
        System.out.println("  Removido: " + (removido != null ? removido.getNome() : "nao encontrado"));
        abb.listarEmOrdem();

        System.out.println("\n--- Removendo no com 1 FILHO: Bruno (700.000.000-03) ---");
        removido = abb.remover("700.000.000-03");
        System.out.println("  Removido: " + (removido != null ? removido.getNome() : "nao encontrado"));
        abb.listarEmOrdem();

        System.out.println("\n--- Removendo no com 2 FILHOS: Carlos/raiz (500.000.000-01) ---");
        removido = abb.remover("500.000.000-01");
        System.out.println("  Removido: " + (removido != null ? removido.getNome() : "nao encontrado"));
        abb.listarEmOrdem();

        System.out.println("\n=== BUSCAS FINAIS ===");
        Cliente encontrado = abb.buscar("300.000.000-02");
        System.out.println("Busca por Ana (300.000.000-02): " + (encontrado != null ? encontrado : "nao encontrado"));

        encontrado = abb.buscar("800.000.000-06");
        System.out.println("Busca por Fernanda (800.000.000-06): " + (encontrado != null ? encontrado : "nao encontrado"));

        encontrado = abb.buscar("400.000.000-04");
        System.out.println("Busca por Diana (400.000.000-04): " + (encontrado != null ? encontrado : "nao encontrado"));
    }
}

