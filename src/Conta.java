import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<String> transacoes;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.transacoes = new ArrayList<>();
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Erro: Saldo insuficiente!");
            registrarTransacao("Tentativa de saque falhou (saldo insuficiente)", valor);
        } else {
            saldo -= valor;
            registrarTransacao("Saque", valor);
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: Valor de depósito inválido!");
            registrarTransacao("Tentativa de depósito falhou (valor inválido)", valor);
        } else {
            saldo += valor;
            registrarTransacao("Depósito", valor);
        }
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (valor > saldo) {
            System.out.println("Erro: Saldo insuficiente para transferência!");
            registrarTransacao("Tentativa de transferência falhou (saldo insuficiente)", valor);
        } else {
            this.sacar(valor);
            contaDestino.depositar(valor);
            registrarTransacao("Transferência para conta " + ((Conta) contaDestino).getNumero(), valor);
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }

    private void registrarTransacao(String tipo, double valor) {
        String transacao = String.format("%s de R$%.2f em %s", tipo, valor, new Date().toString());
        transacoes.add(transacao);
    }

    public void imprimirHistoricoTransacoes() {
        System.out.println("=== Histórico de Transações ===");
        for (String transacao : transacoes) {
            System.out.println(transacao);
        }
    }
}
