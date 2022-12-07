package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection connection = connectionFactory.recuperarConexao()){		
			System.out.println("Fechando Conexão!!");
		}
		
	}

}
