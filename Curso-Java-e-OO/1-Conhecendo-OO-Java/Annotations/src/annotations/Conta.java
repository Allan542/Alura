package annotations;

/**
 * 
 * @author Allan Carlos
 * @version 1.0
 */

public abstract class Conta {

    @DoublePositivo
    protected double saldo;
    private String titular;
    private String banco;
    private String agencia;
    private String numero;

    public void deposita(double valor) {
        this.setSaldo(this.getSaldo() + valor);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    
}
