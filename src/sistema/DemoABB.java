package sistema;

import java.util.Scanner;

import models.Cliente;
import models.Ticket;

public class DemoABB {
    private static final Scanner entrada = new Scanner(System.in);
    private static final SistemaHelpDesk sistema = new SistemaHelpDesk();

    public static void main(String[] args) {
        sistema.carregarDadosExemplo();

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            System.out.println();
            executar(opcao);
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== HELP DESK =====");
        System.out.println("--- Clientes ---");
        System.out.println(" 1 - Cadastrar cliente");
        System.out.println(" 2 - Buscar cliente");
        System.out.println(" 3 - Remover cliente");
        System.out.println(" 4 - Listar clientes (em-ordem)");
        System.out.println(" 5 - Listar clientes (pré-ordem)");
        System.out.println(" 6 - Listar clientes (pós-ordem)");
        System.out.println("--- Tickets ---");
        System.out.println(" 7 - Abrir ticket");
        System.out.println(" 8 - Ver fila de espera");
        System.out.println(" 9 - Atender próximo ticket");
        System.out.println("10 - Cancelar ticket da fila");
        System.out.println("11 - Listar tickets em atendimento");
        System.out.println("12 - Fechar ticket");
        System.out.println("13 - Listar tickets fechados");
        System.out.println("--- Interações ---");
        System.out.println("14 - Registrar interação");
        System.out.println("15 - Listar interações de um ticket");
        System.out.println("16 - Remover interação");
        System.out.println(" 0 - Sair");
    }

    private static void executar(int opcao) {
        try {
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> buscarCliente();
                case 3 -> removerCliente();
                case 4 -> sistema.listarClientesEmOrdem();
                case 5 -> sistema.listarClientesPreOrdem();
                case 6 -> sistema.listarClientesPosOrdem();
                case 7 -> abrirTicket();
                case 8 -> sistema.listarFila();
                case 9 -> atenderProximo();
                case 10 -> cancelarTicket();
                case 11 -> sistema.listarEmAtendimento();
                case 12 -> fecharTicket();
                case 13 -> sistema.listarHistorico();
                case 14 -> registrarInteracao();
                case 15 -> sistema.listarInteracoes(lerInt("ID do ticket: "));
                case 16 -> removerInteracao();
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cadastrarCliente() {
        String documento = lerTexto("CPF/CNPJ: ");
        String nome = lerTexto("Nome: ");
        String telefone = lerTexto("Telefone: ");
        String plano = lerTexto("Plano: ");

        if (sistema.cadastrarCliente(documento, nome, telefone, plano)) {
            System.out.println("Cliente cadastrado com sucesso.");
        } else {
            System.out.println("Já existe um cliente com esse CPF/CNPJ.");
        }
    }

    private static void buscarCliente() {
        Cliente cliente = sistema.buscarCliente(lerTexto("CPF/CNPJ: "));
        System.out.println(cliente != null ? cliente : "Cliente não encontrado.");
    }

    private static void removerCliente() {
        Cliente removido = sistema.removerCliente(lerTexto("CPF/CNPJ: "));
        if (removido != null) {
            System.out.println("Cliente removido: " + removido.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void abrirTicket() {
        String documento = lerTexto("CPF/CNPJ do cliente: ");
        String descricao = lerTexto("Descrição do problema: ");

        Ticket ticket = sistema.abrirTicket(documento, descricao);
        if (ticket != null) {
            System.out.println("Ticket aberto: " + ticket);
        } else {
            System.out.println("Cliente não cadastrado. Cadastre-o antes de abrir o ticket.");
        }
    }

    private static void atenderProximo() {
        Ticket ticket = sistema.atenderProximo();
        if (ticket != null) {
            System.out.println("Atendendo: " + ticket);
        } else {
            System.out.println("Não há tickets na fila.");
        }
    }

    private static void cancelarTicket() {
        int id = lerInt("ID do ticket: ");
        System.out.println(sistema.cancelarTicket(id) ? "Ticket cancelado." : "Ticket não encontrado na fila.");
    }

    private static void fecharTicket() {
        int id = lerInt("ID do ticket: ");
        System.out.println(sistema.fecharTicket(id) ? "Ticket fechado." : "Ticket não está em atendimento.");
    }

    private static void registrarInteracao() {
        int id = lerInt("ID do ticket: ");
        String autor = lerTexto("Autor: ");
        String mensagem = lerTexto("Mensagem: ");

        if (sistema.registrarInteracao(id, autor, mensagem)) {
            System.out.println("Interação registrada.");
        } else {
            System.out.println("Ticket não encontrado ou já fechado.");
        }
    }

    private static void removerInteracao() {
        int idTicket = lerInt("ID do ticket: ");
        int idInteracao = lerInt("ID da interação: ");

        if (sistema.removerInteracao(idTicket, idInteracao)) {
            System.out.println("Interação removida.");
        } else {
            System.out.println("Ticket ou interação não encontrados.");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine().trim();
    }

    private static int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
