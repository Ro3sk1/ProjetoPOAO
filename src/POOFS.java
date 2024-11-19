import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class POOFS {

    static String VERMELHO = "\033[0;31m";
    static String VERDE = "\033[0;32m";
    static String AMARELO = "\033[0;33m";
    static String AZUL = "\033[0;34m";
    static String MAGENTA = "\033[0;35m";
    static String NEGRITO = "\033[1m";
    static String RESET = "\033[0m";

    private List<Clientes> clientesList;

    private List<Produtos> produtosList;

    private List<Faturas> faturasList;

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public List<Produtos> getProdutosList() {
        return produtosList;
    }

    public void setProdutosList(List<Produtos> produtosList) {this.produtosList = produtosList;}

    public List<Faturas> getFaturasList() {
        return faturasList;
    }

    public void setFaturasList(List<Faturas> faturasList) {this.faturasList = faturasList;}

    public POOFS() {
        clientesList = new ArrayList<>();
        produtosList = new ArrayList<>();
        faturasList = new ArrayList<>();
    }

    private static final List<String> LOCALIZACOES_VALIDAS = Arrays.asList("Portugal Continental", "Açores", "Madeira");

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
        while (true) {
            sysMsg("Introduza a localização do cliente (1. Portugal Continental, 2. Açores, 3. Madeira): ");
            localizacao = sc.nextLine();
            if (localizacao.equals("1") || localizacao.equals("2") || localizacao.equals("3")) {
                localizacao = LOCALIZACOES_VALIDAS.get(Integer.parseInt(localizacao) - 1);
                sysWarning("Localização definida: " + localizacao,1);
                break;
            } else if (LOCALIZACOES_VALIDAS.contains(localizacao)) {
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
            sysWarning("Database de clientes vazia. Experimente primeiro adicionar algum cliente.", 1);
        } else {
            Scanner sc = new Scanner(System.in);
            sysMsg("Introduza o número de contribuinte do cliente que deseja editar: ");
            String numero_contribuinte = sc.nextLine();
            for (Clientes cliente : clientesList) {
                if (cliente.getNumero_contribuinte().equals(numero_contribuinte)) {
                    String novoNome;
                    while (true) {
                        sysMsg("Introduza o novo nome do cliente (ou 0 para não alterar) [Atual: " + cliente.getNome() + "]: ");
                        novoNome = sc.nextLine();
                        if (novoNome.equals("0")) {
                            break;
                        } else if (novoNome.length() > 35) {
                            sysWarning("Nome inválido. Não pode ter mais de 35 caracteres.", 2);
                        } else if (!novoNome.isEmpty() && !novoNome.matches(".*\\d.*")) {
                            if (novoNome.equals(cliente.getNome())) {
                                sysWarning("O novo nome é igual ao nome atual.", 2);
                            } else {
                                cliente.setNome(novoNome);
                                break;
                            }
                        } else {
                            sysWarning("Nome inválido. Não pode estar vazio ou conter dígitos.", 2);
                        }
                    }

                    String novaLocalizacao;
                    while (true) {
                        sysMsg("Introduza a nova localização do cliente (ou 0 para não alterar) [Atual: " + cliente.getLocalizacao() + "]: ");
                        novaLocalizacao = sc.nextLine();
                        if (novaLocalizacao.equals("0")) {
                            break;
                        } else if (novaLocalizacao.equals("1") || novaLocalizacao.equals("2") || novaLocalizacao.equals("3")) {
                            novaLocalizacao = LOCALIZACOES_VALIDAS.get(Integer.parseInt(novaLocalizacao) - 1);
                            if (novaLocalizacao.equals(cliente.getLocalizacao())) {
                                sysWarning("A nova localização é igual à localização atual.", 2);
                            } else {
                                sysWarning("Localização definida: " + novaLocalizacao, 1);
                                cliente.setLocalizacao(novaLocalizacao);
                                break;
                            }
                        } else if (LOCALIZACOES_VALIDAS.contains(novaLocalizacao)) {
                            if (novaLocalizacao.equals(cliente.getLocalizacao())) {
                                sysWarning("A nova localização é igual à localização atual.", 2);
                            } else {
                                cliente.setLocalizacao(novaLocalizacao);
                                break;
                            }
                        } else {
                            sysWarning("Localização inválida. Deve ser uma das seguintes: 1. Portugal Continental, 2. Açores, 3. Madeira.", 2);
                        }
                    }

                    sysWarning("Cliente editado com sucesso.", 0);
                    return;
                }
            }
            sysWarning("Cliente não encontrado.", 2);
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

    private void lerFicheiroObjetos() {
        File ficheiro = new File("POOFSData.obj");
        try {
            FileInputStream fis = new FileInputStream(ficheiro);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Clientes> clientes = (List<Clientes>)ois.readObject();
            List<Produtos> produtos = (List<Produtos>)ois.readObject();
            List<Faturas> faturas = (List<Faturas>)ois.readObject();

            clientesList.addAll(clientes);

            produtosList.addAll(produtos);

            faturasList.addAll(faturas);

            sysWarning("Dados carregados com sucesso.", 0);

            ois.close();

        } catch (FileNotFoundException ex) {
            sysWarning("Ficheiro não encontrado.", 2);
        } catch (IOException ex) {
            sysWarning("Erro ao ler ficheiro.", 2);
        } catch (ClassNotFoundException ex) {
            sysWarning("Erro ao converter objeto.", 2);
        }
    }

    private void escreverFicheiroObjetos() {
        File ficheiro = new File("POOFSData.obj");
        try {
            FileOutputStream fos = new FileOutputStream(ficheiro);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(clientesList);
            oos.writeObject(produtosList);
            oos.writeObject(faturasList);

            oos.close();

            sysWarning("Dados guardados com sucesso.", 0);

        } catch (FileNotFoundException ex) {
            sysWarning("Ficheiro não encontrado.", 2);
        } catch (IOException ex) {
            sysWarning("Erro ao escrever ficheiro.", 2);
        }
    }

    private void lerFicheiroTexto() {
        File ficheiro = new File("POOFSData.txt");
        try {
            FileReader fr = new FileReader(ficheiro);
            BufferedReader br = new BufferedReader(fr);

            String linha = br.readLine();
            while (linha != null && !linha.equals("produtos:")) {
                String[] cliente = linha.split(",");
                Clientes novoCliente = new Clientes();
                novoCliente.setNome(cliente[0]);
                novoCliente.setNumero_contribuinte(cliente[1]);
                novoCliente.setLocalizacao(cliente[2]);
                clientesList.add(novoCliente);
                linha = br.readLine();
            }

            linha = br.readLine();
            while (linha != null && !linha.equals("faturas:")) {
                String[] produto = linha.split(",");
                Produtos novoProduto = new Produtos();
                novoProduto.setNome(produto[0]);
                novoProduto.setValor_unitario(Double.parseDouble(produto[1]));
                produtosList.add(novoProduto);
                linha = br.readLine();
            }

            linha = br.readLine();
            while (linha != null) {
                String[] fatura = linha.split(",");
                int id = Integer.parseInt(fatura[0]);
                Clientes cliente = clientesList.get(Integer.parseInt(fatura[1]));
                int dia = Integer.parseInt(fatura[2]);
                int mes = Integer.parseInt(fatura[3]);
                int ano = Integer.parseInt(fatura[4]);
                double valor_sem_viva = Double.parseDouble(fatura[5]);
                double valor_iva = Double.parseDouble(fatura[6]);
                double valor_total = Double.parseDouble(fatura[7]);
                List<Produtos> produtosFatura = new ArrayList<>();
                for (int i = 5; i < fatura.length; i++) {
                    produtosFatura.add(produtosList.get(Integer.parseInt(fatura[i])));
                }
                Faturas novaFatura = new Faturas(id, cliente, dia, mes, ano, valor_sem_viva, valor_iva, valor_total, produtosFatura);
                faturasList.add(novaFatura);
                linha = br.readLine();
            }

            br.close();

        } catch (FileNotFoundException ex) {
            sysWarning("Ficheiro não encontrado.", 2);
        } catch (IOException ex) {
            sysWarning("Erro ao ler ficheiro.", 2);
        }
    }

    private void verFaturas() {
        if (clientesList.isEmpty()) {
            sysWarning("Nenhum cliente encontrado.",1);
        } else {
            System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            for (Clientes cliente : clientesList) {
                System.out.println(VERMELHO + "> DADOS DO CLIENTE:" + RESET);
                System.out.println(" | Cliente: " + AZUL + cliente.getNome() + RESET);
                System.out.println(" | Número de contribuinte: " + AZUL + cliente.getNumero_contribuinte() + RESET);
                System.out.println(VERMELHO + " > FATURAS:" + RESET);
                for(Faturas faturas : faturasList) {
                    if (faturas.getCliente().equals(cliente)) {
                        System.out.println("   <|>");
                        System.out.println("    | ID: " + AZUL + faturas.getId() + RESET);
                        System.out.println("    | Data: " + AZUL + faturas.getDia() + "/" + faturas.getMes() + "/" + faturas.getAno() + RESET);
                        System.out.println("    | " + MAGENTA + "> PRODUTOS:" + RESET);
                        System.out.println("    | | --------------------------- |");
                        for (Produtos produto : faturas.getProdutosList()) {
                            System.out.println("    | | Nome: " + AMARELO + produto.getNome() + RESET);
                            System.out.println("    | | Valor unitário: " + VERDE + produto.getValor_unitario() + RESET);
                            System.out.println("    | | --------------------------- |");
                        }
                        System.out.println("    | PREÇO (s/IVA): " + AMARELO + faturas.getValor_sem_iva() + "€" + RESET);
                        System.out.println("    | IVA: " + AMARELO + faturas.getValor_iva() + "€" + RESET);
                        System.out.println("    | " + NEGRITO + "TOTAL: " + AZUL + faturas.getValor_total() + "€" + RESET);
                        System.out.println("   <|> ");
                    }
                }
                System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            }
        }
    }

    public static void main(String[] args) {
        int escolha_utilizador = -1;
        int escolha_cliente;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();

        poofs.lerFicheiroObjetos();

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
                    poofs.verFaturas();

                    break;
                case 6:
                    
                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 0:
                    sysWarning("A terminar o programa...",1);
                    poofs.escreverFicheiroObjetos();
                    break;
                default:
                    sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                    break;

            }
        }
    }
}