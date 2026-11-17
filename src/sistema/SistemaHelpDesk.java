package sistema;

import estruturas.ArvoreBinariaBusca;
import estruturas.FilaTickets;
import estruturas.ListaTickets;
import models.Cliente;
import models.StatusEnum;
import models.Ticket;

public class SistemaHelpDesk {
    private ArvoreBinariaBusca clientes;
    private FilaTickets fila;
    private ListaTickets emAtendimento;
    private ListaTickets historico;
    private int proximoIdTicket;

    public SistemaHelpDesk() {
        this.clientes = new ArvoreBinariaBusca();
        this.fila = new FilaTickets();
        this.emAtendimento = new ListaTickets();
        this.historico = new ListaTickets();
        this.proximoIdTicket = 1;
    }

    public boolean cadastrarCliente(String cpfCnpj, String nome, String telefone, String plano) {
        Cliente cliente = new Cliente(cpfCnpj, nome, telefone, plano);
        return clientes.inserir(cliente);
    }

    public Cliente buscarCliente(String cpfCnpj) {
        return clientes.buscar(cpfCnpj);
    }

    public Cliente removerCliente(String cpfCnpj) {
        String chave = Cliente.normalizar(cpfCnpj);

        if (clientes.buscar(chave) == null) {
            return null;
        }
        if (fila.existeDoCliente(chave) || emAtendimento.existeDoCliente(chave)) {
            throw new IllegalStateException("O cliente possui tickets abertos ou em atendimento e não pode ser removido.");
        }
        return clientes.remover(chave);
    }

    public Ticket abrirTicket(String cpfCnpj, String descricao) {
        Cliente cliente = buscarCliente(cpfCnpj);

        if (cliente == null) {
            return null;
        }

        Ticket ticket = new Ticket(proximoIdTicket, cliente, descricao);
        proximoIdTicket++;
        fila.enfileirar(ticket);
        return ticket;
    }

    public Ticket atenderProximo() {
        Ticket ticket = fila.desenfileirar();

        if (ticket == null) {
            return null;
        }

        ticket.iniciarAtendimento();
        emAtendimento.adicionar(ticket);
        return ticket;
    }

    public boolean cancelarTicket(int idTicket) {
        return fila.remover(idTicket);
    }

    public boolean registrarInteracao(int idTicket, String autor, String mensagem) {
        Ticket ticket = localizarTicketAtivo(idTicket);

        if (ticket == null) {
            return false;
        }

        ticket.adicionarInteracao(autor, mensagem);
        return true;
    }

    public boolean fecharTicket(int idTicket) {
        Ticket ticket = emAtendimento.buscar(idTicket);

        if (ticket == null) {
            return false;
        }

        emAtendimento.remover(idTicket);
        ticket.fechar();
        historico.adicionar(ticket);
        return true;
    }

    public Ticket localizarTicket(int idTicket) {
        Ticket ticket = localizarTicketAtivo(idTicket);

        if (ticket == null) {
            ticket = historico.buscar(idTicket);
        }
        return ticket;
    }

    private Ticket localizarTicketAtivo(int idTicket) {
        Ticket ticket = emAtendimento.buscar(idTicket);

        if (ticket == null) {
            ticket = fila.buscar(idTicket);
        }
        return ticket;
    }

    public boolean removerInteracao(int idTicket, int idInteracao) {
        Ticket ticket = localizarTicketAtivo(idTicket);

        if (ticket == null) {
            return false;
        }
        return ticket.getInteracoes().remover(idInteracao);
    }

    public void listarInteracoes(int idTicket) {
        Ticket ticket = localizarTicket(idTicket);

        if (ticket == null) {
            System.out.println("Ticket não encontrado.");
            return;
        }

        System.out.println(ticket);
        ticket.getInteracoes().listar();
    }

    public void listarClientesEmOrdem() {
        clientes.listarEmOrdem();
    }

    public void listarClientesPreOrdem() {
        clientes.listarPreOrdem();
    }

    public void listarClientesPosOrdem() {
        clientes.listarPosOrdem();
    }

    public void listarFila() {
        fila.listar();
    }

    public void listarEmAtendimento() {
        emAtendimento.listar();
    }

    public void listarHistorico() {
        historico.listar();
    }

    public void carregarDadosExemplo() {
        cadastrarCliente("500.000.000-01", "Carlos Silva", "(71) 91111-1111", "Gold");
        cadastrarCliente("300.000.000-02", "Ana Souza", "(71) 92222-2222", "Silver");
        cadastrarCliente("700.000.000-03", "Bruno Lima", "(71) 93333-3333", "Gold");
        cadastrarCliente("400.000.000-04", "Diana Costa", "(71) 94444-4444", "Basic");
        cadastrarCliente("600.000.000-05", "Eduardo Rocha", "(71) 95555-5555", "Silver");
        cadastrarCliente("800.000.000-06", "Fernanda Alves", "(71) 96666-6666", "Gold");
        cadastrarCliente("11.222.333/0001-81", "Mercado Central Ltda", "(71) 3333-0001", "Empresarial");
        cadastrarCliente("45.678.901/0001-23", "Oficina Rapida ME", "(71) 3333-0002", "Empresarial");

        abrirTicket("500.000.000-01", "Internet fora do ar");
        abrirTicket("300.000.000-02", "Erro na fatura de outubro");
        abrirTicket("11.222.333/0001-81", "Sistema de caixa não conecta");
        abrirTicket("700.000.000-03", "Lentidão na conexão");
        abrirTicket("400.000.000-04", "Troca de plano");
    }

    public ArvoreBinariaBusca getClientes() {
        return clientes;
    }

    public FilaTickets getFila() {
        return fila;
    }

    public StatusEnum statusDe(int idTicket) {
        Ticket ticket = localizarTicket(idTicket);
        return ticket == null ? null : ticket.getStatus();
    }
}