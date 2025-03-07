package Dados;

import java.io.Serializable;

public class Item<T> implements Serializable {

    private T codigo;
    private String descricao;
    public String categoria;
    private Cliente cliente;
    private int volume;
    private double valorDeclarado;
    private Situacao situacao;
    private Deposito<T> deposito;


    public Item(T codigo, String descricao, String categoria, Cliente cliente, int volume, double valorDeclarado, Situacao situacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.cliente = cliente;
        this.volume = volume;
        this.valorDeclarado = valorDeclarado;
        this.situacao = situacao;
    }

    public T getCodigo() {
        return codigo;
    }

    public void setCodigo(T codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Cliente getCliente(){ return cliente; }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }


    public Deposito<T> getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito<T> deposito) {
        this.deposito = deposito;
    }

    public String toString() {
        return "Item {" +
                "\n  Código: " + codigo +
                "\n  Descrição: " + descricao +
                "\n  Categoria: " + categoria +
                "\n  Cliente: " + (cliente != null ? cliente : "Nenhum") +
                "\n  Volume: " + volume +
                "\n  Valor Declarado: R$ " + String.format("%.2f", valorDeclarado) +
                "\n  Situação: " + situacao +
                "\n}";
    }
}