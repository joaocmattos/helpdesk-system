# Helpdesk System

Trabalho final de Estrutura de Dados (2026.2), Universidade Católica do Salvador. É o Tema 07 da disciplina, um sistema de help desk escrito em Java, com todas as estruturas de dados implementadas à mão, sem as coleções do Java (`ArrayList`, `LinkedList`, `Stack`, `Queue` etc.).

O sistema cadastra clientes com contrato, recebe os tickets de suporte que eles abrem, mantém os tickets em uma fila até um técnico atendê-los e guarda em cada ticket as mensagens trocadas.

## Estruturas de dados

| Estrutura | Uso |
|-----------|-----|
| Árvore binária de busca | Clientes com contrato, buscados por CNPJ/CPF |
| Fila | Tickets abertos aguardando técnico |
| Lista encadeada | Interações e mensagens de cada ticket |

Na árvore, `inserir()` coloca o menor à esquerda e o maior à direita, `buscar()` procura o cliente pelo CNPJ/CPF, `excluir()` trata nó folha, nó com um filho e nó com dois filhos, e `percursos()` exibe os dados em in-ordem, pré-ordem e pós-ordem.

A fila e a lista encadeada têm `inserir()`/`adicionar()`, `buscar()`, `excluir()`/`remover()` e `listar()`.

## Funcionalidades

- cadastro, busca, remoção e listagem de clientes
- abertura de tickets vinculados a um cliente
- atendimento dos tickets em ordem de chegada
- registro e consulta das interações de cada ticket

## Estrutura do repositório

```
helpdesk-system/
├── src/                  # Código-fonte
├── .idea/                # Configurações do IntelliJ
├── helpdesk-system.iml   # Módulo do IntelliJ
└── .gitignore
```

## Como executar

### IntelliJ IDEA

1. Clone o repositório:
   ```bash
   git clone https://github.com/joaocmattos/helpdesk-system.git
   ```
2. Abra a pasta em **File > Open**.
3. Defina o JDK em **File > Project Structure > Project**.
4. Execute a classe `Main`.

### Terminal

```bash
cd helpdesk-system
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out Main
```

Se a classe `Main` estiver em um pacote, use o nome completo, por exemplo `java -cp out app.Main`.
