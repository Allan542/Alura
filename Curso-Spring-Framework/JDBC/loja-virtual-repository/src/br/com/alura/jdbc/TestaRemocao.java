package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.dao.ProdutoDAO;

public class TestaRemocao {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection con = connectionFactory.recuperarConexao()) {
//		Statement stm = con.createStatement();
			try(PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?")) {			
				stm.setInt(1, 33);
				stm.execute();
				
				Integer linhasModificadas = stm.getUpdateCount();
				System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
			}
		}
	}
}
