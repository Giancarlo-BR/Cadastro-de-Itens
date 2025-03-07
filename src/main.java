import Dados.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws CodigoItemDuplicadoException {

        Scanner in = new Scanner(System.in);
        DepositoManager<String> gerenciamentoDeposito = new DepositoManager<>();
        ClienteManager<String> gerenciamentoCliente = new ClienteManager<>();
        ItemManager<String> gerenciamentoItem = new ItemManager<>();
        List<Item<String>> todosItens = new ArrayList<>();

//                      Teste
//        gerenciamentoDeposito.cadastroDeposito();
//        gerenciamentoItem.cadastroItem(todosItens);
//        gerenciamentoItem.alterarSituacao(todosItens, gerenciamentoDeposito);
//        gerenciamentoItem.consultarItem(todosItens);

        int opcao = 0;

        do{
            System.out.println("======== Menu =========");
            System.out.println("Opções: \n" +
                    "1: Cadastrar depósito \n" +
                    "2: Cadastrar cliente \n" +
                    "3: Cadastrar item \n" +
                    "4: Consultar itens armazenados \n" +
                    "5: Alterar situação de um item \n" +
                    "6: Carregar dados iniciais \n" +
                    "7: Organizar itens para armazenamento \n" +
                    "8: Salvar dados \n" +
                    "9: Carregar dados salvos \n" +
                    "10: Encerrar programa \n ");

            opcao = in.nextInt();
            switch(opcao){
                case 1:
                    gerenciamentoDeposito.cadastroDeposito();
                    break;
                case 2:
                    gerenciamentoCliente.cadastroCliente();
                    break;
                case 3:
                    gerenciamentoItem.cadastroItem(todosItens);
                    break;
                case 4:
                    gerenciamentoItem.consultarItem(todosItens);
                    break;
                case 5:
                    gerenciamentoItem.alterarSituacao(todosItens, gerenciamentoDeposito);
                    break;
                case 6:
                    System.out.println("========== Gerenciamento de dados ==========");
                    System.out.println("1: Salvar itens pendentes em arquivo");
                    System.out.println("2: Carregar itens pendentes de arquivo");
                    System.out.print("Escolha uma opção: ");
                    int escolha = in.nextInt();
                    in.nextLine();

                    String nomeArquivo = "itens_pendentes.txt";

                    if (escolha == 1) {
                        gerenciamentoItem.salvarItensPendentesEmArquivo(nomeArquivo);
                    } else if (escolha == 2) {
                        gerenciamentoItem.carregarItensPendentesDeArquivo(nomeArquivo);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 7:
                    gerenciamentoItem.organizaItens(gerenciamentoDeposito);
                    break;
                case 8:
                    gerenciamentoItem.salvarDados(gerenciamentoCliente.clientes, todosItens, gerenciamentoDeposito.depositos);
                    break;
                case 9:
                    gerenciamentoItem.carregarDadosSalvos(gerenciamentoCliente.clientes, todosItens, gerenciamentoDeposito.depositos);
                    break;
                case 10:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while(opcao != 10);
        in.close();


    }
}
