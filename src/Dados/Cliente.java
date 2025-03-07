package Dados;

import java.io.Serializable;

public class Cliente <T> implements Serializable {

    private T codigo;
    private String nome;
    private String telefone;

    public Cliente(T codigo, String nome, String telefone) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
    }

    public T getCodigo() {
        return codigo;
    }

    public void setCodigo(T codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Dados do cliente:\n" +
                "Código: " + codigo + "\n" +
                "Nome: " + nome + "\n" +
                "Telefone: " + telefone + "\n";
    }

}
