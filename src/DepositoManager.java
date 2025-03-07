import Dados.Deposito;
import Dados.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepositoManager<T extends Comparable<T>> {
    public List<Deposito<T>> depositos = new ArrayList<>();
    public ItemManager<String> gerenciamentoItem;

    public List<Deposito<T>> getDepositos() {
        return new ArrayList<>(depositos);
    }

    public void listarItensPorDeposito(T codigoDeposito, List<Item<T>> todosItens) {
        Deposito<T> deposito = buscarDepositoPorCodigo(codigoDeposito);

        if (deposito == null) {
            System.out.println("Depósito não encontrado.");
            return;
        }

        System.out.println("Itens armazenados no depósito " + deposito.getNome() + ":");
        for (Item<T> item : todosItens) {
            if (item.getDeposito() != null && item.getDeposito().getCodigo().equals(codigoDeposito)) {
                System.out.println("- Código: " + item.getCodigo() + ", Descrição: " + item.getDescricao());
            }
        }
    }

    public Deposito<T> buscarDepositoPorCodigo(T codigo) {
        for (Deposito<T> deposito : depositos) {
            if (deposito.getCodigo().equals(codigo)) {
                return deposito;
            }
        }
        return null;
    }

    public void cadastroDeposito() {
        Scanner in = new Scanner(System.in);
        System.out.println("Código do depósito: ");
        T codigo = (T) in.nextLine().trim();

        System.out.println("Nome do depósito: ");
        String nome = in.nextLine();

        System.out.println("Cidade do depósito: ");
        String cidade = in.nextLine();

        System.out.println("Endereço do depósito: ");
        String endereco = in.nextLine();

        System.out.println("Capacidade máxima do depósito: ");
        int capacidadeMaxima = in.nextInt();

        Deposito<T> novoDeposito = new Deposito<>(codigo, nome, cidade, endereco, capacidadeMaxima);

        int posicaoInsercao = 0;
        for (int i = 0; i < depositos.size(); i++) {
            if (depositos.get(i).getCodigo().compareTo(codigo) > 0) {
                posicaoInsercao = i;
                break;
            }
            posicaoInsercao = i + 1;
        }

        depositos.add(novoDeposito);
        System.out.println("Depósito cadastrado com sucesso!");


    }
}