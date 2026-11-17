# Helpdesk System

Trabalho final de Estrutura de Dados (2026.2), Universidade Católica do Salvador. É o Tema 07 da disciplina, um sistema de help desk escrito em Java, com todas as estruturas de dados implementadas à mão, sem as coleções do Java (`ArrayList`, `LinkedList`, `Stack`, `Queue` etc.).

O sistema cadastra clientes com contrato, recebe os tickets de suporte que eles abrem, mantém os tickets em uma fila até um técnico atendê-los e guarda em cada ticket as mensagens trocadas.

## Estruturas de dados

| Estrutura | Classes | Uso |
|-----------|---------|-----|
| Árvore binária de busca | `ArvoreBinariaBusca`, `NoABB` | Clientes com contrato, buscados por CNPJ/CPF |
| Fila | `FilaTickets`, `NoFila` | Tickets abertos aguardando técnico |
| Lista encadeada | `ListaInteracoes`, `NoLista` | Interações e mensagens de cada ticket |
| Lista encadeada | `ListaTickets`, `NoListaTicket` | Tickets em atendimento e histórico de tickets fechados |

### Métodos

- **Árvore:** `inserir()` coloca o menor à esquerda e o maior à direita, `buscar()` procura o cliente pelo CNPJ/CPF, `remover()` trata nó folha, nó com um filho e nó com dois filhos (usando o sucessor in-ordem), e os percursos `listarEmOrdem()`, `listarPreOrdem()` e `listarPosOrdem()` exibem os dados em in-ordem, pré-ordem e pós-ordem.
- **Fila:** `enfileirar()`, `desenfileirar()`, `buscar()`, `remover()` (cancela um ticket de qualquer posição) e `listar()`.
- **Listas encadeadas:** `adicionar()`, `buscar()`, `remover()` e `listar()`.

## Fluxo do atendimento

1. O cliente é cadastrado na árvore.
2. Ao abrir um ticket, o cliente é buscado na árvore pelo CNPJ/CPF e o ticket entra no fim da fila.
3. O técnico atende o próximo: o ticket sai da frente da fila e vai para a lista de tickets em atendimento.
4. Durante o atendimento, as mensagens são registradas na lista de interações do ticket.
5. Ao fechar, o ticket sai da lista de atendimento e vai para o histórico.

## Funcionalidades

- cadastro, busca, remoção e listagem de clientes (em-ordem, pré-ordem e pós-ordem)
- abertura de tickets vinculados a um cliente
- atendimento dos tickets em ordem de chegada
- cancelamento de tickets que ainda estão na fila
- registro, consulta e remoção das interações de cada ticket
- fechamento de tickets e consulta ao histórico
- menu interativo no console, com dados de exemplo carregados ao iniciar

## Regras de negócio

- O CNPJ/CPF é normalizado (pontos, traços e barras são removidos) e validado: 11 dígitos para CPF e 14 para CNPJ.
- Não é possível cadastrar dois clientes com o mesmo CNPJ/CPF.
- Só é possível abrir ticket para cliente cadastrado.
- Cliente com ticket na fila ou em atendimento não pode ser removido, para manter a integridade dos tickets.

## Estrutura do repositório

```
helpdesk-system/
├── src/
│   ├── models/           # Cliente, Ticket, Interacao, StatusEnum
│   ├── estruturas/       # Árvore, fila e listas encadeadas
│   └── sistema/          # SistemaHelpDesk, Main (menu) e DemoABB
├── .idea/                # Configurações do IntelliJ
├── helpdesk-system.iml   # Módulo do IntelliJ
└── .gitignore
```

A classe `DemoABB` é uma demonstração isolada da remoção na árvore (folha, um filho e dois filhos).

## Como executar

### IntelliJ IDEA

1. Clone o repositório:
   ```bash
   git clone https://github.com/joaocmattos/helpdesk-system.git
   ```
2. Abra a pasta em **File > Open**.
3. Defina o JDK em **File > Project Structure > Project**.
4. Execute a classe `sistema.Main`.

Se os acentos aparecerem errados no console, adicione `-Dfile.encoding=UTF-8` em **Run > Edit Configurations > VM options**.

### Terminal

```bash
cd helpdesk-system
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out sistema.Main
```

Para ver a demonstração da árvore, use `sistema.DemoABB` no lugar de `sistema.Main`.
