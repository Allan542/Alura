package br.com.bytebank.banco.modelo;


import java.io.Serializable;

//new ContaCorrente()
// Na serialização, não adianta uma subclasse ser serializavel se a superclasse não é, vai dar erro de qualquer forma. Porém, quando a superclasse é serializável, a subclasse serializável passa a ser opcional
// A afirmação acima só é válida para herança. Se não existir herança entre as classes e sim agregação entre as mesmas, todas devem ser serializáveis
public class ContaCorrente extends Conta implements Tributavel {

	public ContaCorrente(int agencia, int numero) {
		super(agencia, numero); // invoca o construtor da classe pai, classe que está sendo estendida, mas neste caso, com argumentos. super sem parênteses permite invocar métodos da classe que foi estendida.
	}
	
	@Override
	public void saca(double valor) throws SaldoInsuficienteException{
		double valorASacar = valor + 0.2;
		super.saca(valorASacar);
	}

	@Override
	public void deposita(double valor) {
        super.saldo += valor;
    }

	@Override
	public double getValorImposto() {	
		return super.saldo * 0.01;
	}
	
	@Override
	public String toString() {
		return "ContaCorrente, " + super.toString();
	}
	
}
