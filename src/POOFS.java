import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class POOFS {

    private String VERMELHO = "\033[0;31m";
    private String VERDE = "\033[0;32m";
    private String AMARELO = "\033[0;33m";
    private String AZUL = "\033[0;34m";
    private String MAGENTA = "\033[0;35m";
    private String NEGRITO = "\033[1m";
    private String RESET = "\033[0m";

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

    private final List<String> LOCALIZACOES_VALIDAS = Arrays.asList("Portugal Continental", "Açores", "Madeira");

    public void criarMenu(String... opcoes) {  // Usage: criarMenu(Título, opção1, opção2, ..., opçãoN, MensagemDeSaída)
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

    private void sysMsg(String msg) {
        System.out.print(AMARELO + "[POOFS] " + RESET +  msg);
    }

    private void sysWarning(String msg, int tipo) {  // Usage: sysWarning("Mensagem", n) -> 0 = Verde, 1 = Amarelo, 2 = Vermelho
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

    private void lerFicheiroObjetos(String objfilename, String txtfilename) {
        File ficheiro = new File(objfilename);
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
            lerFicheiroTexto(txtfilename);
        } catch (IOException ex) {
            sysWarning("Erro ao ler ficheiro.", 2);
        } catch (ClassNotFoundException ex) {
            sysWarning("Erro ao converter objeto.", 2);
        }
    }

    private void escreverFicheiroObjetos(String objfilename) {
        File ficheiro = new File(objfilename);
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
        } catch (IOException ex) {
            sysWarning("Erro ao escrever ficheiro.", 2);
        }
    }

    private void lerFicheiroTexto(String filename) {
        File ficheiro = new File(filename);
        if (ficheiro.exists() && ficheiro.isFile()) {
            try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(",");
                    switch (partes[0]) {
                        case "CT":
                            Clientes cliente = new Clientes(partes[1], partes[2], partes[3]);
                            clientesList.add(cliente);
                            break;
                        case "TR":
                            ProdAlimentarTaxaReduzida prodTR = new ProdAlimentarTaxaReduzida(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Double.parseDouble(partes[5]), Boolean.parseBoolean(partes[6]), Arrays.asList(partes[7].split(";")));
                            produtosList.add(prodTR);
                            break;
                        case "TI":
                            ProdAlimentarTaxaIntermedia prodTI = new ProdAlimentarTaxaIntermedia(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Double.parseDouble(partes[5]), Boolean.parseBoolean(partes[6]), partes[7]);
                            produtosList.add(prodTI);
                            break;
                        case "TN":
                            ProdAlimentarTaxaNormal prodTN = new ProdAlimentarTaxaNormal(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Double.parseDouble(partes[5]), Boolean.parseBoolean(partes[6]));
                            produtosList.add(prodTN);
                            break;
                        case "CP":
                            ProdFarmaciaComPrescricao prodCP = new ProdFarmaciaComPrescricao(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Double.parseDouble(partes[5]), partes[6]);
                            produtosList.add(prodCP);
                            break;
                        case "SP":
                            ProdFarmaciaSemPrescricao prodSP = new ProdFarmaciaSemPrescricao(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]), Double.parseDouble(partes[5]), partes[6]);
                            produtosList.add(prodSP);
                            break;
                        case "FT":
                            int id = Integer.parseInt(partes[1]);
                            Clientes faturaCliente = clientesList.get(Integer.parseInt(partes[2]));
                            int dia = Integer.parseInt(partes[3]);
                            int mes = Integer.parseInt(partes[4]);
                            int ano = Integer.parseInt(partes[5]);
                            double valorSemIva = Double.parseDouble(partes[6]);
                            double valorIva = Double.parseDouble(partes[7]);
                            double valorTotal = Double.parseDouble(partes[8]);
                            List<Produtos> produtosFatura = new ArrayList<>();
                            for (int i = 9; i < partes.length; i++) {
                                produtosFatura.add(produtosList.get(Integer.parseInt(partes[i])));
                            }
                            Faturas fatura = new Faturas(id, faturaCliente, dia, mes, ano, valorSemIva, valorIva, valorTotal, produtosFatura);
                            faturasList.add(fatura);
                            break;
                        default:
                            sysWarning("Linha inválida no ficheiro: " + linha, 2);
                            break;
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
        boolean verificacaoFaturas;
        Scanner sc = new Scanner(System.in);
        sysMsg("Introduza o contribuinte do cliente: ");
        String numero_contribuinte = sc.nextLine();
        Clientes cliente = null;
        for (Clientes c : clientesList) {
            if (c.getNumero_contribuinte().equals(numero_contribuinte)) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) {
            sysWarning("Cliente com o contribuinte " + numero_contribuinte + " não foi encontrado.", 1);
        } else {
            for (Clientes clientes : clientesList) {
                verificacaoFaturas = false;
                if (clientes.getNumero_contribuinte().equals(numero_contribuinte)) {
                    System.out.println(VERMELHO + "> DADOS DO CLIENTE:" + RESET);
                    System.out.println(" | Cliente: " + AZUL + cliente.getNome() + RESET);
                    System.out.println(" | Localização: " + AZUL + cliente.getLocalizacao() + RESET);
                    System.out.println(" | Número de contribuinte: " + AZUL + cliente.getNumero_contribuinte() + RESET);
                    System.out.println(VERMELHO + " > FATURAS:" + RESET);
                    for (Faturas faturas : faturasList) {
                        if (faturas.getCliente().equals(cliente)) {
                            verificacaoFaturas = true;
                            System.out.println("   <|>");
                            System.out.println("    | ID: " + AZUL + faturas.getId() + RESET);
                            System.out.println("    | Data: " + AZUL + faturas.getDia() + "/" + faturas.getMes() + "/" + faturas.getAno() + RESET);
                            System.out.println("    | " + MAGENTA + "> PRODUTOS (" + faturas.getProdutosList().size() + "):" + RESET);
                            System.out.println("    | | ------------------------------- |");
                            for (Produtos produto : faturas.getProdutosList()) {
                                double iva = produto.calcularIVA(cliente);
                                double valorComIva = produto.getValor_unitario() + produto.getValor_unitario() * iva;
                                System.out.println("    | | Nome: " + AMARELO + produto.getNome() + RESET);
                                System.out.println("    | | Quantidade: " + AMARELO + produto.getQuantidade() + RESET);
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

                    if(!verificacaoFaturas){
                        sysWarning("Nenhuma fatura encontrada para o cliente.", 1);
                    }
                }
            }
        }
    }

    private void listaProdutos(Clientes cliente) {
        for (int i = 0; i < produtosList.size(); i++) {
            Produtos produto = produtosList.get(i);
            System.out.printf(" | %d. ", i + 1);
            produto.printProduto(cliente);
        }
        System.out.println(" | ------------------------------- | | ------------------------------- | | ------------------------------- | | ------------------------------- |");
    }

    private void criarFatura() {
        double valorIva = 0;
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
            listaProdutos(cliente);
            sysMsg("Escolha um produto da lista (ou 0 para cancelar): ");
            int produtoEscolhido = sc.nextInt();
            sc.nextLine();
            if (produtoEscolhido == 0) {
                sysWarning("Criação de fatura cancelada.", 1);
                return;
            } else if (produtoEscolhido > 0 && produtoEscolhido <= produtosList.size()) {
                Produtos produto = produtosList.get(produtoEscolhido - 1);
                produtosFatura.add(produto);
                valorIva += produto.calcularIVA(cliente) * produto.getValor_unitario() * produto.getQuantidade();
            } else {
                sysWarning("Opção inválida. Tente novamente.", 2);
            }

            sysMsg("Deseja adicionar mais produtos? (s/n): ");
            String continuar = sc.nextLine();
            if (!continuar.equals("s")) {
                break;
            }
        }

        double valorSemIva = produtosFatura.stream().mapToDouble(p -> p.getQuantidade() * p.getValor_unitario()).sum();
        double valorTotal = valorSemIva + valorIva;
        int totalQuantidade = produtosFatura.stream().mapToInt(Produtos::getQuantidade).sum();

        Faturas novaFatura = new Faturas(faturasList.size() + 1, cliente, dia, mes, ano, valorSemIva, valorIva, valorTotal, produtosFatura);
        faturasList.add(novaFatura);
        sysWarning("Fatura criada com sucesso.", 0);
        sysMsg("Total de quantidade de produtos na fatura: " + totalQuantidade + "\n");
    }

    private void exportarParaFicheiroTexto() {

        // FUNÇÃO TEMPORÁRIA -> ELIMINAR APÓS TER FICHEIRO TEXTO DE DADOS

        File ficheiro = new File("POOFSData.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiro))) {
            // Write clients
            for (Clientes cliente : clientesList) {
                bw.write("CT," + cliente.getNome() + "," + cliente.getNumero_contribuinte() + "," + cliente.getLocalizacao());
                bw.newLine();
            }

            // Write products
            for (Produtos produto : produtosList) {
                if (produto instanceof ProdAlimentarTaxaReduzida) {
                    ProdAlimentarTaxaReduzida prodTR = (ProdAlimentarTaxaReduzida) produto;
                    bw.write("TR," + prodTR.getCodigo() + "," + prodTR.getNome() + "," + prodTR.getDescricao() + "," + prodTR.getQuantidade() + "," + prodTR.getValor_unitario() + "," + prodTR.isBiologico() + "," + String.join(";", prodTR.getCertificacoes()));
                } else if (produto instanceof ProdAlimentarTaxaIntermedia) {
                    ProdAlimentarTaxaIntermedia prodTI = (ProdAlimentarTaxaIntermedia) produto;
                    bw.write("TI," + prodTI.getCodigo() + "," + prodTI.getNome() + "," + prodTI.getDescricao() + "," + prodTI.getQuantidade() + "," + prodTI.getValor_unitario() + "," + prodTI.isBiologico() + "," + prodTI.getCategoria());
                } else if (produto instanceof ProdAlimentarTaxaNormal) {
                    ProdAlimentarTaxaNormal prodTN = (ProdAlimentarTaxaNormal) produto;
                    bw.write("TN," + prodTN.getCodigo() + "," + prodTN.getNome() + "," + prodTN.getDescricao() + "," + prodTN.getQuantidade() + "," + prodTN.getValor_unitario() + "," + prodTN.isBiologico());
                } else if (produto instanceof ProdFarmaciaComPrescricao) {
                    ProdFarmaciaComPrescricao prodCP = (ProdFarmaciaComPrescricao) produto;
                    bw.write("CP," + prodCP.getCodigo() + "," + prodCP.getNome() + "," + prodCP.getDescricao() + "," + prodCP.getQuantidade() + "," + prodCP.getValor_unitario() + "," + prodCP.getMedicoPrescritor());
                } else if (produto instanceof ProdFarmaciaSemPrescricao) {
                    ProdFarmaciaSemPrescricao prodSP = (ProdFarmaciaSemPrescricao) produto;
                    bw.write("SP," + prodSP.getCodigo() + "," + prodSP.getNome() + "," + prodSP.getDescricao() + "," + prodSP.getQuantidade() + "," + prodSP.getValor_unitario() + "," + prodSP.getCategoria());
                }
                bw.newLine();
            }

            // Write invoices
            for (Faturas fatura : faturasList) {
                bw.write("FT," + fatura.getId() + "," + clientesList.indexOf(fatura.getCliente()) + "," + fatura.getDia() + "," + fatura.getMes() + "," + fatura.getAno() + "," + fatura.getValor_sem_iva() + "," + fatura.getValor_iva() + "," + fatura.getValor_total());
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

    private void listarFaturas() {
        if(faturasList.isEmpty()) {
            sysWarning("Nenhuma fatura encontrada.", 1);
        } else {
            for (Faturas faturas : faturasList) {
                Clientes cliente = faturas.getCliente();
                System.out.println("   <|>");
                System.out.println("    | ID: " + AZUL + faturas.getId() + RESET);
                System.out.println("    | Cliente: " + AZUL + cliente.getNome() + RESET);
                System.out.println("    | Contribuinte: " + AZUL + cliente.getNumero_contribuinte() + RESET);
                System.out.println("    | Localização: " + AZUL + cliente.getLocalizacao() + RESET);
                System.out.println("    | Data: " + AZUL + faturas.getDia() + "/" + faturas.getMes() + "/" + faturas.getAno() + RESET);
                System.out.println("    | " + MAGENTA + "> PRODUTOS (" + faturas.getProdutosList().size() + "):" + RESET);
                System.out.println("    | | -------------------------------------------- |");
                for (Produtos produto : faturas.getProdutosList()) {
                    double iva = produto.calcularIVA(cliente);
                    double valorComIva = produto.getValor_unitario() + produto.getValor_unitario() * iva;
                    System.out.println("    | | Nome: " + AMARELO + produto.getNome() + RESET);
                    System.out.println("    | | Unidades: " + AMARELO + produto.getQuantidade() + RESET);
                    System.out.printf("    | | Valor unitário (s/IVA): " + VERDE + "%.2f€" + RESET + "\n", produto.getValor_unitario());
                    System.out.printf("    | | Valor TOTAL (s/IVA): " + VERDE + "%.2f€" + RESET + "\n", produto.getValor_unitario() * produto.getQuantidade());
                    System.out.printf("    | | IVA: " + AMARELO + "%.2f€ " + MAGENTA + "(%.1f%%)" + RESET + "\n", produto.getValor_unitario() * iva * produto.getQuantidade(), iva * 100);
                    System.out.printf("    | | Valor TOTAL (c/IVA): " + VERDE + "%.2f€" + RESET + "\n", valorComIva * produto.getQuantidade());
                    System.out.println("    | | -------------------------------------------- |");
                }
                System.out.printf("    | PREÇO (s/IVA): " + AMARELO + "%.2f€" + RESET + "\n", faturas.getValor_sem_iva());
                System.out.printf("    | IVA: " + AMARELO + "%.2f€ " + MAGENTA + "(%.1f%%)" + RESET + "\n", faturas.getValor_iva(), faturas.getValor_iva() / faturas.getValor_sem_iva() * 100);
                System.out.printf("    | " + NEGRITO + "TOTAL: " + AZUL + "%.2f€" + RESET + "\n", faturas.getValor_total());
                System.out.println("   <|> ");
            }
        }
    }

    void editarFatura() {
        Scanner sc = new Scanner(System.in);
        sysMsg("Introduza o ID da fatura que deseja editar: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Faturas fatura : faturasList) {
            if (fatura.getId() == id) {
                criarMenu("EDITAR FATURA", "Editar data", "Editar produtos", "Voltar ao menu principal");
                int escolha_editar_fatura = sc.nextInt();
                sc.nextLine();
                switch (escolha_editar_fatura) {
                    case 1:
                        sysMsg("Introduza a nova data da fatura (dd/mm/aaaa): ");
                        String data = sc.nextLine();
                        String[] dataParts = data.split("/");
                        fatura.setDia(Integer.parseInt(dataParts[0]));
                        fatura.setMes(Integer.parseInt(dataParts[1]));
                        fatura.setAno(Integer.parseInt(dataParts[2]));
                        break;
                    case 2:
                        criarMenu("EDITAR PRODUTOS", "Adicionar produto", "Remover produto", "Voltar ao menu anterior");
                        int escolha_editar_produtos = sc.nextInt();
                        sc.nextLine();
                        switch (escolha_editar_produtos) {
                            case 1:
                                while (true) {
                                    listaProdutos(fatura.getCliente());
                                    sysMsg("Escolha um produto da lista (ou 0 para parar, 'c' para cancelar): ");
                                    String input = sc.nextLine();
                                    if (input.equals("0")) {
                                        break;
                                    } else if (input.equals("c")) {
                                        sysWarning("Adição de produto cancelada.", 1);
                                        break;
                                    } else {
                                        try {
                                            int produtoEscolhido = Integer.parseInt(input);
                                            if (produtoEscolhido > 0 && produtoEscolhido <= produtosList.size()) {
                                                Produtos produto = produtosList.get(produtoEscolhido - 1);
                                                fatura.getProdutosList().add(produto);
                                                fatura.setValor_iva(fatura.getValor_iva() + produto.calcularIVA(fatura.getCliente()) * produto.getValor_unitario() * produto.getQuantidade());
                                                fatura.setValor_sem_iva(fatura.getValor_sem_iva() + produto.getValor_unitario() * produto.getQuantidade());
                                                fatura.setValor_total(fatura.getValor_sem_iva() + fatura.getValor_iva());
                                            } else {
                                                sysWarning("Opção inválida. Tente novamente.", 2);
                                            }
                                        } catch (NumberFormatException e) {
                                            sysWarning("Entrada inválida. Por favor, insira um número ou 'c' para cancelar.", 2);
                                        }
                                    }
                                }
                                break;
                            case 2:
                                sysMsg("Produtos: \n");
                                for (int i = 0; i < fatura.getProdutosList().size(); i++) {
                                    Produtos produto = fatura.getProdutosList().get(i);
                                    System.out.printf(" | %d. ", i + 1);
                                    produto.printProduto(fatura.getCliente());
                                }
                                sysMsg("Escolha um produto da lista para remover (ou 0 para cancelar): ");
                                int produtoRemover = sc.nextInt();
                                sc.nextLine();
                                if (produtoRemover == 0) {
                                    sysWarning("Remoção de produto cancelada.", 1);
                                    return;
                                } else if (produtoRemover > 0 && produtoRemover <= fatura.getProdutosList().size()) {
                                    Produtos produtoRemoverObj = fatura.getProdutosList().get(produtoRemover - 1);
                                    fatura.setValor_iva(fatura.getValor_iva() - produtoRemoverObj.calcularIVA(fatura.getCliente()) * produtoRemoverObj.getValor_unitario() * produtoRemoverObj.getQuantidade());
                                    fatura.setValor_sem_iva(fatura.getValor_sem_iva() - produtoRemoverObj.getValor_unitario() * produtoRemoverObj.getQuantidade());
                                    fatura.setValor_total(fatura.getValor_sem_iva() + fatura.getValor_iva());
                                    fatura.getProdutosList().remove(produtoRemover - 1);
                                    if (fatura.getProdutosList().isEmpty()) {
                                        faturasList.remove(fatura);
                                        sysWarning("Fatura removida.", 0);
                                    }
                                } else {
                                    sysWarning("Opção inválida. Tente novamente.", 2);
                                }
                                break;
                            default:
                                sysWarning("Opção inválida. Tente novamente.", 2);
                                break;
                        }
                        break;
                    default:
                        sysWarning("Opção inválida. Tente novamente.", 2);
                        break;
                }
                return;
            }
        }
        sysWarning("Fatura não encontrada.", 2);
    }

    private void getStats() {
        int totalFaturas = 0, totalProdutos = 0;
        double totalSemIva = 0, totalIva = 0, totalComIva = 0;

        for (Faturas fatura : faturasList) {
            totalFaturas++;
            totalProdutos += fatura.getProdutosList().size();
            totalSemIva += fatura.getValor_sem_iva();
            totalIva += fatura.getValor_iva();
            totalComIva += fatura.getValor_total();
        }
        sysMsg("Estatísticas: \n");
        System.out.printf("        > Número de faturas: %s%d%s \n",AZUL, totalFaturas, RESET);
        System.out.printf("        > Número de produtos: %s%d%s \n", AZUL, totalProdutos, RESET);
        System.out.printf("        > Valor total (s/IVA): %s%.2f€%s \n", AZUL, totalSemIva, RESET);
        System.out.printf("        > Valor total IVA: %s%.2f€%s \n", AZUL, totalIva, RESET);
        System.out.printf("        > Valor total (c/IVA): %s%.2f€%s \n", AZUL, totalComIva, RESET);
    }

    public static void main(String[] args) {
        String txtfilename = "POOFSData.txt", objfilename = "POOFSData.obj";
        int escolha_utilizador = -1;
        int escolha_cliente;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();
        poofs.lerFicheiroObjetos(objfilename,txtfilename);

        while (escolha_utilizador != 0) {
            poofs.criarMenu("< M E N U >", "Criar ou editar cliente", "Mostrar lista de clientes", "Criar ou editar faturas", "Mostrar lista de faturas", "Visualizar fatura", "Importar faturas", "Exportar faturas", "Mostrar estatísticas", "Terminar programa");
            while (true) {
                poofs.sysMsg("Introduza a sua opção: ");
                if (sc.hasNextInt()) {
                    escolha_utilizador = sc.nextInt();
                    break;
                } else {
                    poofs.sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
                    sc.next();
                    sc.close();
                }
            }

            switch (escolha_utilizador) {
                case 1:
                    escolha_cliente = -1;
                    while (escolha_cliente != 0) {
                        poofs.criarMenu("CRIAR OU EDITAR CLIENTE", "Criar cliente ", "Editar cliente", "Voltar ao menu principal");
                        poofs.sysMsg("Escolha uma opção: ");
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
                                    poofs.sysWarning("Voltando ao menu principal...",1);
                                    break;
                                default:
                                    poofs.sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                                    break;
                            }
                        } else {
                            poofs.sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
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
                        poofs.criarMenu("CRIAR OU EDITAR FATURA", "Criar fatura ", "Editar fatura", "Voltar ao menu principal");
                        poofs.sysMsg("Escolha uma opção: ");
                        if (sc.hasNextInt()) {
                            escolha_cliente = sc.nextInt();
                            sc.nextLine();
                            switch (escolha_cliente) {
                                case 1:
                                    poofs.criarFatura();
                                    escolha_cliente = 0;
                                    break;
                                case 2:
                                    poofs.editarFatura();
                                    escolha_cliente = 0;
                                    break;
                                case 0:
                                    poofs.sysWarning("Voltando ao menu principal...",1);
                                    break;
                                default:
                                    poofs.sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                                    break;
                            }
                        } else {
                            poofs.sysWarning("ERRO! Por favor, insira um dígito correspondente à opção desejada.",2);
                            sc.next();
                        }
                    }
                    break;
                case 4:
                    poofs.listarFaturas();
                    break;
                case 5:
                    poofs.verFaturas();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    poofs.getStats();
                    break;
                case 0:
                    poofs.sysWarning("A terminar o programa...",1);
                    poofs.escreverFicheiroObjetos(objfilename);
                    poofs.exportarParaFicheiroTexto();
                    break;
                default:
                    poofs.sysWarning("OPÇÃO ERRADA. TENTE NOVAMENTE!",2);
                    break;

            }
        }
    }
}