package Dados;

public class Perecivel implements Categoria {
    private String origem;
    private int tempoMaximoValidade;

    public Perecivel(String origem, int tempoMaximoValidade) {
        this.origem = origem;
        this.tempoMaximoValidade = tempoMaximoValidade;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getTempoMaximoValidade() {
        return tempoMaximoValidade;
    }

    public void setTempoMaximoValidade(int tempoMaximoValidade) {
        this.tempoMaximoValidade = tempoMaximoValidade;
    }

    @Override
    public String getDescricao() {
        return "Dados.Item Perecível de origem: " + origem + " com tempo máximo de validade de " + tempoMaximoValidade + " dias.";
    }
}
