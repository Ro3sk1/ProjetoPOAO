import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POOFS {

    private void menu() {
        System.out.println("""
                __________________________________
                |           < M E N U >           |
                | 1 . Criar ou editar cliente     |
                | 2 . Mostrar lista de clientes   |
                | 3 . Criar ou editar faturas     |
                | 4 . Mostrar lista de faturas    |
                | 5 . Visualizar fatura           |
                | 6 . Importar faturas            |
                | 7 . Exportar faturas            |
                | 8 . Mostrar estatísticas        |
                | 0 . Terminar programa           |
                |_________________________________|""");
    }

    public static void criarMenu(String... opcoes) {
        int maxLength = 0;
        for (String opcao : opcoes) {
            if (opcao.length() > maxLength) {
                maxLength = opcao.length();
            }
        }

        String border = "_".repeat(maxLength + 6);
        System.out.println(border + "_");
        int padding = (maxLength + 6 - opcoes[0].length()) / 2;
        System.out.println("|" + " ".repeat(padding) + opcoes[0] + " ".repeat(maxLength + 6 - opcoes[0].length() - padding) + "|");
        for (int i = 1; i < opcoes.length-1; i++) {
            System.out.printf("| %d . %-" + maxLength + "s |\n", i, opcoes[i]);
        }
        System.out.printf("| 0 . %-" + maxLength + "s |\n", opcoes[opcoes.length-1]);
        System.out.println("|" + border + "|");
    }

    private Clientes criarCliente() {
        Scanner sc = new Scanner(System.in);
        Clientes cliente = new Clientes();
        System.out.print("Introduza o nome do cliente: ");
        cliente.setNome(sc.nextLine());
        System.out.print("Introduza o número de contribuinte do cliente: ");
        cliente.setNumero_contribuinte(sc.nextLine());
        System.out.print("Introduza a localização do cliente: ");
        cliente.setLocalizacao(sc.nextLine());
        clientesList.add(cliente);
        return cliente;
    }

    private void editarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduza o número de contribuinte do cliente que deseja editar: ");
        String numero_contribuinte = sc.nextLine();
        for (Clientes cliente : clientesList) {
            if (cliente.getNumero_contribuinte().equals(numero_contribuinte)) {
                System.out.print("Introduza o novo nome do cliente (ou 0 para não alterar) [Atual: " + cliente.getNome() + "]: ");
                String novoNome = sc.nextLine();
                if (!novoNome.equals("0")) {
                    cliente.setNome(novoNome);
                }
                System.out.print("Introduza a nova localização do cliente (ou 0 para não alterar) [Atual: " + cliente.getLocalizacao() + "]: ");
                String novaLocalizacao = sc.nextLine();
                if (!novaLocalizacao.equals("0")) {
                    cliente.setLocalizacao(novaLocalizacao);
                }
                System.out.println("Cliente editado com sucesso.");
                return;
            }
        }
        System.out.println("Cliente não encontrado.");
    }

    private void mostrarListaDeClientes() {
        if (clientesList.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            for (Clientes cliente : clientesList) {
                System.out.println("-----------------------------");
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Número de contribuinte: " + cliente.getNumero_contribuinte());
                System.out.println("Localização: " + cliente.getLocalizacao());
                System.out.println("-----------------------------");
            }
        }
    }

    private List<Clientes> clientesList = new ArrayList<>();

    public static void main(String[] args) {
        int escolha_utilizador = -1;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();

        while (escolha_utilizador != 0) {
            poofs.criarMenu("MENU", "Criar ou editar cliente", "Mostrar lista de clientes", "Criar ou editar faturas", "Mostrar lista de faturas", "Visualizar fatura", "Importar faturas", "Exportar faturas", "Mostrar estatísticas", "Terminar programa");
            while (true) {
                System.out.print("Introduza a sua opção: ");
                if (sc.hasNextInt()) {
                    escolha_utilizador = sc.nextInt();
                    break;
                } else {
                    System.out.println("ERRO! Por favor, insira um dígito correspondente à opção desejada.");
                    sc.next();
                    sc.close();
                }
            }

            switch (escolha_utilizador) {
                case 1:
                    int escolha_cliente = -1;
                    while (escolha_cliente != 0) {
                        poofs.criarMenu("CRIAR OU EDITAR CLIENTE", "Criar cliente ", "Editar cliente", "Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");
                        if (sc.hasNextInt()) {
                            escolha_cliente = sc.nextInt();
                            sc.nextLine();
                            switch (escolha_cliente) {
                                case 1:
                                    Clientes novoCliente = poofs.criarCliente();
                                    System.out.println("Cliente adicionado:");
                                    System.out.println("Nome: " + novoCliente.getNome());
                                    System.out.println("Número de contribuinte: " + novoCliente.getNumero_contribuinte());
                                    System.out.println("Localização: " + novoCliente.getLocalizacao());
                                    escolha_cliente = 0;
                                    break;
                                case 2:
                                    poofs.editarCliente();
                                    escolha_cliente = 0;
                                    break;
                                case 0:
                                    System.out.println("Voltando ao menu principal...");
                                    break;
                                default:
                                    System.out.println("OPÇÃO ERRADA. TENTE NOVAMENTE!");
                                    break;
                            }
                        } else {
                            System.out.println("ERRO! Por favor, insira um dígito correspondente à opção desejada.");
                            sc.next();
                        }
                    }
                    break;
                case 2:
                    poofs.mostrarListaDeClientes();
                    break;
                case 3:
                    poofs.editarCliente();
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    
                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 0:
                    System.out.println("A terminar o programa...");
                    break;
                default:
                    System.out.println("OPÇÃO ERRADA. TENTE NOVAMENTE!");
                    break;

            }
        }
    }
}