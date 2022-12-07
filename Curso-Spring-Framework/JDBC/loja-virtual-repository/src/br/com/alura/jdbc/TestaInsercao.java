package br.com.alura.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection con = connectionFactory.recuperarConexao()) {
			try(Statement stm = con.createStatement()) {
				boolean resultado = stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
						Statement.RETURN_GENERATED_KEYS);
				try(ResultSet rst = stm.getGeneratedKeys()) {
					while(rst.next()) {
						Integer id = rst.getInt(1);
						System.out.println("O id criado foi: " + id);
					}
				}
			}
		}
	}
}
