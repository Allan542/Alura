package br.com.bytebank.banco.modelo;

public interface Tributavel {
    double getValorImposto(); // o padrão do compilador é aceitar public e abstract quando é um método de uma interface, então public abstract pode ser omitido
    
}
