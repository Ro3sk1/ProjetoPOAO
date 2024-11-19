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

            sysWarning("Dados do ficheiro de objetos carregados com sucesso.", 0);

            ois.close();

        } catch (FileNotFoundException ex) {
            sysWarning("Ficheiro não encontrado.", 2);
            lerFicheiroTexto();
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

            sysWarning("Dados guardados com sucesso no ficheiro de objetos.", 0);

        } catch (FileNotFoundException ex) {
            sysWarning("Ficheiro de objetos não encontrado.", 2);
            lerFicheiroTexto();
        } catch (IOException ex) {
            sysWarning("Erro ao escrever ficheiro.", 2);
        }
    }

    private void lerFicheiroTexto() {
        File ficheiro = new File("POOFSData.txt");
        if (ficheiro.exists() && ficheiro.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
                String linha = br.readLine();
                if (linha != null) {
                    String[] counts = linha.split(",");
                    int numClientes = Integer.parseInt(counts[0].trim());
                    int numProdutos = Integer.parseInt(counts[1].trim());
                    int numFaturas = Integer.parseInt(counts[2].trim());

                    // Read clients
                    for (int i = 0; i < numClientes; i++) {
                        linha = br.readLine();
                        if (linha != null) {
                            String[] cliente = linha.split(",");
                            if (cliente.length == 3) {
                                Clientes novoCliente = new Clientes(cliente[0].trim(), cliente[1].trim(), cliente[2].trim());
                                clientesList.add(novoCliente);
                            }
                        }
                    }

                    // Read products
                    for (int i = 0; i < numProdutos; i++) {
                        linha = br.readLine();
                        if (linha != null) {
                            String[] produto = linha.split(",");
                            if (produto.length == 5) {
                                Produtos novoProduto = new Produtos(produto[0].trim(), produto[1].trim(), produto[2].trim(), Integer.parseInt(produto[3].trim()), Double.parseDouble(produto[4].trim()));
                                produtosList.add(novoProduto);
                            }
                        }
                    }

                    // Read invoices
                    for (int i = 0; i < numFaturas; i++) {
                        linha = br.readLine();
                        if (linha != null) {
                            String[] fatura = linha.split(",");
                            int id = Integer.parseInt(fatura[0].trim());
                            int clienteIndex = Integer.parseInt(fatura[1].trim());
                            if (clienteIndex < 0 || clienteIndex >= clientesList.size()) {
                                throw new IndexOutOfBoundsException("Invalid client index: " + clienteIndex);
                            }
                            Clientes cliente = clientesList.get(clienteIndex);
                            int dia = Integer.parseInt(fatura[2].trim());
                            int mes = Integer.parseInt(fatura[3].trim());
                            int ano = Integer.parseInt(fatura[4].trim());
                            double valor_sem_iva = Double.parseDouble(fatura[5].trim());
                            double valor_iva = Double.parseDouble(fatura[6].trim());
                            double valor_total = Double.parseDouble(fatura[7].trim());
                            List<Produtos> produtosFatura = new ArrayList<>();
                            for (int j = 8; j < fatura.length; j++) {
                                int produtoIndex = Integer.parseInt(fatura[j].trim());
                                if (produtoIndex < 0 || produtoIndex >= produtosList.size()) {
                                    throw new IndexOutOfBoundsException("Invalid product index: " + produtoIndex);
                                }
                                produtosFatura.add(produtosList.get(produtoIndex));
                            }
                            Faturas novaFatura = new Faturas(id, cliente, dia, mes, ano, valor_sem_iva, valor_iva, valor_total, produtosFatura);
                            faturasList.add(novaFatura);
                        }
                    }
                }
                sysWarning("Dados do ficheiro de texto carregados com sucesso.", 0);
            } catch (FileNotFoundException ex) {
                sysWarning("Ficheiro de texto não encontrado.", 2);
            } catch (IOException ex) {
                sysWarning("Erro ao ler ficheiro.", 2);
            }
        } else {
            sysWarning("Ficheiro de dados não encontrado.", 2);
        }
    }

    private void verFaturas() {
        if (clientesList.isEmpty()) {
            sysWarning("Nenhum cliente encontrado.", 1);
        } else {
            System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            for (Clientes cliente : clientesList) {
                System.out.println(VERMELHO + "> DADOS DO CLIENTE:" + RESET);
                System.out.println(" | Cliente: " + AZUL + cliente.getNome() + RESET);
                System.out.println(" | Localização: " + AZUL + cliente.getLocalizacao() + RESET);
                System.out.println(" | Número de contribuinte: " + AZUL + cliente.getNumero_contribuinte() + RESET);
                System.out.println(VERMELHO + " > FATURAS:" + RESET);
                for (Faturas faturas : faturasList) {
                    if (faturas.getCliente().equals(cliente)) {
                        System.out.println("   <|>");
                        System.out.println("    | ID: " + AZUL + faturas.getId() + RESET);
                        System.out.println("    | Data: " + AZUL + faturas.getDia() + "/" + faturas.getMes() + "/" + faturas.getAno() + RESET);
                        System.out.println("    | " + MAGENTA + "> PRODUTOS:" + RESET);
                        System.out.println("    | | ------------------------------- |");
                        for (Produtos produto : faturas.getProdutosList()) {
                            double iva = produto.calcularIVA(cliente);
                            double valorComIva = produto.getValor_unitario() + produto.getValor_unitario() * iva;
                            System.out.println("    | | Nome: " + AMARELO + produto.getNome() + RESET);
                            System.out.printf("    | | Valor unitário (s/IVA): " + VERDE + "%.2f€" + RESET + "\n", produto.getValor_unitario());
                            System.out.printf("    | | IVA: " + AMARELO + "%.2f€ " + MAGENTA + "(%.1f%%)" + RESET + "\n", produto.getValor_unitario() * iva, iva * 100);
                            System.out.printf("    | | Valor unitário (c/IVA): " + VERDE + "%.2f€" + RESET + "\n", valorComIva);
                            System.out.println("    | | ------------------------------- |");
                        }
                        System.out.printf("    | PREÇO (s/IVA): " + AMARELO + "%.2f€" + RESET + "\n", faturas.getValor_sem_iva());
                        System.out.printf("    | IVA: " + AMARELO + "%.2f€ " + MAGENTA + "(%.1f%%)" + RESET + "\n", faturas.getValor_iva(), faturas.getValor_iva() / faturas.getValor_sem_iva() * 100);
                        System.out.printf("    | " + NEGRITO + "TOTAL: " + AZUL + "%.2f€" + RESET + "\n", faturas.getValor_total());
                        System.out.println("   <|> ");
                    }
                }
                System.out.println(NEGRITO + "| ------------------ | | ------------------ |" + RESET);
            }
        }
    }

    private void criarFatura() {
        double valorIva = 0;
        double valorTotal = 0;
        Scanner sc = new Scanner(System.in);
        sysMsg("Introduza o número de contribuinte do cliente: ");
        String nif = sc.nextLine();
        Clientes cliente = null;
        for (Clientes c : clientesList) {
            if (c.getNumero_contribuinte().equals(nif)) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) {
            sysWarning("Cliente não encontrado.", 2);
            return;
        }

        sysMsg("Introduza a data de criação da fatura (dd/mm/aaaa): ");
        String data = sc.nextLine();
        String[] dataParts = data.split("/");
        int dia = Integer.parseInt(dataParts[0]);
        int mes = Integer.parseInt(dataParts[1]);
        int ano = Integer.parseInt(dataParts[2]);

        List<Produtos> produtosFatura = new ArrayList<>();
        while (true) {
            sysMsg("Produtos: \n");
            for (int i = 0; i < produtosList.size(); i++) {
                Produtos produto = produtosList.get(i);
                System.out.printf(" | %d. %s (%s) - %.2f€\n", i + 1, produto.getNome(), produto.getDescricao(), produto.getValor_unitario());
            }
            sysMsg("Escolha um produto da lista (ou 0 para cancelar): ");
            int produtoEscolhido = sc.nextInt();
            sc.nextLine();
            if (produtoEscolhido == 0) {
                sysWarning("Criação de fatura cancelada.", 1);
                return;
            } else if (produtoEscolhido > 0 && produtoEscolhido <= produtosList.size()) {
                produtosFatura.add(produtosList.get(produtoEscolhido - 1));
                valorIva += produtosList.get(produtoEscolhido - 1).calcularIVA(cliente)*produtosList.get(produtoEscolhido - 1).getValor_unitario();
            } else {
                sysWarning("Opção inválida. Tente novamente.", 2);
            }

            sysMsg("Deseja adicionar mais produtos? (s/n): ");
            String continuar = sc.nextLine();
            if (!continuar.equalsIgnoreCase("s")) {
                break;
            }
        }

        double valorSemIva = produtosFatura.stream().mapToDouble(p -> p.getQuantidade() * p.getValor_unitario()).sum();
        valorTotal = valorSemIva + valorIva;
        int totalQuantidade = produtosFatura.stream().mapToInt(Produtos::getQuantidade).sum();

        Faturas novaFatura = new Faturas(faturasList.size() + 1, cliente, dia, mes, ano, valorSemIva, valorIva, valorTotal, produtosFatura);
        faturasList.add(novaFatura);
        sysWarning("Fatura criada com sucesso.", 0);
        sysMsg("Total de quantidade de produtos na fatura: " + totalQuantidade + "\n");
    }

    private void exportarParaFicheiroTexto() {
        File ficheiro = new File("POOFSData.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiro))) {
            // Write the counts of clients, products, and invoices
            bw.write(clientesList.size() + "," + produtosList.size() + "," + faturasList.size());
            bw.newLine();

            // Write clients
            for (Clientes cliente : clientesList) {
                bw.write(cliente.getNome() + "," + cliente.getNumero_contribuinte() + "," + cliente.getLocalizacao());
                bw.newLine();
            }

            // Write products
            for (Produtos produto : produtosList) {
                bw.write(produto.getCodigo() + "," + produto.getNome() + "," + produto.getDescricao() + "," + produto.getQuantidade() + "," + produto.getValor_unitario());
                bw.newLine();
            }

            // Write invoices
            for (Faturas fatura : faturasList) {
                bw.write(fatura.getId() + "," + clientesList.indexOf(fatura.getCliente()) + "," + fatura.getDia() + "," + fatura.getMes() + "," + fatura.getAno() + "," + fatura.getValor_sem_iva() + "," + fatura.getValor_iva() + "," + fatura.getValor_total());
                for (Produtos produto : fatura.getProdutosList()) {
                    bw.write("," + produtosList.indexOf(produto));
                }
                bw.newLine();
            }

            sysWarning("Dados exportados com sucesso para o ficheiro de texto.", 0);
        } catch (IOException ex) {
            sysWarning("Erro ao escrever no ficheiro de texto.", 2);
        }
    }

    public static void main(String[] args) {
        int escolha_utilizador = -1;
        int escolha_cliente;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();

        poofs.lerFicheiroObjetos();

        poofs.produtosList.add(new ProdAlimentarTaxaNormal("ALIM3", "Arroz", "Taxa Normal", 1, 1.20,false));
        poofs.produtosList.add(new ProdAlimentarTaxaNormal("ALIM4", "Feijão", "Taxa Normal", 1, 1.50, true));
        poofs.produtosList.add(new ProdAlimentarTaxaReduzida("ALIM5", "Maçã", "Taxa Reduzida", 1, 0.80, true, Arrays.asList("FSSC22000", "ISO22000")));
        poofs.produtosList.add(new ProdAlimentarTaxaReduzida("ALIM6", "Banana", "Taxa Reduzida", 1, 0.50, false, Arrays.asList("FSSC22000", "ISO22000")));
        poofs.produtosList.add(new ProdFarmaciaComPrescricao("FARM1", "Ben-u-ron 1g", "Com prescrição", 1, 5.0, "Rogério Machado"));
        poofs.produtosList.add(new ProdFarmaciaSemPrescricao("FARM2", "Ben-u-ron 500mg", "Sem prescrição", 1, 4.0,"bebés"));
        poofs.produtosList.add(new ProdAlimentarTaxaIntermedia("ALIM7", "Espumante Bairrada", "Taxa Intermedia", 1, 1.00, false, "vinho"));
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
                                    poofs.criarFatura();
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
                    poofs.exportarParaFicheiroTexto();
                    break;
                default:
                    sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                    break;

            }
        }
    }
}