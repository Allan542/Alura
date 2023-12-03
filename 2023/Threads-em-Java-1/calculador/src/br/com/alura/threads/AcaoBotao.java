
package br.com.alura.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class AcaoBotao implements ActionListener {

	private JTextField primeiro;
	private JTextField segundo;
	private JLabel resultado;

	public AcaoBotao(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Curiosiade: a thread implementa a classe Runnable, ou seja, a thread é uma Runnable. Não é o recomendado a se fazer, porque está usando e abusando da herança, assim fazendo uma "herança por preguiça",
		// que nada mais é que estender uma classe com um monte de métodos e utilizar apenas um, que neste caso, seria o run(). Também, o polimorfismo que a herança oferece, não está sendo aproveitado
		// Uma boa prática é separar a definição da tarefa de uma responsabilidade de ser uma thread. Sendo assim, o recomendado no mundo OO é implementar a Runnable em vez de herdar da Thread.
		Runnable tarefa = new TarefaMultiplicacao(primeiro, segundo, resultado); // Tarefa criada para que possa ser associada à thread. Necessário construtor com parâmetros se necessário passar atributos
		Thread threadCalculo = new Thread(tarefa, "Thread Calculador"); // Associando tarefa a ser rodada na thread e criando a mesma para rodar numa thread paralela a thread main. O segundo parâmetro é o nome da thread
		threadCalculo.start();// Rodar a thread com a tarefa. Não trava a tela porque está executando numa thread separada ea thread main deixa de existir porque ela já fez o que tinha que fazer, existindo apenas essa thread processando o cálculo
	}

}