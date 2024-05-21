import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        banco.setNome("Meu Banco Digital");

        List<Conta> contas = new ArrayList<>();
        
        Cliente venilton = new Cliente();
        venilton.setNome("Esther");

        Conta cc = new ContaCorrente(venilton);
        Conta poupanca = new ContaPoupanca(venilton);

        contas.add(cc);
        contas.add(poupanca);
        banco.setContas(contas);

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Depositar na Conta Corrente");
            System.out.println("2. Sacar da Conta Corrente");
            System.out.println("3. Transferir para a Conta Poupança");
            System.out.println("4. Ver Extrato da Conta Corrente");
            System.out.println("5. Ver Extrato da Conta Poupança");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();

            Conta contaCorrente = banco.getContas().get(0);
            Conta contaPoupanca = banco.getContas().get(1);

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    contaCorrente.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.print("Digite o valor para saque: ");
                    double valorSaque = scanner.nextDouble();
                    contaCorrente.sacar(valorSaque);
                    break;
                case 3:
                    System.out.print("Digite o valor para transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    contaCorrente.transferir(valorTransferencia, contaPoupanca);
                    break;
                case 4:
                    contaCorrente.imprimirExtrato();
                    break;
                case 5:
                    contaPoupanca.imprimirExtrato();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
