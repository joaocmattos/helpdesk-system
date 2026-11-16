package models;

public class Cliente {
     private String cpfCnpj;
     private String nome;
     private String telefone;
     private String plano;

     public Cliente(String cpfCnpj, String nome, String telefone, String plano) {
          this.cpfCnpj = normalizar(cpfCnpj);
          this.nome = nome;
          this.telefone = telefone;
          this.plano = plano;
     }

     public static String normalizar(String documento) {
          String digitos = documento == null ? "" : documento.replaceAll("\\D", "");
          if (digitos.length() != 11 && digitos.length() != 14) {
               throw new IllegalArgumentException("CPF deve ter 11 dígitos e CNPJ 14 dígitos.");
          }
          return digitos;
     }

     public String getCpfCnpj() {
          return cpfCnpj;
     }

     public String getNome() {
          return nome;
     }

     public String getTelefone() {
          return telefone;
     }

     public String getPlano() {
          return plano;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

     public void setTelefone(String telefone) {
          this.telefone = telefone;
     }

     public void setPlano(String plano) {
          this.plano = plano;
     }

     @Override
     public String toString() {
          return "Cliente{cpfCnpj=" + cpfCnpj + ", nome=" + nome + ", telefone=" + telefone + ", plano=" + plano + "}";
     }
}