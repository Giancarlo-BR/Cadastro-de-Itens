import Dados.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;


public class ItemManager<T extends Comparable<T>>  {
    private List<Item<T>> itensPendentes = new ArrayList<>();
    private List<Item<T>> itensArmazenados = new ArrayList<>();
    private List<Item<T>> itensRetirados = new ArrayList<>();
    private List<Item<T>> itensCancelados = new ArrayList<>();
    private Queue<Item<T>> filaItensCadastrados = new LinkedList<>();

    public List<Item<T>> getItensPendentes() {
        return new ArrayList<>(itensPendentes);
    }
    public List<Item<T>> getItensArmazenados() {
        return new ArrayList<>(itensArmazenados);
    }

    public List<Item<T>> getItensRetirados() {
        return new ArrayList<>(itensRetirados);
    }

    public List<Item<T>> getItensCancelados() {
        return new ArrayList<>(itensCancelados);
    }



    public void cadastroItem(List<Item<T>> todosItens) throws CodigoItemDuplicadoException {
        Scanner in = new Scanner(System.in);

        System.out.print("Código do item: ");
        T codigo = (T) in.nextLine();

        for (Item<T> item : todosItens) {
            if (item.getCodigo().equals(codigo)) {
                throw new CodigoItemDuplicadoException("Erro: Código de item já existe.");
            }
        }

        System.out.print("Descrição do item: ");
        String descricao = in.nextLine();

        System.out.println("Escolha a categoria do item:");
        System.out.println("1 - Perecível");
        System.out.println("2 - Durável");
        int escolhaCategoria = in.nextInt();
        in.nextLine();

        Object categoria = null;
        if (escolhaCategoria == 1) {
            System.out.print("Origem do item: ");
            String origem = in.nextLine();
            System.out.print("Tempo máximo de validade (dias): ");
            int tempoMaximoValidade = in.nextInt();
            in.nextLine();
            categoria = new Perecivel(origem, tempoMaximoValidade);
        } else if (escolhaCategoria == 2) {
            System.out.print("Setor do item: ");
            String setor = in.nextLine();
            System.out.print("Material principal: ");
            String materialPrincipal = in.nextLine();
            categoria = new Duravel(setor, materialPrincipal);
        } else {
            System.out.println("Escolha inválida de categoria.");
            return;
        }

        System.out.print("Volume do item: ");
        int volume = in.nextInt();

        System.out.print("Valor declarado do item: ");
        double valorDeclarado = in.nextDouble();

        Item<T> novoItem = new Item<>(
                codigo,
                descricao,
                categoria.toString(),
                null,
                volume,
                valorDeclarado,
                Situacao.PENDENTE
        );

        itensPendentes.add(novoItem); // Lista interna do ItemManager
        todosItens.add(novoItem);    // Lista externa passada como argumento
        filaItensCadastrados.add(novoItem);

        System.out.println("Item cadastrado com sucesso!");
    }

