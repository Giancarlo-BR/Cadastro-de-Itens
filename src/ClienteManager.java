import Dados.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteManager<T extends Comparable<T>> {
    public List<Cliente<T>> clientes = new ArrayList<>();

    public void cadastroCliente() {
        Scanner in = new Scanner(System.in);
        System.out.println("Código do Cliente: ");
        T codigo = (T) in.nextLine();

        System.out.println("Nome do Cliente: ");
        String nome = in.nextLine();

        System.out.println("Telefone do Cliente: ");
        String telefone = in.nextLine();

        Cliente<T> novoCliente = new Cliente<>(codigo, nome, telefone);

        int posicaoInsercao = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCodigo().compareTo(codigo) > 0) {
                posicaoInsercao = i;
                break;
            }
            posicaoInsercao = i + 1;
        }

        clientes.add(posicaoInsercao, novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");


    }

}
