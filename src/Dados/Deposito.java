package Dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deposito <T> implements Serializable {

    private T codigo;
    private String nome;
    private String cidade;
    private String endereco;
    private int capacidadeMaxima;

    public Deposito(T codigo, String nome, String cidade, String endereco, int capacidadeMaxima) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
        this.capacidadeMaxima = capacidadeMaxima;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @Override
    public String toString() {
        return "Dados do depósito:\n" +
                "Código: " + codigo + "\n" +
                "Nome: " + nome + "\n" +
                "Cidade: " + cidade + "\n" +
                "Endereço: " + endereco + "\n" +
                "Capacidade Máxima: " + capacidadeMaxima + "\n"+ "\n";
    }
}