    public void salvarItensPendentesEmArquivo(String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(itensPendentes);
            System.out.println("Lista de itens pendentes salva no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os itens pendentes: " + e.getMessage());
        }
    }

    public void carregarItensPendentesDeArquivo(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            List<Item<T>> itensCarregados = (List<Item<T>>) ois.readObject();
            System.out.println("Itens pendentes carregados do arquivo:");
            for (Item<T> item : itensCarregados) {
                System.out.println(item);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os itens pendentes: " + e.getMessage());
        }
    }




    public void alterarSituacao(List<Item<T>> todosItens, DepositoManager<T> depositoManager){
        Scanner in = new Scanner(System.in);
        System.out.println("====== Alterar a situação do item ====== \n" );
        System.out.println("Informe o código do item:");
        T codigoItem = (T) in.nextLine();

        Item<T> itemEncontrado = null;
        for(Item<T> item : todosItens){
            if (item.getCodigo().equals(codigoItem)) {
                itemEncontrado = item;
                break;
            }
        }

        if (itemEncontrado == null) {
            System.out.println("Erro: Item com o código informado não encontrado.");
        }

        System.out.println("Item encontrado:");
        System.out.println("Código: " + itemEncontrado.getCodigo());
        System.out.println("Descrição: " + itemEncontrado.getDescricao());
        System.out.println("Categoria: " + itemEncontrado.getCategoria());
        System.out.println("Volume: " + itemEncontrado.getVolume());
        System.out.println("Valor declarado: " + itemEncontrado.getValorDeclarado() + "\n");

        if (itemEncontrado.getSituacao() == Situacao.RETIRADO) {
            System.out.println("Este item já foi retirado");
        }

        System.out.println("Escolha a nova situação para o item:");
        System.out.println("1 - Pendente");
        System.out.println("2 - Armazenado");
        System.out.println("3 - Cancelado");
        System.out.println("4 - Retirado");
        int escolhaSituacao = in.nextInt();

        Situacao novaSituacao = null;
        switch (escolhaSituacao) {
            case 1:
                novaSituacao = Situacao.PENDENTE;
                break;
            case 2:
                novaSituacao = Situacao.ARMAZENADO;
                System.out.println("Escolha o depósito para armazenar o item:");
                List<Deposito<T>> depositos = depositoManager.getDepositos();
                if (depositos.isEmpty()) {
                    System.out.println("Não há depósitos cadastrados.");
                    return;
                }

                for (Deposito<T> deposito : depositos) {
                    System.out.println("- Código: " + deposito.getCodigo() + ", Nome: " + deposito.getNome());
                }

                in.nextLine();

                System.out.print("Código do depósito: ");
                T codigoDeposito = (T) in.nextLine().trim();


                Deposito<T> depositoEscolhido = depositoManager.buscarDepositoPorCodigo(codigoDeposito);

                if (depositoEscolhido == null) {
                    System.out.println("Depósito com o código informado não encontrado.");
                    return;
                }
                itemEncontrado.setDeposito(depositoEscolhido);
                itensPendentes.remove(itemEncontrado);
                itensArmazenados.add(itemEncontrado);

                System.out.println("Item armazenado no depósito: " + depositoEscolhido.getNome());
                break;
            case 3: novaSituacao = Situacao.CANCELADO;
                break;
            case 4: novaSituacao = Situacao.RETIRADO;
                break;
            default: {
                System.out.println("Opção inválida!");
            }
            return;
        }
        itemEncontrado.setSituacao(novaSituacao);
        System.out.println("Situação do item alterada para: " + novaSituacao);
        if (novaSituacao == Situacao.ARMAZENADO) {
            System.out.println("Item adicionado ao depósito: "+ itemEncontrado.getDescricao());
        }

    }

    public void consultarItem(List<Item<T>> todosItens){
        Scanner in = new Scanner(System.in);
        System.out.println("====== Consultar um item ======");
        System.out.println("Codigo do item: ");
        in.nextLine();

        List<Item<T>> itensArmazenados = new ArrayList<>();
        for (Item<T> item : todosItens){
            if (item.getSituacao() == Situacao.ARMAZENADO) {
                itensArmazenados.add(item);
            }
        }

        if(itensArmazenados.isEmpty()){
            System.out.println("Não há itens armazenados.");
        }

        for (Item<T> item : itensArmazenados){
            System.out.println("Dados do item:");
            System.out.println("Código: " + item.getCodigo());
            System.out.println("Descrição: " + item.getDescricao());
            System.out.println("Categoria: " + item.getCategoria());
            System.out.println("Volume: " + item.getVolume());
            System.out.println("Valor declarado: " + item.getValorDeclarado());
        }

    }


    public void organizaItens(DepositoManager<T> depositoManager) {
        for (Item<T> item : itensPendentes) {
            boolean alocado = false;
            for (Deposito<T> deposito : depositoManager.getDepositos()) {
                if (deposito.getCapacidadeMaxima() >= item.getVolume()) {
                    deposito.setCapacidadeMaxima(deposito.getCapacidadeMaxima() - item.getVolume());
                    item.setSituacao(Situacao.ARMAZENADO);
                    item.setDeposito(deposito);
                    itensArmazenados.add(item);
                    itensPendentes.remove(item);
                    alocado = true;
                    System.out.println("Item " + item.getCodigo() + " alocado no depósito " + deposito.getNome());
                    break;
                }
            }
            if (!alocado) {
                item.setSituacao(Situacao.CANCELADO);
                System.out.println("Item " + item.getCodigo() + " cancelado devido à falta de espaço.");
            }
        }
    }


    public void salvarDados(List<Cliente<T>> clientes, List<Item<T>> todosItens, List<Deposito<T>> depositos) {
        try {
            try (ObjectOutputStream oosCliente = new ObjectOutputStream(new FileOutputStream("clientes.txt"))) {
                oosCliente.writeObject(clientes);
                System.out.println("Dados de clientes salvos com sucesso.");
            }

            try (ObjectOutputStream oosItem = new ObjectOutputStream(new FileOutputStream("itens.txt"))) {
                oosItem.writeObject(todosItens);
                System.out.println("Dados de itens salvos com sucesso.");
            }

            try (ObjectOutputStream oosDeposito = new ObjectOutputStream(new FileOutputStream("depositos.txt"))) {
                oosDeposito.writeObject(depositos);
                System.out.println("Dados de depósitos salvos com sucesso.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
    public void carregarDadosSalvos(List<Cliente<T>> clientes, List<Item<T>> todosItens, List<Deposito<T>> depositos) {
        try {
            try (ObjectInputStream oisCliente = new ObjectInputStream(new FileInputStream("clientes.txt"))) {
                clientes.addAll((List<Cliente<T>>) oisCliente.readObject());
                System.out.println("Dados de clientes carregados com sucesso.");
            }
            try (ObjectInputStream oisItem = new ObjectInputStream(new FileInputStream("itens.txt"))) {
                todosItens.addAll((List<Item<T>>) oisItem.readObject());
                System.out.println("Dados de itens carregados com sucesso.");
            }

            try (ObjectInputStream oisDeposito = new ObjectInputStream(new FileInputStream("depositos.txt"))) {
                depositos.addAll((List<Deposito<T>>) oisDeposito.readObject());
                System.out.println("Dados de depósitos carregados com sucesso.");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
    public void finalizaSistema() {
        System.out.println("Finalizando o sistema...");
        System.exit(0);
    }
}
