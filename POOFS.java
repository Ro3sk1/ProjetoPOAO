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
    public static void main(String[] args) {
        int escolha_utilizador = -1;
        Scanner sc = new Scanner(System.in);

        POOFS poofs = new POOFS();

        while (escolha_utilizador != 0) {
            poofs.menu();
            while (true) {
                System.out.print("Introduza a sua opção:\s");
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

                    break;
                case 2:

                    break;
                case 3:

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