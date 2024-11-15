import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class POOFS {

    static String VERMELHO = "\033[0;31m";
    static String VERDE = "\033[0;32m";
    static String AMARELO = "\033[0;33m";
    static String AZUL = "\033[0;34m";
    static String NEGRITO = "\033[1m";
    static String RESET = "\033[0m";

    private List<Clientes> clientesList;

    public POOFS() {
        clientesList = new ArrayList<>();
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public static void criarMenu(String... opcoes) {  // Usage: criarMenu(Título, opção1, opção2, ..., opçãoN, MensagemDeSaída)
        int maxLength = 0;
        for (String opcao : opcoes) {
            if (opcao.length() > maxLength) {
                maxLength = opcao.length();
            }
        }

        String border = "_".repeat(maxLength + 6);
        System.out.println(border + "_");
        int padding = (maxLength + 6 - opcoes[0].length()) / 2;
        System.out.println("|" + " ".repeat(padding) + AZUL + opcoes[0] + RESET + " ".repeat(maxLength + 6 - opcoes[0].length() - padding) + "|");
        for (int i = 1; i < opcoes.length - 1; i++) {
            System.out.printf("| %d . " + NEGRITO + "%-" + maxLength + "s" + RESET + " |\n", i, opcoes[i]);
        }
        System.out.printf("| " + VERMELHO + "0 . %-" + maxLength + "s" + RESET + " |\n", opcoes[opcoes.length - 1]);
        System.out.println("|" + border + "|");
    }

    private static void sysMsg(String msg) {
        System.out.print(AMARELO + "[POOFS] " + RESET +  msg);
    }

    private static void sysWarning(String msg, int tipo) {  // Usage: sysWarning("Mensagem", 0) -> 0 = Verde, 1 = Amarelo, 2 = Vermelho
        if (tipo == 2) {
            System.out.println(VERMELHO + "[POOFS] " + msg + RESET);
        } else if (tipo == 1) {
            System.out.println(AMARELO + "[POOFS] " + msg + RESET);
        } else if (tipo == 0) {
            System.out.println(VERDE + "[POOFS] " + msg + RESET);
        }
    }

    private Clientes criarCliente() {
        Scanner sc = new Scanner(System.in);
        Clientes cliente = new Clientes();
        String nome;
        while (true) {
            sysMsg("Introduza o nome do cliente: ");
            nome = sc.nextLine();
            if (nome.length() > 35) {
                sysWarning("Nome inválido. Não pode ter mais de 35 caracteres.", 2);
            } else if (!nome.isEmpty() && !nome.matches(".*\\d.*")) {
                break;
            } else {
                sysWarning("Nome inválido. Não pode estar vazio ou conter dígitos.", 2);
            }
        }
        cliente.setNome(nome);

        String numeroContribuinte;
        while (true) {
            sysMsg("Introduza o número de contribuinte do cliente (9 dígitos): ");
            numeroContribuinte = sc.nextLine();
            if (numeroContribuinte.matches("\\d{9}")) {
                break;
            } else {
                sysWarning("Número de contribuinte inválido. Deve ter 9 dígitos.", 2);
            }
        }
        cliente.setNumero_contribuinte(numeroContribuinte);

        String localizacao;
        List<String> localizacoesValidas = Arrays.asList("Portugal Continental", "Açores", "Madeira");
        while (true) {
            sysMsg("Introduza a localização do cliente (1. Portugal Continental, 2. Açores, 3. Madeira): ");
            localizacao = sc.nextLine();
            if (localizacao.equals("1") || localizacao.equals("2") || localizacao.equals("3")) {
                localizacao = localizacoesValidas.get(Integer.parseInt(localizacao) - 1);
                sysWarning("Localização definida: " + localizacao,1);
                break;
            } else if (localizacoesValidas.contains(localizacao)) {
                break;
            } else {
                sysWarning("Localização inválida. Deve ser uma das seguintes: 1. Portugal Continental, 2. Açores, 3. Madeira.", 2);
            }
        }
        cliente.setLocalizacao(localizacao);

        clientesList.add(cliente);
        sysWarning("Cliente adicionado:",0);
        System.out.println(VERDE + "        | Nome: " + NEGRITO + cliente.getNome() + RESET);
        System.out.println(VERDE + "        | Número de contribuinte: " + NEGRITO + cliente.getNumero_contribuinte() + RESET);
        System.out.println(VERDE + "        | Localização: " + NEGRITO + cliente.getLocalizacao() + RESET);
        return cliente;
    }

    private void editarCliente() {
        if (clientesList.isEmpty()) {
            sysWarning("Database de clientes vazia. Experimente primeiro adicionar algum cliente.",1);

        } else {
            Scanner sc = new Scanner(System.in);
            sysMsg("Introduza o número de contribuinte do cliente que deseja editar: ");
            String numero_contribuinte = sc.nextLine();
            for (Clientes cliente : clientesList) {
                if (cliente.getNumero_contribuinte().equals(numero_contribuinte)) {
                    sysMsg("Introduza o novo nome do cliente (ou 0 para não alterar) [Atual: " + cliente.getNome() + "]: ");
                    String novoNome = sc.nextLine();
                    if (!novoNome.equals("0")) {
                        cliente.setNome(novoNome);
                    }
                    sysMsg("Introduza a nova localização do cliente (ou 0 para não alterar) [Atual: " + cliente.getLocalizacao() + "]: ");
                    String novaLocalizacao = sc.nextLine();
                    if (!novaLocalizacao.equals("0")) {
                        cliente.setLocalizacao(novaLocalizacao);
                    }
                    sysWarning("Cliente editado com sucesso.",0);
                    return;
                }
            }
            sysWarning("Cliente não encontrado.",2);
        }
    }

    private void mostrarListaDeClientes() {
        if (clientesList.isEmpty()) {
            sysWarning("Nenhum cliente encontrado.",1);
        } else {
            System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            for (Clientes cliente : clientesList) {
                System.out.println("  Nome: " + AZUL + cliente.getNome() + RESET);
                System.out.println("  Número de contribuinte: " + AZUL + cliente.getNumero_contribuinte() + RESET);
                System.out.println("  Localização: " + AZUL + cliente.getLocalizacao() + RESET);
                System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            }
        }
    }

    public static void main(String[] args) {
        int escolha_utilizador = -1;
        int escolha_cliente;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();

        while (escolha_utilizador != 0) {
            criarMenu("< M E N U >", "Criar ou editar cliente", "Mostrar lista de clientes", "Criar ou editar faturas", "Mostrar lista de faturas", "Visualizar fatura", "Importar faturas", "Exportar faturas", "Mostrar estatísticas", "Terminar programa");
            while (true) {
                sysMsg("Introduza a sua opção: ");
                if (sc.hasNextInt()) {
                    escolha_utilizador = sc.nextInt();
                    break;
                } else {
                    sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
                    sc.next();
                    sc.close();
                }
            }

            switch (escolha_utilizador) {
                case 1:
                    escolha_cliente = -1;
                    while (escolha_cliente != 0) {
                        criarMenu("CRIAR OU EDITAR CLIENTE", "Criar cliente ", "Editar cliente", "Voltar ao menu principal");
                        sysMsg("Escolha uma opção: ");
                        if (sc.hasNextInt()) {
                            escolha_cliente = sc.nextInt();
                            sc.nextLine();
                            switch (escolha_cliente) {
                                case 1:
                                    Clientes novoCliente = poofs.criarCliente();
                                    escolha_cliente = 0;
                                    break;
                                case 2:
                                    poofs.editarCliente();
                                    escolha_cliente = 0;
                                    break;
                                case 0:
                                    sysWarning("Voltando ao menu principal...",1);
                                    break;
                                default:
                                    sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                                    break;
                            }
                        } else {
                            sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
                            sc.next();
                        }
                    }
                    break;
                case 2:
                    poofs.mostrarListaDeClientes();
                    break;
                case 3:
                    escolha_cliente = -1;
                    while (escolha_cliente != 0) {
                        criarMenu("CRIAR OU EDITAR FATURA", "Criar fatura ", "Editar fatura", "Voltar ao menu principal");
                        sysMsg("Escolha uma opção: ");
                        if (sc.hasNextInt()) {
                            escolha_cliente = sc.nextInt();
                            sc.nextLine();
                            switch (escolha_cliente) {
                                case 1:

                                    escolha_cliente = 0;
                                    break;
                                case 2:

                                    escolha_cliente = 0;
                                    break;
                                case 0:
                                    sysWarning("Voltando ao menu principal...",1);
                                    break;
                                default:
                                    sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                                    break;
                            }
                        } else {
                            sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
                            sc.next();
                        }
                    }
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
                    sysWarning("A terminar o programa...",1);
                    break;
                default:
                    sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                    break;

            }
        }
    }
}